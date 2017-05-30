import { NgModule, ErrorHandler }       from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpModule, JsonpModule }    from '@angular/http';

import { AppComponent }         from './app.component';
import { DashboardComponent }   from './dashboard.component';
import { GameSearchComponent }   from './game-search.component';
import { JoinGameComponent }    from './join-game.component';
import { GameComponent }    from './game.component';
import { NewGameComponent }    from './new-game.component';
import { UsernameComponent }   from './username.component';
import { WordbankDetailComponent }      from './wordbank-detail.component';
import { WordbanksComponent }           from './wordbanks.component';
import { GameboardComponent }           from './gameboard.component';


import { GameService }          from './game.service';
import { GameboardService }          from './gameboard.service';
import { ApiWordbankService } from './api-wordbank.service';
import { BuzzUtils }          from './buzzutils'

import { AppRoutingModule }     from './app-routing.module';


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
    GameComponent,
    NewGameComponent,
    UsernameComponent,
    GameboardComponent
  ],
  providers: [
    ApiWordbankService,
    GameService,
    GameboardService,
    BuzzUtils,
    {provide: ErrorHandler, useClass: MyErrorHandler}
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
