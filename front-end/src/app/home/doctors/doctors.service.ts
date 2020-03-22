import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Doctor } from 'src/app/shared/models';
import { Observable } from 'rxjs';
import { config } from 'src/app/shared/config';

@Injectable({
  providedIn: 'root'
})
export class DoctorsService {

  constructor(
    private http: HttpClient
  ) { }

  public save(doctor: Doctor): Observable<Doctor> {
    return this.http.post<any>(`${config.apiUrl}/rest/doctor/`, doctor);
  }

  public findAll(): Observable<Doctor[]> {
    return this.http.get<Doctor[]>(`${config.apiUrl}/rest/doctor/`);
  }
}
