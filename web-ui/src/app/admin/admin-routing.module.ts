import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AdminLayoutComponent} from "./admin-layout/admin-layout.component";
import {AdminLandmarkListComponent} from "./admin-landmark-list/admin-landmark-list.component";
import {CreateLandmarkComponent} from "./create-landmark/create-landmark.component";
import {EditLandmarkComponent} from "./edit-landmark/edit-landmark.component";

const routes: Routes = [
  {
    path: '', component: AdminLayoutComponent,
    children: [
      {
        path: '', redirectTo: 'active', pathMatch: 'full'
      },
      {
        path:'active', component: AdminLandmarkListComponent
      },
      {
        path:'inactive', component: AdminLandmarkListComponent
      },
      {
        path: 'create', component: CreateLandmarkComponent
      },
      {
        path: 'edit/:id', component: EditLandmarkComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
