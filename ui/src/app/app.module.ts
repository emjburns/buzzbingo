import { NgModule }       from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpModule, JsonpModule }    from '@angular/http';

import { AppComponent }         from './app.component';
import { DashboardComponent }   from './dashboard.component';
import { GameSearchComponent }   from './game-search.component'
import { JoinGameComponent }    from './join-game.component'
import { GameComponent }    from './game.component'
import { NewGameComponent }    from './new-game.component'

import { WordbankService }              from './wordbank.service';
import { WordbankDetailComponent }      from './wordbank-detail.component';
import { WordbanksComponent }           from './wordbanks.component';

import { GameService }          from './game.service';

import { AppRoutingModule }     from './app-routing.module';

// Imports for loading & configuring the in-memory web api
import { InMemoryWebApiModule } from 'angular-in-memory-web-api';
// import { InMemoryDataService }  from './in-memory-data.service';
// import { InMemoryWordbankService } from './in-memory-wordbank.service'
import { ApiWordbankService } from './api-wordbank.service'

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    JsonpModule,
    // InMemoryWebApiModule.forRoot(InMemoryDataService),
    // InMemoryWebApiModule.forRoot(InMemoryWordbankService),
    // InMemoryWebApiModule.forRoot(ApiWordbankService),
    AppRoutingModule,
  ],
  declarations: [
    AppComponent,
    DashboardComponent,
    GameSearchComponent,
    WordbankDetailComponent,
    WordbanksComponent,
    JoinGameComponent,
    GameComponent,
    NewGameComponent
  ],
  providers: [
    WordbankService,
    ApiWordbankService,
    GameService
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
