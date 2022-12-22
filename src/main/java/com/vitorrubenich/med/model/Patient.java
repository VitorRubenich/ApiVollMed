package com.vitorrubenich.med.model;

import com.vitorrubenich.med.dto.DtoPatient;

import com.vitorrubenich.med.dto.DtoUpdatePatient;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "patients")
@Entity(name = "Patient")
@Data
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "patient_name")
	private String name;

	private String email;

	private String telefone;

	private String cpf;
	@Embedded
	private Endereco endereco;

	@Column(name = "active")
	private Boolean ativo;

	public Patient() {}

	public Patient(DtoPatient dtoPatient) {
		this.cpf = dtoPatient.cpf();
		this.name = dtoPatient.name();
		this.email = dtoPatient.email();
		this.telefone = dtoPatient.telefone();
		this.endereco = new Endereco(dtoPatient.endereco());

		if(dtoPatient.ativo() == null){
			this.ativo = true;
		}else{
			this.ativo = dtoPatient.ativo();
		}
	}

    public void inativar() {
		this.ativo = false;
    }

	public void reativar(){
		this.ativo = true;
	}
	public void updatePatient(DtoUpdatePatient dtoUpdatePatient) {
		if(dtoUpdatePatient.telefone() != null){
			this.telefone = dtoUpdatePatient.telefone();
		}
		if(dtoUpdatePatient.name() != null){
			this.name = dtoUpdatePatient.name();
		}
		if(dtoUpdatePatient.dtoEndereco() != null){
			this.endereco.atualizarEndereco(dtoUpdatePatient.dtoEndereco());
		}
	}
}
