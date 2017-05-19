import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent }   from './dashboard.component';
import { WordbanksComponent }   from './wordbanks.component';
import { WordbankDetailComponent }   from './wordbank-detail.component';

const routes: Routes = [
  { path: '', redirectTo: '/wordbanks', pathMatch: 'full' },
  { path: 'dashboard',  component: DashboardComponent },
  { path: 'wordbanks/:name', component: WordbankDetailComponent },
  { path: 'wordbanks',  component: WordbanksComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
