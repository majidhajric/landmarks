import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { CreateUpdateLandmarkComponent } from './create-update-landmark/create-update-landmark.component';
import { ManageLandmarksComponent } from './manage-landmarks/manage-landmarks.component';


@NgModule({
  declarations: [
    CreateUpdateLandmarkComponent,
    ManageLandmarksComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }
