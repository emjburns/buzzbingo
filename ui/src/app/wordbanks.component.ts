import { Component, OnInit } from '@angular/core';

import { Wordbank } from './wordbank';
import { WordbankService } from './wordbank.service';

@Component({
  selector: 'wordbank-dashboard',
  templateUrl: './wordbanks.component.html',
  styleUrls: [ './wordbanks.component.css' ],
  providers: [WordbankService]
})
export class WordbanksComponent implements OnInit {
  wordbanks: Wordbank[] = [];

  constructor(private wordbankService: WordbankService) { }

  ngOnInit(): void {
    this.wordbankService.getWordbanks()
      .then(wordbanks => this.wordbanks = wordbanks);
  }
}
