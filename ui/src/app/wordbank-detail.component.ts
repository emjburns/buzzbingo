import 'rxjs/add/operator/switchMap';
import { Component, OnInit }      from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Location }               from '@angular/common';

import { Wordbank }        from './wordbank';
import { WordbankService } from './wordbank.service';

@Component({
  selector: 'my-wordbank-detail',
  templateUrl: './wordbank-detail.component.html',
  styleUrls: [ './wordbank-detail.component.css' ]
})
export class WordbankDetailComponent implements OnInit {
  wordbank: Wordbank;

  constructor(
    private wordbankService: WordbankService,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.route.params
      .switchMap((params: Params) => this.wordbankService.getWordbank(+params['id']))
      .subscribe(wordbank => this.wordbank = wordbank);
      console.log(this.wordbank)
  }

  // save(): void {
  //   this.heroService.update(this.hero)
  //     .then(() => this.goBack());
  // }

  goBack(): void {
    this.location.back();
  }
}
