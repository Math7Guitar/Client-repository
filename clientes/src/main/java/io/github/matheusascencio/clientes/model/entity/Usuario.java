package io.github.matheusascencio.clientes.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data
@NoArgsConstructor
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


    //Getters
    public Integer getId() { return this.id; }
    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }

    //Setters
    public void setId(Integer id) { this.id = id; }
    public void setUsername(String u) { this.username = u; }
    public void setPassword(String password) { this.password = password; }
}
