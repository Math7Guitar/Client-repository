package io.github.matheusascencio.clientes.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, name = "username")
    @NotEmpty(message = "{ campo.username.obrigatorio }")
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "{ campo.senha.obrigatorio }")
    private String password;
}
