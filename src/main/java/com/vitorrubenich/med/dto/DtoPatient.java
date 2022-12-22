package com.vitorrubenich.med.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DtoPatient(
		@NotBlank
		String name,
		@Email
		String email,
		@Pattern(regexp = "\\d{11}")
		String cpf,
		@Pattern(regexp = "\\d{11}")
		String telefone,
		@Valid
		@NotNull
		DtoEndereco endereco,
		Boolean ativo) {

}
