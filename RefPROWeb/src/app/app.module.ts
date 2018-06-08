import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import {HttpClient, HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { FormsModule } from '@angular/forms';



import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { RankingComponent } from './ranking/ranking.component';
import { ArchiveComponent } from './archive/archive.component';
import { RefereeComponent } from './referee/referee.component';
import { MatchComponent } from './match/match.component';
import { LoginComponent } from './login/login.component';
import { TokenInterceptorService } from './services/token-interceptor.service';
import { IdService } from './services/id.service';

export function tokenGetter() {
  return localStorage.getItem('AuthToken');
}

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
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptorService,
    multi: true
  }, IdService],
  bootstrap: [AppComponent],
})
export class AppModule {


}
