import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {catchError} from "rxjs/operators";
import {Country} from "../model/country";
import {City} from "../model/city";

@Injectable({
  providedIn: 'root'
})
export class PlaceService {

  readonly API_URL = environment.apiServer;

  constructor(private httpClient: HttpClient) {
  }

  public getCountries(): Observable<Country[]> {
    return this.httpClient.get<Country[]>(this.API_URL + '/countries')
      .pipe(
        catchError(err => of([]))
      );
  }

  public getCities(countryId: string): Observable<City[]> {
    return this.httpClient.get<City[]>(this.API_URL + '/countries/' + countryId + '/cities')
      .pipe(
        catchError(err => of([]))
      );
  }
}
