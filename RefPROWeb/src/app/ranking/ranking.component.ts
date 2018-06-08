import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router} from '@angular/router';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Referee} from '../models/Referee';
import {IdService} from '../services/id.service';

@Component({
  selector: 'app-ranking',
  templateUrl: './ranking.component.html',
  styleUrls: ['./ranking.component.css']
})
export class RankingComponent implements OnInit {

  referees: Referee[];

  constructor(private httpClient: HttpClient, private route: Router, private idService: IdService) {
    this.httpClient.get<Referee[]>('http://api2.tues.dreamix.eu:80/referee/',
      {observe: 'response'}).subscribe(response => this.referees = response.body,
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

  openReferee(id: string) {
    this.idService.refereeId = id;
    this.route.navigate(['referee']);
  }

  ngOnInit() {
  }

}
