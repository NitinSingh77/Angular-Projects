import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  constructor(private httpClient:HttpClient) { }

  getAllDoctorApi='http://localhost:8081/api/doctor/all'
  savePatientApi='http://localhost:8081/api/patient/add'
  saveAppointmentApi='http://localhost:8081/api/patient/appointment/add'

  getAllDoctor():Observable<any>
  {
    return this.httpClient.get(this.getAllDoctorApi);
  }

  savePatient(obj:any):Observable<any>{
    return this.httpClient.post(this.savePatientApi,obj)
  }

  saveAppointment(patient_id:number,doctor_id:number,obj:any):Observable<any>{
    return this.httpClient.post(this.saveAppointmentApi+"?patient_id="+patient_id+"&doctor_id="+doctor_id,obj)
  }


}
