import { Component, OnInit } from '@angular/core';
import {Landmark} from "../../model/landmark";
import {LandmarkService} from "../../service/landmark.service";

@Component({
  selector: 'app-landmark-list',
  templateUrl: './landmark-list.component.html',
  styleUrls: ['./landmark-list.component.scss']
})
export class LandmarkListComponent implements OnInit {

  landmarks: Landmark[] = [];
  searchName: string= '';
  searchImportance: string= '';

  constructor(private landmarkService: LandmarkService) { }

  ngOnInit(): void {
    this.getLandmarks();
  }

  getLandmarks() {
    this.landmarkService.getLandmarks(this.searchName, this.searchImportance)
      .subscribe((data: Landmark[]) => {
        this.landmarks= data;
      });
  }
}
