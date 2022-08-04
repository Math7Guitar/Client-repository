package io.github.matheusascencio.clientes.rest.repository;

import io.github.matheusascencio.clientes.model.entity.Servico;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {
    
    @Query("select s from Servico s join s.cliente c where upper(c.nome) like upper(:nome) and MONTH(s.data) = :mes")
    List<Servico> findByNomeClienteEMes(@Param("nome") String nome, @Param("mes") Integer mes);

}