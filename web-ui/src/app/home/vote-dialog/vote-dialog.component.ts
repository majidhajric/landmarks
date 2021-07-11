import {Component, Input, OnInit} from '@angular/core';
import {NgbActiveModal, NgbRatingConfig} from "@ng-bootstrap/ng-bootstrap";
import {FormControl, Validators} from "@angular/forms";

@Component({
  selector: 'app-vote-dialog',
  templateUrl: './vote-dialog.component.html',
  styleUrls: ['./vote-dialog.component.scss'],
  providers: [NgbRatingConfig]
})
export class VoteDialogComponent implements OnInit {

  @Input() landmarkId: string;
  ctrl = new FormControl(null, Validators.required);

  constructor(public activeModal: NgbActiveModal, config: NgbRatingConfig) {
    config.max = 5;
  }

  ngOnInit(): void {
  }

  vote() {
    this.activeModal.close();
  }
}
