import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {Landmark} from "../model/landmark";

@Injectable({
  providedIn: 'root'
})
export class LandmarkService {

  readonly API_URL = environment.apiServer + '/landmarks';

  constructor(private httpClient: HttpClient) { }

  public getLandmarks(name: string, importance: string, active= true): Observable<Landmark[]> {
    return this.httpClient.get<Landmark[]>(this.API_URL,
      {
        params: new HttpParams()
          .set('name', name)
          .set('importance', importance)
      });
  }
}
