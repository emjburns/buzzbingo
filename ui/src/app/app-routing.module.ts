import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent }   from './dashboard.component';
import { WordbanksComponent }   from './wordbanks.component';
import { WordbankDetailComponent }   from './wordbank-detail.component';
import { GameComponent }        from './game.component';
import { NewGameComponent }       from './new-game.component';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard',  component: DashboardComponent },
  { path: 'wordbanks/:name', component: WordbankDetailComponent },
  { path: 'wordbanks',  component: WordbanksComponent },
  // { path: 'game/:name',  component: GameComponent },
  { path: 'game',  component: GameComponent },
  { path: 'newgame',  component: NewGameComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
