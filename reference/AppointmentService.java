package com.springboot.ExampleCaseStudy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.ExampleCaseStudy.exception.ResourceNotFoundException;
import com.springboot.ExampleCaseStudy.model.Address;
import com.springboot.ExampleCaseStudy.model.Doctor;
import com.springboot.ExampleCaseStudy.model.Patient;
import com.springboot.ExampleCaseStudy.model.PatientDoctorAppointment;
import com.springboot.ExampleCaseStudy.repository.AddressRepository;
import com.springboot.ExampleCaseStudy.repository.DoctorRepository;
import com.springboot.ExampleCaseStudy.repository.PatientDoctorAppointmentRepository;
import com.springboot.ExampleCaseStudy.repository.PatientRepository;
@Service
public class AppointmentService {
	@Autowired
	private PatientRepository   patientRepository;
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private PatientDoctorAppointmentRepository patientDoctorAppointmentRepository;
	
	
	public Patient insertPatient(Patient patient) {
		Address address=patient.getAddress();
		address=addressRepository.save(address);
		
	    patient.setAddress(address);
		return patientRepository.save(patient);
		
		
		}


	public Doctor insertDoctor(Doctor doctor) {
		Address address=doctor.getAddress();
		address=addressRepository.save(address);
		
	    doctor.setAddress(address);
		return doctorRepository.save(doctor);
	
	}
	
	public Patient validatePatient(int patient_id) throws ResourceNotFoundException {
		Optional<Patient> optional=patientRepository.findById(patient_id);
		if(optional.isEmpty())
		{
			throw new ResourceNotFoundException("Invalid id");
		}
		
		Patient patient=optional.get();
		return patient;
	}
	
	public Doctor validateDoctor(int doctor_id) throws ResourceNotFoundException {
		Optional<Doctor> optional=doctorRepository.findById(doctor_id);
		if(optional.isEmpty())
		{
			throw new ResourceNotFoundException("Invalid id");
		}
		
		Doctor doctor=optional.get();
		return doctor;
	}


	public PatientDoctorAppointment insertAppointment(int patient_id, int doctor_id,
			PatientDoctorAppointment patientDoctorAppointment) throws ResourceNotFoundException {
		
		Patient patient=validatePatient(patient_id);
		Doctor doctor=validateDoctor(doctor_id);
		
		patientDoctorAppointment.setPatient(patient);
		patientDoctorAppointment.setDoctor(doctor);
		
		return patientDoctorAppointmentRepository.save(patientDoctorAppointment);
	}


	public List<Doctor> getAll() {
		return doctorRepository.findAll();
	}

}
