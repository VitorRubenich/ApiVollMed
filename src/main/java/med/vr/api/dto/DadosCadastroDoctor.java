package med.vr.api.dto;

import med.vr.api.model.Especialidade;

public record DadosCadastroDoctor(String nome, String email, String crm, String telefone, Especialidade especialidade, DadosEndereco endereco) {
}
