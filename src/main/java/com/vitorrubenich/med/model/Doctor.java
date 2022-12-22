package com.vitorrubenich.med.model;

import com.vitorrubenich.med.dto.DtoDoctor;

import com.vitorrubenich.med.dto.DtoUpdateDoctor;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "doctor_name")
	private String name;

	private String email;

	private String telefone;

	private String crm;

	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;

	@Embedded
	private Endereco endereco;

	@Column(name = "active")
	private boolean ativo;

	public Doctor(DtoDoctor dtoDoctor) {
		this.crm = dtoDoctor.crm();
		this.name = dtoDoctor.name();
		this.email = dtoDoctor.email();
		this.telefone = dtoDoctor.telefone();
		this.especialidade = dtoDoctor.especialidade();
		this.endereco = new Endereco(dtoDoctor.endereco());
		if(dtoDoctor.ativo() == null){
			this.ativo = true;
		}else{
			this.ativo = dtoDoctor.ativo();
		}
	}

	public void atualizarDados(DtoUpdateDoctor dados){
		if(dados.name() != null){
			this.name = dados.name();
		}
		if(dados.telefone() != null){
			this.telefone = dados.telefone();
		}
		if(dados.dadosEndereco() != null){
			this.endereco.atualizarEndereco(dados.dadosEndereco());
		}
	}

    public void inativar() {
		this.ativo = false;
    }
	public void reativar(){
		this.ativo = true;
	}
}
