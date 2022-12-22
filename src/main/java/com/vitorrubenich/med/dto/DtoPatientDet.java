package com.vitorrubenich.med.dto;

import com.vitorrubenich.med.model.Endereco;
import com.vitorrubenich.med.model.Patient;

public record DtoPatientDet(Long id, String name, String telefone, Endereco endereco) {
    public DtoPatientDet(Patient patient){
        this(patient.getId(), patient.getName(), patient.getTelefone(), patient.getEndereco());
    }
}
