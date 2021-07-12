import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {of, Subscription} from "rxjs";
import {LandmarkService} from "../../service/landmark.service";
import {Landmark} from "../../model/landmark";
import {concatMap} from "rxjs/operators";
import {Country} from "../../model/country";
import {City} from "../../model/city";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {PlaceService} from "../../service/place.service";
import {LandmarkRequest} from "../../model/landmark-request";
import {Location} from '@angular/common';

@Component({
  selector: 'app-edit-landmark',
  templateUrl: './edit-landmark.component.html',
  styleUrls: ['./edit-landmark.component.scss']
})
export class EditLandmarkComponent implements OnInit, OnDestroy {

  subscription: Subscription;
  landmarkId: string;
  landmark: Landmark;
  countries: Country[] = [];
  cities: City[] = [];
  files: File[] = [];

  form = new FormGroup({
    countryId: new FormControl('', [Validators.required]),
    cityId: new FormControl( '', [Validators.required]),
    name: new FormControl('', [Validators.required, Validators.minLength(3)]),
    description: new FormControl('', [Validators.required]),
    importance: new FormControl('', [Validators.required]),
    file: new FormControl(''),
    active: new FormControl(false)
  });

  constructor(private route: ActivatedRoute, private landmarkService: LandmarkService, private countryService: PlaceService, private location: Location) { }

  ngOnInit(): void {
    this.subscription = this.route.params.pipe(
      concatMap((params: Params) => of(params['id'])),
      concatMap((id:string) => {
        this.landmarkId= id;
        return this.landmarkService.getLandmark(id);
      }),
    ).subscribe((landmark: Landmark) => {
      this.countryService.getCities(landmark.countryId).subscribe(cities => {
        this.cities = cities;
      });
      this.form.patchValue(landmark);
    });

    this.countryService.getCountries().subscribe(countries => {
      this.countries = countries;
    })
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
  get f(){
    return this.form.controls;
  }

  onFileChange(event: any) {
    for (let i = 0; i < event.target.files.length; i++) {
      this.files.push(event.target.files[i]);
    }
  }

  changeCountry(event: any) {
    const countryId = event.target.value;
    this.countryService.getCities(countryId).subscribe(cities => {
      this.cities = cities;
      this.form.controls.cityId.patchValue(this.cities[0].id);
    })
  }

  onSubmit(){
    this.landmarkService.updateLandmark(this.landmarkId, this.form.value as LandmarkRequest, this.files)
      .subscribe(value => this.form.markAsPristine());
  }

  backClicked() {
    this.location.back();
  }
}
