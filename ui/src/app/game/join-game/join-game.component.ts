import { Component, OnInit } from '@angular/core';
import { Router }            from '@angular/router';
import { Observable }        from 'rxjs/Observable';
import { Subject }           from 'rxjs/Subject';
// Observable class extensions
import 'rxjs/add/observable/of';

// Observable operators
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import { CookieService } from 'ngx-cookie';

import { GameSearchService } from '../game-search/game-search.service';
import { IdentityService } from '../../identity/identity.service';
import { Game } from '../game'
import { GameService } from '../game.service'
import { BuzzUtils }    from '../../buzzutils'


@Component({
  selector: 'join-game',
  templateUrl: './join-game.component.html',
  styleUrls: [ './join-game.component.css' ],
  providers: [GameService, IdentityService]
})
export class JoinGameComponent implements OnInit {
  games: Observable<Game[]>;

  constructor(
    private gameService: GameService,
    private cookieService: CookieService,
    private router: Router,
    private buzzutils: BuzzUtils,
    private identityService: IdentityService
  ) {}

  ngOnInit(): void {
  }

  goToGame(gameName: string): void {
    let username: string = this.identityService.getID();
    this.gameService.addPlayer(gameName, username)
      .subscribe(
        () => this.router.navigate(['/gameboard', gameName])
      );
  }

}
