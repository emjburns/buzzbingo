import { Component, OnInit } from '@angular/core';

import { Wordbank } from './wordbank';
// import { WordbankService } from './wordbank.service';
import { WordbankService } from './wordbank.service'
// Observable class extensions
import 'rxjs/add/observable/of';

// Observable operators
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';

@Component({
  selector: 'wordbank-dashboard',
  templateUrl: './wordbanks.component.html',
  styleUrls: [ './wordbanks.component.css' ],
  providers: [WordbankService]
})
export class WordbanksComponent implements OnInit {
  errorMessage: string;
  wordbanks: Wordbank[] = [];
  mode = 'Observable';

  constructor(
    private wordbankService: WordbankService
  ) { }

  ngOnInit(): void {
    this.getWordbanks();
  }

  getWordbanks() {
    this.wordbankService.getWordbanks()
        .subscribe(
          wordbanks => {
            this.wordbanks = wordbanks,
            this.sortWordbanks()
          },
          error => this.errorMessage = <any>error
        );
  }

  addWordbank(name: string, words: string) {
    if (!name) { return; }
    words = words.replace(/(\r\n|\n|\r)/gm,"");
    let wordArray = words.split(",");

    wordArray.forEach( (item,index) => {
      item = item.trim();
      wordArray[index] = item;
    });

    this.wordbankService.create(name, wordArray)
       .subscribe(
         wordbank  => {
           this.wordbanks.push(wordbank),
           this.sortWordbanks()
         },
         error =>  this.errorMessage = <any>error
       );
  }

  deleteWordbank(name: string) {
    if (!name) { return; }

    let wb: Wordbank;
    this.wordbankService.getWordbank(name)
      .subscribe(
        wordbank  => wb = wordbank,
        error =>  this.errorMessage = <any>error
      );

    if (!wb) { return; }
    //TODO: delete call
    //TODO: remove wordbank from list of wordbanks
  }

  getWordbank(name: string) {
    if (!name) { return; }
    this.wordbankService.getWordbank(name)
      .subscribe(
        wordbank  => this.wordbanks.push(wordbank),
        error =>  this.errorMessage = <any>error
      );
  }

  sortWordbanks(){
    this.wordbanks.sort(
      (w1,w2) => {
          if (w1.name > w2.name){
            return 1;
          }
          if (w1.name < w2.name){
            return -1;
          }
          return 0;
      }
    )
  }

}
