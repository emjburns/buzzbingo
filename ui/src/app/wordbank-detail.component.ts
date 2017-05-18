import 'rxjs/add/operator/switchMap';
import { Component, OnInit }      from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Location }               from '@angular/common';

import { Wordbank }        from './wordbank';
import { WordbankService } from './wordbank.service';
import { ApiWordbankService} from './api-wordbank.service';

@Component({
  selector: 'my-wordbank-detail',
  templateUrl: './wordbank-detail.component.html',
  styleUrls: [ './wordbank-detail.component.css' ]
})
export class WordbankDetailComponent implements OnInit {
  wordbank: Wordbank;
  mode = 'Observable';

  constructor(
    private wordbankService: WordbankService,
    private apiWordbankService: ApiWordbankService,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.route.params
      .switchMap((params: Params) => this.apiWordbankService.getWordbank(params['name']))
      .subscribe(wordbank => this.wordbank = wordbank);
  }

  // save(): void {
  //   this.heroService.update(this.hero)
  //     .then(() => this.goBack());
  // }

  goBack(): void {
    this.location.back();
  }
}
