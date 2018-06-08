import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router} from '@angular/router';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {IdService} from '../services/id.service';
import {MatchInfo} from '../models/MatchInfo';

@Component({
  selector: 'app-match',
  templateUrl: './match.component.html',
  styleUrls: ['./match.component.css']
})
export class MatchComponent implements OnInit {

  match: MatchInfo;

  constructor(private httpClient: HttpClient, private route: Router, private idService: IdService) {

    const id = this.idService.matchId;

    if (id === null) {
      alert('Invalid match id!');
      this.openHome();
    }

    this.httpClient.get<MatchInfo>('http://api2.tues.dreamix.eu:80/matchInfo/getMatchById',
      {params : {
          id : id
        }, observe: 'response'}).subscribe(response => this.match = response.body,
      error => alert('Error occurred try again later!'));
  }

  openHome() {
    this.route.navigate(['']);
  }

  openRanking() {
    this.route.navigate(['ranking']);
  }

  openArchive() {
    this.route.navigate(['archive']);
  }

  ngOnInit() {
  }

}
