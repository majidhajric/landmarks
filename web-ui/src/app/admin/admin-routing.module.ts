import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ManageLandmarksComponent} from "./manage-landmarks/manage-landmarks.component";

const routes: Routes = [
  {
    path: '', component: ManageLandmarksComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
