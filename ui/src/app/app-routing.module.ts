import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent }   from './dashboard/dashboard.component';
import { WordbanksComponent }   from './wordbank/wordbanks.component';
import { WordbankDetailComponent }   from './wordbank/wordbank-detail.component';
import { GameboardComponent }        from './gameboard/gameboard.component';
import { NewGameComponent }       from './game/new-game/new-game.component';
import { NotFoundComponent }       from './not-found/not-found.component';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard',  component: DashboardComponent },
  { path: 'wordbanks/:name', component: WordbankDetailComponent },
  { path: 'wordbanks',  component: WordbanksComponent },
  { path: 'gameboard/:gameName',  component: GameboardComponent },
  { path: 'newgame',  component: NewGameComponent },
  { path: '**',  component: NotFoundComponent },
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
