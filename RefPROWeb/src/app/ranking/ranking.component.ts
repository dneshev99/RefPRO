import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-ranking',
  templateUrl: './ranking.component.html',
  styleUrls: ['./ranking.component.css']
})
export class RankingComponent implements OnInit {

  constructor(private route: Router) { }

  openHome() {
    this.route.navigate(['']);
  }

  openRanking() {
    this.route.navigate(['ranking']);
  }

  openArchive() {
    this.route.navigate(['archive']);
  }

  Name = "Alexander Alexandrov Verbovskiy";
  Mark = 9.8;
  Date = "18-06-1999";
  Height = "190";
  Weight = "77";
  Experience = "3";

  ngOnInit() {
  }

}
