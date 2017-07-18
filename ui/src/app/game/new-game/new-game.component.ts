import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';

// Observable class extensions
import 'rxjs/add/observable/of';
// Observable operators
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';

import { Game } from '../game';
// import { WordbankService } from './wordbank.service';
import { GameService } from '../game.service'
import { WordbankService } from '../../wordbank/wordbank.service'
import { IdentityService } from '../../identity/identity.service';
import { AlertService }    from '../../alert/alert.service';

@Component({
  selector: 'new-game',
  templateUrl: './new-game.component.html',
  styleUrls: [ './new-game.component.css' ],
  providers: [GameService, WordbankService, IdentityService]
})
export class NewGameComponent implements OnInit {
  errorMessage: string;
  game: Game;
  wordbanks: String[]
  mode = 'Observable';
  wb: string;

  constructor(
    private gameService: GameService,
    private wordbankService: WordbankService,
    private route: ActivatedRoute,
    private router: Router,
    private identityService: IdentityService,
    private alertService: AlertService,
  ) { }

  ngOnInit(): void {
    this.getWordbankNames();
  }

  getWordbankNames() {
    this.wordbankService.getWordbankNames()
        .subscribe(
          wordbanks => this.wordbanks = wordbanks,
          error => this.errorMessage = <any>error
        );
  }

  createGame(name: string, wordbank: string): void {
    console.log("creating game");
    if (!name) { return; }
    console.log("name: " + name);
    this.gameService.createGame(name, wordbank)
      .subscribe(
        game => this.game = game,
        error =>  this.alertService.error(error),
        () => this.joinGameandNavigate(name)
      );
  }

  joinGameandNavigate(name: string): void {
    console.log("navigating to game");
    let username: string = this.identityService.getID();
    this.gameService.addPlayer(name, username)
      .subscribe(
        () => this.router.navigate(['/gameboard', name])
      );
  }

}
