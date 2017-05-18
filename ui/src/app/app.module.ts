import { NgModule }       from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpModule, JsonpModule }    from '@angular/http';

import { AppComponent }         from './app.component';
import { DashboardComponent }   from './dashboard.component';
import { HeroDetailComponent }  from './hero-detail.component';
import { HeroesComponent }      from './heroes.component';
import { HeroService }          from './hero.service';
import { GameSearchComponent}   from './game-search.component'

import { WordbankService }              from './wordbank.service';
import { WordbankDetailComponent }      from './wordbank-detail.component';
import { WordbanksComponent }           from './wordbanks.component';

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
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    DashboardComponent,
    HeroDetailComponent,
    HeroesComponent,
    GameSearchComponent,
    WordbankDetailComponent,
    WordbanksComponent
  ],
  providers: [
    HeroService,
    WordbankService,
    ApiWordbankService
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
