import 'rxjs/add/operator/switchMap';
import { Component, OnInit }      from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Location }               from '@angular/common';

import { Wordbank }        from './wordbank';
import { ApiWordbankService} from './api-wordbank.service';
// Observable class extensions
import 'rxjs/add/observable/of';

// Observable operators
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';

@Component({
  selector: 'my-wordbank-detail',
  templateUrl: './wordbank-detail.component.html',
  styleUrls: [ './wordbank-detail.component.css' ]
})
export class WordbankDetailComponent implements OnInit {
  wordbank: Wordbank;
  mode = 'Observable';

  constructor(
    private apiWordbankService: ApiWordbankService,
    private route: ActivatedRoute,
    private location: Location,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.params
      .switchMap((params: Params) => this.apiWordbankService.getWordbank(params['name']))
      .subscribe(wordbank => this.wordbank = wordbank);
  }

  deleteWord(name: string, word: string) {
    let words: string[] = [word]
    this.apiWordbankService.deleteWord(name, words)
      .subscribe(wordbank => this.wordbank = wordbank);
  }

  addWord(name: string, word: string) {
    let words: string[] = word.split(",")
    this.apiWordbankService.addWord(name, words)
      .subscribe(wordbank => this.wordbank = wordbank);
  }

   deleteWordbank(name: string) {
    this.apiWordbankService.delete(name)
        .subscribe(
          // must delete wordbank
        );
        // this.router.navigate(["wordbanks"]);

        //TODO: have this trigger an update of the
        //word list, so the deleted list doesn't
        //appear in the list of all wordbanks
        //return you to previous place
        this.goBack();
  }

  goBack(): void {
    this.location.back();
  }
}
