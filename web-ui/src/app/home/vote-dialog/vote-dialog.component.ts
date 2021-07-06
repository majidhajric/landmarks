import {Component, Input, OnInit} from '@angular/core';
import {NgbActiveModal, NgbRatingConfig} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-vote-dialog',
  templateUrl: './vote-dialog.component.html',
  styleUrls: ['./vote-dialog.component.scss'],
  providers: [NgbRatingConfig]
})
export class VoteDialogComponent implements OnInit {

  @Input() landmarkId: string;
  rate: number;

  constructor(public activeModal: NgbActiveModal, config: NgbRatingConfig) {
    config.max = 5;
  }

  ngOnInit(): void {
  }

  vote() {
    this.activeModal.close();
  }
}
