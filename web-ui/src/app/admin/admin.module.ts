import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminLayoutComponent } from './admin-layout/admin-layout.component';
import {MatSidenavModule} from "@angular/material/sidenav";
import { AdminLandmarkListComponent } from './admin-landmark-list/admin-landmark-list.component';
import { CreateLandmarkComponent } from './create-landmark/create-landmark.component';
import { EditLandmarkComponent } from './edit-landmark/edit-landmark.component';
import {ReactiveFormsModule} from "@angular/forms";
import { AdminLandmarkItemComponent } from './admin-landmark-item/admin-landmark-item.component';
import {EllipsisModule} from "ngx-ellipsis";


@NgModule({
  declarations: [
    AdminLayoutComponent,
    AdminLandmarkListComponent,
    CreateLandmarkComponent,
    EditLandmarkComponent,
    AdminLandmarkItemComponent
  ],
    imports: [
        CommonModule,
        AdminRoutingModule,
        MatSidenavModule,
        ReactiveFormsModule,
        EllipsisModule
    ]
})
export class AdminModule { }
