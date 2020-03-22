import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Appointments } from 'src/app/shared/models/appointments.model';
import { Observable } from 'rxjs';
import { config } from 'src/app/shared/config';

@Injectable({
  providedIn: 'root'
})
export class AppointmentsService {

  constructor(
    private http: HttpClient
  ) { }

  public save(appointments: Appointments): Observable<Appointments> {
    return this.http.post<any>(`${config.apiUrl}/rest/appointment/`, appointments);
  }

  public findAll(): Observable<Appointments[]> {
    return this.http.get<Appointments[]>(`${config.apiUrl}/rest/appointment/`);
  }
}
