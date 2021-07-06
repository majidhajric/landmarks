import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {LandmarkItemComponent} from "./home/landmark-item/landmark-item.component";
import {LandmarkListComponent} from "./home/landmark-list/landmark-list.component";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { VoteDialogComponent } from './home/vote-dialog/vote-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LandmarkItemComponent,
    LandmarkListComponent,
    VoteDialogComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        HttpClientModule,
        FormsModule,
        NgbModule,
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
