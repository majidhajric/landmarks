import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable, of} from "rxjs";
import {Landmark} from "../model/landmark";
import {catchError} from "rxjs/operators";
import {LandmarkRequest} from "../model/landmark-request";

@Injectable({
  providedIn: 'root'
})
export class LandmarkService {

  readonly API_URL = environment.apiServer + '/landmarks';

  constructor(private httpClient: HttpClient) { }

  public getLandmarks(name: string, importance: string, active= true): Observable<Landmark[]> {
    let params = new HttpParams();
    if(name) {
      params= params.set('name', name);
    }
    if(importance){
      params= params.set('importance', importance);
    }
    params= params.set('active', active);

    return this.httpClient.get<Landmark[]>(this.API_URL,{ params}).pipe(
        catchError(err => of([]))
    );
  }

  public createLandmark(data: LandmarkRequest, files: File[]) {
    const formData = new FormData();

    // add the files
    if (files && files.length) {
      files.forEach(file => formData.append('file', file));
    }

    // add the data object
    formData.append('landmark', new Blob([JSON.stringify(data)], {type: 'application/json'}));

    return this.httpClient.post(this.API_URL, formData);
  }

  public updateLandmark(id: string, data: LandmarkRequest, files: File[]) {
    const formData = new FormData();

    // add the files
    if (files && files.length) {
      files.forEach(file => formData.append('file', file));
    }

    // add the data object
    formData.append('landmark', new Blob([JSON.stringify(data)], {type: 'application/json'}));

    return this.httpClient.put(this.API_URL + '/' + id, formData);
  }

  public getLandmark(id: string): Observable<Landmark> {
    return this.httpClient.get<Landmark>(this.API_URL + '/' + id);
  }

  public deleteLandmark(id: string) {
    return this.httpClient.delete(this.API_URL + '/' + id);
  }
}
