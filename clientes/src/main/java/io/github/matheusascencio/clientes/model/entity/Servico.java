package io.github.matheusascencio.clientes.model.entity;

import java.time.LocalDate;

import javax.persistence.*;

import com.ibm.icu.math.BigDecimal;

import lombok.Data;

@Entity@Data
public class Servico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Column
    private BigDecimal valor;

    @Column
    private LocalDate data;

    //Getters
    public Integer getId() { return this.id; }
    public String getDescricao() { return this.descricao; }
    public Cliente getCliente() { return this.cliente; }
    public BigDecimal getValor() { return this.valor; }
    public LocalDate getData() { return this.data; }

    //Setters
    public void setId(Integer id) { this.id = id; }
    public void setDescricao(String d) { this.descricao = d; }
    public void setCliente(Cliente cli) { this.cliente = cli; }
    public void setValor(BigDecimal v) { this.valor = v; }
    public void setData(LocalDate data) { this.data = data; }
}
