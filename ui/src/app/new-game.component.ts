import { Component, OnInit } from '@angular/core';

import { Game } from './game';
// import { WordbankService } from './wordbank.service';
import { GameService } from './game.service'
import { ApiWordbankService } from './api-wordbank.service'
// Observable class extensions
import 'rxjs/add/observable/of';

// Observable operators
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';

@Component({
  selector: 'new-game',
  templateUrl: './new-game.component.html',
  styleUrls: [ './new-game.component.css' ],
  providers: [GameService, ApiWordbankService]
})
export class NewGameComponent implements OnInit {
  errorMessage: string;
  game: Game;
  wordbanks: String[]
  mode = 'Observable';
  wb: string;

  constructor(
    private gameService: GameService,
    private apiWordbankService: ApiWordbankService
  ) { }

  ngOnInit(): void {
    this.getWordbankNames();
  }

  getWordbankNames() {
    this.apiWordbankService.getWordbankNames()
        .subscribe(
          wordbanks => this.wordbanks = wordbanks,
          error => this.errorMessage = <any>error
        );
  }

  createGame(name: string, wordbank: string) {
    if (!name) { return; }
    this.gameService.createGame(name, wordbank)
      .subscribe(
        game => this.game = game,
        error =>  this.errorMessage = <any>error
      );
      //TODO: redirect to that game
  }

}
