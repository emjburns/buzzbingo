import { NgModule, ErrorHandler }       from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpModule, JsonpModule }    from '@angular/http';

import { AppComponent }         from './app.component';
import { DashboardComponent }   from './dashboard/dashboard.component';
import { GameSearchComponent }   from './game/game-search/game-search.component';
import { JoinGameComponent }    from './game/join-game/join-game.component';
import { UsernameComponent }   from './username/username.component';
import { WordbankDetailComponent }      from './wordbank/wordbank-detail.component';
import { WordbanksComponent }           from './wordbank/wordbanks.component';
import { GameboardComponent }           from './gameboard/gameboard.component';

import { NewGameComponent }   from './game/new-game/new-game.component';
import { GameService }        from './game/game.service';
import { GameboardService }   from './gameboard/gameboard.service';
import { WordbankService }    from './wordbank/wordbank.service';
import { IdentityService }    from './identity/identity.service';

import { BuzzUtils }          from './buzzutils';

import { AppRoutingModule }   from './app-routing.module';


import { CookieModule } from 'ngx-cookie';

import { MyErrorHandler } from './MyErrorHandler';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    JsonpModule,
    AppRoutingModule,
    CookieModule.forRoot()
  ],
  declarations: [
    AppComponent,
    DashboardComponent,
    GameSearchComponent,
    WordbankDetailComponent,
    WordbanksComponent,
    JoinGameComponent,
    NewGameComponent,
    UsernameComponent,
    GameboardComponent
  ],
  providers: [
    WordbankService,
    GameService,
    GameboardService,
    BuzzUtils,
    IdentityService,
    {provide: ErrorHandler, useClass: MyErrorHandler}
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
