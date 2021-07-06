import {Component, Input, OnInit} from '@angular/core';
import {Landmark} from "../../model/landmark";

@Component({
  selector: 'app-landmark-item',
  templateUrl: './landmark-item.component.html',
  styleUrls: ['./landmark-item.component.scss']
})
export class LandmarkItemComponent implements OnInit {

  @Input() landmark: Landmark;

  constructor() { }

  ngOnInit(): void {
  }

}
