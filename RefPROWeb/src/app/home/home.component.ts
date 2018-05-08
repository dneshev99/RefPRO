import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private route: Router) { }

  static isLoginVisible: boolean;

  openHome() {
    this.route.navigate(['']);
  }

  openRanking() {
    this.route.navigate(['ranking']);
  }

  openArchive() {
    this.route.navigate(['archive']);
  }

  openLogin() {
    HomeComponent.isLoginVisible = !HomeComponent.isLoginVisible;
  }

  ngOnInit() {
    HomeComponent.isLoginVisible = false;
  }

  isOpen() {
    return HomeComponent.isLoginVisible;
  }

}
