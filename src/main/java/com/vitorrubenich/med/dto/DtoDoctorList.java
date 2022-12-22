package com.vitorrubenich.med.dto;

import com.vitorrubenich.med.model.Doctor;
import com.vitorrubenich.med.model.Especialidade;

public record DtoDoctorList(Long id, String name,String email, String crm, Especialidade especialidade) {

	public DtoDoctorList(Doctor doctor) {
		this(doctor.getId(),doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getEspecialidade());
	}
}
