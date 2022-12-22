package com.vitorrubenich.med.dto;

import com.vitorrubenich.med.model.Doctor;
import com.vitorrubenich.med.model.Endereco;
import com.vitorrubenich.med.model.Especialidade;

public record DtoDoctorDet(Long id, String name, String email, String crm, Especialidade especialidade, Endereco endereco) {
    public DtoDoctorDet(Doctor doctor){
        this(doctor.getId(),doctor.getName(),doctor.getEmail(),doctor.getCrm(),doctor.getEspecialidade(),doctor.getEndereco());
    }
}
