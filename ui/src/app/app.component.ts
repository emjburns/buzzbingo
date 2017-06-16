import { Component, OnInit } from '@angular/core';
import { IdentityService }   from './identity/identity.service';


@Component({
  selector: 'my-app',
  styleUrls: [ './app.component.css' ],
  templateUrl: './app.component.html',
  providers: [IdentityService]
})
export class AppComponent implements OnInit {
  title = 'Buzzword Bingo';

  constructor(
    // private gameSearchService: GameSearchService,
    private identityService: IdentityService,
  ) {}

  ngOnInit(): void {
    //generate a unique ID for user/browser
    this.identityService.initializeIdentity();
  }


}
