import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router} from '@angular/router';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {IdService} from '../services/id.service';
import {Referee} from '../models/Referee';

@Component({
  selector: 'app-referee',
  templateUrl: './referee.component.html',
  styleUrls: ['./referee.component.css']
})
export class RefereeComponent implements OnInit {

  referee: Referee;

  id: string;

  mark: number;

  constructor(private httpClient: HttpClient, private route: Router, private idService: IdService) {

    this.mark = 0.0;

    this.id = this.idService.refereeId;

    if (this.id === null) {
      alert('Invalid referee id!');
      this.openHome();
    }


    this.httpClient.get<Referee>('http://api2.tues.dreamix.eu:80/referee/' + this.id,
      {observe: 'response'}).subscribe(response => this.referee = response.body,
      error => alert(error.toString()));
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

  sendMark() {
    alert(this.mark);
  }

  ngOnInit() {

  }

}
