import {Component, Input, OnInit} from '@angular/core';
import {Landmark} from "../../model/landmark";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {VoteDialogComponent} from "../vote-dialog/vote-dialog.component";

@Component({
  selector: 'app-landmark-item',
  templateUrl: './landmark-item.component.html',
  styleUrls: ['./landmark-item.component.scss']
})
export class LandmarkItemComponent implements OnInit {

  @Input() landmark: Landmark;

  constructor(private modalService: NgbModal) { }

  ngOnInit(): void {
  }

  openVoteDialog() {
    const modalRef = this.modalService.open(VoteDialogComponent);
    modalRef.componentInstance.landmarkId = this.landmark.id;
  }

}
