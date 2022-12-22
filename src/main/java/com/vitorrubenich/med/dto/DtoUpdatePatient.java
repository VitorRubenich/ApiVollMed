package com.vitorrubenich.med.dto;

import jakarta.validation.constraints.NotNull;

public record DtoUpdatePatient(
        @NotNull
        Long id,
        String name,
        String telefone,
        DtoEndereco dtoEndereco) {


}
