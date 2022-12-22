package com.vitorrubenich.med.dto;


import com.vitorrubenich.med.model.Especialidade;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DtoDoctor(
		@NotBlank
		String name,
		@Email
		String email,
		@NotBlank
		@Pattern(regexp = "\\d{4,6}")
		String crm,
		@NotBlank
		String telefone,
		@NotNull
		Especialidade especialidade,
		@NotNull
		@Valid
		DtoEndereco endereco,
		Boolean ativo
		) {

}
