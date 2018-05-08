import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';



import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { RankingComponent } from './ranking/ranking.component';
import { ArchiveComponent } from './archive/archive.component';
import { RefereeComponent } from './referee/referee.component';
import { MatchComponent } from './match/match.component';
import { LoginComponent } from './login/login.component';



@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RankingComponent,
    ArchiveComponent,
    RefereeComponent,
    MatchComponent,
    LoginComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {


}
