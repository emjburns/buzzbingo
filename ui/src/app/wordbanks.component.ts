import { Component, OnInit } from '@angular/core';

import { Wordbank } from './wordbank';
// import { WordbankService } from './wordbank.service';
import { ApiWordbankService } from './api-wordbank.service'


@Component({
  selector: 'wordbank-dashboard',
  templateUrl: './wordbanks.component.html',
  styleUrls: [ './wordbanks.component.css' ],
  providers: [ApiWordbankService]
})
export class WordbanksComponent implements OnInit {
  errorMessage: string;
  wordbanks: Wordbank[] = [];
  mode = 'Observable';

  constructor(
    private apiWordbankService: ApiWordbankService
  ) { }

  ngOnInit(): void {
    this.getWordbanks();
    // this.wordbankService.getWordbanks()
    //   .then(wordbanks => this.wordbanks = wordbanks);
  }

  getWordbanks() {
    this.apiWordbankService.getWordbanks()
        .subscribe(
          wordbanks => this.wordbanks = wordbanks,
          error => this.errorMessage = <any>error
        );
  }

  addWordbank(name: string, words: string) {
    let wordArray = words.split(",")
    if (!name) { return; }
    this.apiWordbankService.create(name, wordArray)
       .subscribe(
         wordbank  => this.wordbanks.push(wordbank),
         error =>  this.errorMessage = <any>error
       );
  }

}
