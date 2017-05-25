import { Component, OnInit } from '@angular/core';
import { Location }               from '@angular/common';

import { ActivatedRoute, Params, Router } from '@angular/router';
import { GameService } from './game.service'
import { Game } from './game';
// import { WordbankService } from './wordbank.service';

@Component({
  selector: 'game',
  templateUrl: './game.component.html',
  styleUrls: [ './game.component.css' ],
  providers: [GameService]
})
export class GameComponent implements OnInit {
  errorMessage: string;
  game: Game;
  mode = 'Observable';
  words: string[] = ["one","two","three","four","five","six","seven",
    "eight","nine","ten","eleven","twelve","thirteen","fourteen","fifteen",
    "sixteen","seventeen","eighteen","nineteen","twenty","twentyone","twentytwo",
    "twentythree","twentyfour","twentyfive"]

  constructor(
    private gameService: GameService,
    private route: ActivatedRoute,
    private location: Location,
    private router: Router
  ) {}

  ngOnInit(): void {
    // this.route.params
    //   .switchMap((params: Params) => this.gameService.getGame(params['name']))
    //   .subscribe(
    //     game => this.game = game,
    //   );
  }

}
