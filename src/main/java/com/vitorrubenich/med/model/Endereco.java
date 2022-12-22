package com.vitorrubenich.med.model;

import com.vitorrubenich.med.dto.DtoEndereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Embeddable
@Getter
@AllArgsConstructor
public class Endereco {

	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String UF;
	private String CEP;

	public Endereco() {}

	public Endereco(DtoEndereco dtoEndereco) {
		this.bairro = dtoEndereco.bairro();
		this.CEP = dtoEndereco.cep();
		this.cidade = dtoEndereco.cidade();
		this.numero = dtoEndereco.numero();
		this.complemento = dtoEndereco.complemento();
		this.logradouro = dtoEndereco.logradouro();
		this.UF = dtoEndereco.uf();

	}

	public void atualizarEndereco(DtoEndereco dataEndereco){
		if(dataEndereco.bairro() != null){
			this.bairro = dataEndereco.bairro();
		}
		if(dataEndereco.cep() != null){
			this.CEP = dataEndereco.cep();
		}
		if(dataEndereco.cidade() != null){
			this.cidade = dataEndereco.cidade();
		}
		if(dataEndereco.numero() != null){
			this.numero = dataEndereco.numero();
		}
		if(dataEndereco.complemento() != null){
			this.complemento = dataEndereco.complemento();
		}
		if(dataEndereco.logradouro() != null){
			this.logradouro = dataEndereco.logradouro();
		}
		if(dataEndereco.uf() != null){
			this.UF = dataEndereco.uf();
		}
	}
}
