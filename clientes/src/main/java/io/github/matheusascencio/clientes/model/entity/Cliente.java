package io.github.matheusascencio.clientes.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;
    
    @Column(nullable = false, length = 11)
    @NotNull(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;

    @Column(name = "data_cadastro", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @PrePersist
    public void PrePersist() {
        setDataCadastro(LocalDate.now());
    }

    //Getters
    public Integer getId() { return this.id; }
    public String getNome() { return this.nome; }
    public String getCpf() { return this.cpf; }
    public LocalDate getDataCadastro() { return this.dataCadastro; }

    //Setters
    public void setId(Integer id) { this.id = id; }
    public void setNome(String n) { this.nome = n; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setDataCadastro(LocalDate data) { this.dataCadastro = data; }
}
