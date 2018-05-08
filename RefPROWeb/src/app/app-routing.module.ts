import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ActivatedRoute, Router} from '@angular/router';

import {HomeComponent} from './home/home.component';
import {RankingComponent} from './ranking/ranking.component';
import {ArchiveComponent} from './archive/archive.component';
import {RefereeComponent} from './referee/referee.component';
import {MatchComponent} from './match/match.component';

const routes: Routes = [

  {
    path: 'ranking',
    component: RankingComponent,
  },

  {
    path: 'archive',
    component: ArchiveComponent,
  },

  {
    path: '',
    component: HomeComponent,
  },

  {
    path: 'referee',
    component: RefereeComponent,
  },

  {
    path: 'match',
    component: MatchComponent,
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
