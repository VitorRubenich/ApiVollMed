package com.vitorrubenich.med.dto;

import com.vitorrubenich.med.model.Patient;

public record DtoPatientList(String name, String email, String cpf) {
	public DtoPatientList(Patient patient) {
		this(patient.getName(), patient.getEmail(), patient.getCpf());
	}
}
