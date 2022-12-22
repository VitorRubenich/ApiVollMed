package med.vr.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import med.vr.api.dto.DadosEndereco;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Endereco extends Entidade{

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String Cidade;

    @Column(nullable = false, length = 2)
    //@Enumerated(EnumType.STRING)
    private String uf;

    private String CEP;

    public Endereco(DadosEndereco dadosEndereco){
        this.bairro = dadosEndereco.bairro();
        this.CEP = dadosEndereco.CEP();
        this.Cidade = dadosEndereco.cidade();
        this.complemento = dadosEndereco.complemento();
        this.logradouro = dadosEndereco.logradouro();
        this.numero = dadosEndereco.numero();
        this.uf = dadosEndereco.uf();
    }
}
