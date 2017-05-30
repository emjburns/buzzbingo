import { Component, OnInit } from '@angular/core';
import { Location }               from '@angular/common';

import { ActivatedRoute, Params, Router } from '@angular/router';
import { GameService } from './game.service'
import { GameboardService } from './gameboard.service'
import { Game } from './game';
import { Gameboard } from './gameboard';
// import { WordbankService } from './wordbank.service';

@Component({
  selector: 'gameboard',
  templateUrl: './gameboard.component.html',
  styleUrls: [ './gameboard.component.css' ],
  providers: [GameService, GameboardService]
})
export class GameboardComponent implements OnInit {
  errorMessage: string;
  gameboard: Gameboard;
  mode = 'Observable';

  constructor(
    private gameService: GameService,
    private gameboardService: GameboardService,
    private route: ActivatedRoute,
    private location: Location,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.params
      .switchMap((params: Params) => this.gameboardService.getGameboard(params['gameboardName']))
      .subscribe(
        gameboard => this.gameboard = gameboard,
        error =>  this.errorMessage = <any>error
      );
  }

}
