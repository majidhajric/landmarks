import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {LandmarkService} from "../../service/landmark.service";
import {PlaceService} from "../../service/place.service";
import {Country} from "../../model/country";
import {City} from "../../model/city";
import {Landmark} from "../../model/landmark";
import {LandmarkRequest} from "../../model/landmark-request";

@Component({
  selector: 'app-create-landmark',
  templateUrl: './create-landmark.component.html',
  styleUrls: ['./create-landmark.component.scss']
})
export class CreateLandmarkComponent implements OnInit {

  countries: Country[] = [];
  cities: City[] = [];
  files: File[] = [];

  form = new FormGroup({
    countryId: new FormControl('', [Validators.required]),
    cityId: new FormControl({value: '', disabled: true}, [Validators.required]),
    name: new FormControl('', [Validators.required, Validators.minLength(3)]),
    description: new FormControl('', [Validators.required]),
    importance: new FormControl('', [Validators.required]),
    file: new FormControl('', [Validators.required]),
    active: new FormControl(false)
  });

  constructor(private landmarkService: LandmarkService, private countryService: PlaceService) { }

  get f(){
    return this.form.controls;
  }

  ngOnInit(): void {
    this.countryService.getCountries().subscribe(countries => {
      this.countries = countries;
    })
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
      this.form.controls.cityId.enable();
    })
  }

  onSubmit(){
    this.landmarkService.createLandmark(this.form.value as LandmarkRequest, this.files).subscribe();
  }
}
