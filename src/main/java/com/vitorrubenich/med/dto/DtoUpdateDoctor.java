package com.vitorrubenich.med.dto;

import jakarta.validation.constraints.NotNull;

public record DtoUpdateDoctor(@NotNull Long id, String telefone, String name, DtoEndereco dadosEndereco) {

}
