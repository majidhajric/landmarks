import { Component, OnInit } from '@angular/core';
import {LandmarkService} from "../../service/landmark.service";
import {Landmark} from "../../model/landmark";

@Component({
  selector: 'app-admin-landmark-list',
  templateUrl: './admin-landmark-list.component.html',
  styleUrls: ['./admin-landmark-list.component.scss']
})
export class AdminLandmarkListComponent implements OnInit {

  landmarks: Landmark[] = [];

  constructor(private landmarkService: LandmarkService) { }

  ngOnInit(): void {
    this.getLandmarks();
  }

  getLandmarks() {
    this.landmarkService.getLandmarks('', '', true)
      .subscribe((data: Landmark[]) => {
        this.landmarks= data;
      });
  }

  deleteLandmark(id: string) {
    this.landmarkService.deleteLandmark(id).subscribe( next =>
      this.landmarks.splice(this.landmarks.findIndex(item => item.id === id), 1)
    );
  }
}
