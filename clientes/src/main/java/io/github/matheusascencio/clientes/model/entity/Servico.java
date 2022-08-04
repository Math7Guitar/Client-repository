package io.github.matheusascencio.clientes.model.entity;

import java.time.LocalDate;

import javax.persistence.*;

import com.ibm.icu.math.BigDecimal;

import lombok.Data;

@Entity
@Data
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
}
