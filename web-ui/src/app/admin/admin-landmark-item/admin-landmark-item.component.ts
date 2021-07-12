import {Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import {Landmark} from "../../model/landmark";

@Component({
  selector: 'app-admin-landmark-item',
  templateUrl: './admin-landmark-item.component.html',
  styleUrls: ['./admin-landmark-item.component.scss']
})
export class AdminLandmarkItemComponent implements OnInit {

  @Input() landmark: Landmark;
  @Output() deleteItemEvent = new EventEmitter<string>();
  @Output() editItemEvent = new EventEmitter<string>();

  constructor() { }

  ngOnInit(): void {
  }

  deleteLandmark() {
    this.deleteItemEvent.emit(this.landmark.id);
  }

  editLandmark() {
    this.editItemEvent.emit(this.landmark.id);
  }
}
