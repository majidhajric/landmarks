import { Component, OnInit } from '@angular/core';
import {LandmarkService} from "../../service/landmark.service";
import {Landmark} from "../../model/landmark";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-admin-landmark-list',
  templateUrl: './admin-landmark-list.component.html',
  styleUrls: ['./admin-landmark-list.component.scss']
})
export class AdminLandmarkListComponent implements OnInit {

  landmarks: Landmark[] = [];
  showActive: boolean;

  constructor(private landmarkService: LandmarkService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.data.subscribe(v => {
      this.showActive = v['active']
      this.getLandmarks();
    });
  }

  getLandmarks() {
    this.landmarkService.getLandmarks('', '', this.showActive)
      .subscribe((data: Landmark[]) => {
        this.landmarks= data;
      });
  }

  deleteLandmark(id: string) {
    this.landmarkService.deleteLandmark(id).subscribe( next =>
      this.getLandmarks()
    );
  }

  editLandmark(id: string) {
    this.router.navigate(['/admin','edit',id]);
  }
}
