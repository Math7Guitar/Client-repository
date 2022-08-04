package io.github.matheusascencio.clientes.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.github.matheusascencio.clientes.model.entity.Cliente;
import io.github.matheusascencio.clientes.rest.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;


    public List<Cliente> obterTodos() {
        return repository.findAll();
    }


    public Cliente salvar(Cliente cli){
        return repository.save(cli);
    }

    public Cliente consultarPorId(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    public void deletar(Integer id){
        repository.findById(id).map( cli -> {
            repository.delete(cli);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void atualizar(Integer id, Cliente clienteAtualizado) {
        repository.findById(id).map( cliente -> {
            clienteAtualizado.setId(cliente.getId());
            return repository.save(clienteAtualizado);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    
}
