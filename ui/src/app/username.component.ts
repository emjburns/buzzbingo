import { Component, OnInit } from '@angular/core';
import { Router }            from '@angular/router';
import { Observable }        from 'rxjs/Observable';
import { Subject }           from 'rxjs/Subject';

import { BuzzUtils }     from './buzzutils'

import { CookieService } from 'ngx-cookie';

// Observable class extensions
// import 'rxjs/add/observable/of';

// Observable operators
// import 'rxjs/add/operator/catch';
// import 'rxjs/add/operator/debounceTime';
// import 'rxjs/add/operator/distinctUntilChanged';

@Component({
  selector: 'set-username',
  templateUrl: './username.component.html',
  styleUrls: [ './username.component.css' ],
  providers: [BuzzUtils]
})
export class UsernameComponent implements OnInit {
  playerUsername: string;
  hasUsername: boolean = false;

  constructor(
    // private gameSearchService: GameSearchService,
    private cookieService: CookieService,
    private router: Router,
    private buzzutils: BuzzUtils
  ) {}
  // Push a search term into the observable stream.

  ngOnInit(): void {
    this.playerUsername = this.getUsername();
    if (this.playerUsername != '') {
      this.hasUsername = true;
    }
  }

  setUsername(username: string): void {
    if (!username) { return; }
    this.cookieService.put(this.buzzutils.cookieKey, username);
    this.playerUsername = username
    this.hasUsername = true;
  }

  unsetUsername(): void {
    this.cookieService.remove(this.buzzutils.cookieKey)
    this.hasUsername = false
    this.playerUsername = ''
  }

  getUsername(): string {
    return this.cookieService.get(this.buzzutils.cookieKey)
  }


}
