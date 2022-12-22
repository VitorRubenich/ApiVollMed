package com.vitorrubenich.med.dto;

import com.vitorrubenich.med.model.Patient;

public record DtoPatientList(Long id,String name, String email, String cpf) {
	public DtoPatientList(Patient patient) {
		this(patient.getId(),patient.getName(), patient.getEmail(), patient.getCpf());
	}
}
