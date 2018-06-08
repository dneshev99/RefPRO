import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router} from '@angular/router';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {MatchInfo} from '../models/MatchInfo';
import {IdService} from '../services/id.service';


@Component({
  selector: 'app-archive',
  templateUrl: './archive.component.html',
  styleUrls: ['./archive.component.css']
})
export class ArchiveComponent implements OnInit {

  data: MatchInfo[];

  constructor(private httpClient: HttpClient, private route: Router, private idService: IdService) {

    this.httpClient.get<MatchInfo[]>('http://api2.tues.dreamix.eu:80/matchInfo/get',
      {observe: 'response'}).subscribe(response => this.data = response.body,
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

  openMatchInfo(id: string) {
    this.idService.matchId = id;
    this.route.navigate(['match']);
  }


  ngOnInit() {

  }

}
