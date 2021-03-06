import { NgModule, ErrorHandler }     from '@angular/core';
import { BrowserModule }              from '@angular/platform-browser';
import { FormsModule }                from '@angular/forms';
import { HttpModule, JsonpModule }    from '@angular/http';
import { CookieModule }               from 'ngx-cookie';

import { AppComponent }                 from './app.component';
import { DashboardComponent }           from './dashboard/dashboard.component';
import { GameSearchComponent }          from './game/game-search/game-search.component';
import { JoinGameComponent }            from './game/join-game/join-game.component';
import { WordbankDetailComponent }      from './wordbank/wordbank-detail.component';
import { WordbanksComponent }           from './wordbank/wordbanks.component';
import { GameboardComponent }           from './gameboard/gameboard.component';
import { NotFoundComponent }            from './not-found/not-found.component';
import { AlertComponent }               from './alert/alert.component';

import { NewGameComponent }   from './game/new-game/new-game.component';
import { GameService }        from './game/game.service';
import { GameboardService }   from './gameboard/gameboard.service';
import { WordbankService }    from './wordbank/wordbank.service';
import { IdentityService }    from './identity/identity.service';
import { AlertService }       from './alert/alert.service';

import { BuzzUtils }          from './buzzutils';
import { MyErrorHandler }     from './MyErrorHandler';

import { AppRoutingModule }   from './app-routing.module';



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
    GameboardComponent,
    NotFoundComponent,
    AlertComponent
  ],
  providers: [
    WordbankService,
    GameService,
    GameboardService,
    BuzzUtils,
    IdentityService,
    AlertService,
    {provide: ErrorHandler, useClass: MyErrorHandler}
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
