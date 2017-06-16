import { Component, OnInit } from '@angular/core';
import { Router }            from '@angular/router';
import { Observable }        from 'rxjs/Observable';
import { Subject }           from 'rxjs/Subject';
// Observable class extensions
import 'rxjs/add/observable/of';

// Observable operators
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import { CookieService } from 'ngx-cookie';

import { GameSearchService } from '../game-search/game-search.service';
import { IdentityService } from '../../identity/identity.service';
import { GameService } from '../game.service';
import { AlertService } from '../../alert/alert.service';

import { Game } from '../game';
import { BuzzUtils }    from '../../buzzutils';


@Component({
  selector: 'join-game',
  templateUrl: './join-game.component.html',
  styleUrls: [ './join-game.component.css' ],
  providers: [GameService, IdentityService]
})
export class JoinGameComponent implements OnInit {
  games: Observable<Game[]>;
  gameExists: boolean;

  constructor(
    private gameService: GameService,
    private cookieService: CookieService,
    private router: Router,
    private buzzutils: BuzzUtils,
    private identityService: IdentityService,
    private alertService: AlertService,
  ) {}

  ngOnInit(): void {
  }

  goToGame(gameName: string): void {
    let username: string = this.identityService.getID();
    this.gameService.addPlayer(gameName, username)
    .subscribe(
      game => this.router.navigate(['/gameboard', gameName]),
      error => this.alertService.error(error)
    );
  }
}
