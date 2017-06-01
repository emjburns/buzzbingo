import { Component, OnInit } from '@angular/core';
import { Location }               from '@angular/common';
import { ActivatedRoute, Params, Router } from '@angular/router';
// Observable class extensions
import 'rxjs/add/observable/of';

// Observable operators
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';

import { GameService } from '../game/game.service'
import { GameboardService } from './gameboard.service'
import { IdentityService } from '../identity/identity.service';

import { Game } from '../game/game';
import { Square } from './square';
import { Gameboard } from './gameboard';

@Component({
  selector: 'gameboard',
  templateUrl: './gameboard.component.html',
  styleUrls: [ './gameboard.component.css' ],
  providers: [GameService, GameboardService, IdentityService]
})
export class GameboardComponent implements OnInit {
  errorMessage: string;
  gameboard: Gameboard;
  mode = 'Observable';
  game: Game;

  constructor(
    private gameService: GameService,
    private gameboardService: GameboardService,
    private identityService: IdentityService,
    private route: ActivatedRoute,
    private location: Location,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.params
      .switchMap((params: Params) => this.gameboardService.getGameboard(params['gameName'], this.identityService.getID()))
      .subscribe(
        gameboard => this.gameboard = gameboard,
        error =>  this.errorMessage = <any>error
      );
  }

  toggle(index: number){
    this.gameboardService.toggle(this.gameboard.gameName, this.gameboard.playerName, index)
    .subscribe(
      gameboard => this.gameboard = gameboard,
      error =>  this.errorMessage = <any>error
    );
  }

  bingo() {
    this.gameboardService.bingo(this.gameboard.gameName, this.gameboard.playerName)
    .subscribe(
      gameboard => this.gameboard = gameboard,
      error =>  this.errorMessage = <any>error
    );
  }

  //TODO: get game on init, have a clear winner name
  getGame() {
    console.log("getting game");
    this.gameService.getGame(this.gameboard.gameName)
      .subscribe(
        game => this.game = game,
        error => this.errorMessage = <any>error
      )
  }
}
