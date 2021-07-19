import {Component, OnDestroy, OnInit} from '@angular/core';
import {Landmark} from "../model/landmark";
import {LandmarkService} from "../service/landmark.service";
import {AuthService} from "../service/auth.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnDestroy {

  isAuthenticated: boolean;
  sub: Subscription;

  constructor(private authService: AuthService) {
    this.sub = this.authService.isAuthenticated().subscribe(value => this.isAuthenticated = value);
  }

  logIn(): void {
    this.authService.logIn();
  }

  logOut(): void {
    this.authService.logOut();
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

}
