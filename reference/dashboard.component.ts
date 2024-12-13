import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppointmentService } from '../../service/appointment.service';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  imports: [FormsModule,NgFor],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit{
constructor(private appointmentService:AppointmentService){}
  

  date:any
  name:any
  contact:any
  address:any
  addressLine1:any
  city:any
  doctor:any
  doctor_id:number=0
  patient_id:number=0

  ngOnInit(): void {
    this.appointmentService.getAllDoctor().subscribe({
      next:(data)=>{
        this.doctor=data

      },
      error:()=>{}
    })
  }
  
  book(){
   
   
    this.appointmentService.savePatient(
      {
      name:this.name,
      contact:this.contact,
      address:{
        addressLine1:this.addressLine1,
        city:this.city
      }
    }
    ).subscribe({
      next:(data)=>{
        this.patient_id=parseInt(data.id)

        this.appointmentService.saveAppointment(
          this.patient_id,
          this.doctor_id,{
            date:this.date
          }
         
        ).subscribe({
          next:(data)=>{
            console.log(data)
    
          },
          error:()=>{}
        })
      },
      error:()=>{}
    })



    
    
    
  }

}
