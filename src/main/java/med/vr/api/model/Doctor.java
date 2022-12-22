package med.vr.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import med.vr.api.dto.DadosCadastroDoctor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@Table(name = "medicos")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "medico")
public class Doctor extends Entidade{

    private String Nome;

    private String Email;

    private String Telefone;

    private String CRM;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Column(name = "dt_cadastro", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate data_cadastro;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_id_fk", nullable = false)
    private Endereco endereco;


    public Doctor(DadosCadastroDoctor dados){
        data_cadastro = LocalDate.now();
        this.Email = dados.email();
        this.Telefone = dados.telefone();
        this.Nome = dados.nome();
        this.CRM = dados.crm();
        this.endereco = new Endereco(dados.endereco());
    }
}
