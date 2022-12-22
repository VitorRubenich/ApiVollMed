package com.vitorrubenich.med.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitorrubenich.med.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{

}
