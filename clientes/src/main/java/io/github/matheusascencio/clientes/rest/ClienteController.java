package io.github.matheusascencio.clientes.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.matheusascencio.clientes.model.entity.Cliente;
import io.github.matheusascencio.clientes.rest.exceptions.ClienteAtualizadoException;
import io.github.matheusascencio.clientes.rest.exceptions.ClienteDeletadoException;
import io.github.matheusascencio.clientes.rest.exceptions.ClienteSalvoException;
import io.github.matheusascencio.clientes.rest.exceptions.ConsultaClienteException;
import io.github.matheusascencio.clientes.rest.exceptions.ListaClientesException;
import io.github.matheusascencio.clientes.service.ClienteService;

@RestController
@RequestMapping(value = "/api/clientes")

public class ClienteController {
    
    private final ClienteService service;

    @Autowired
    public ClienteController(ClienteService service) {
        this.service = service;
    }

    //CRUD
    @GetMapping
    public void obterTodos() {
        try {
            service.obterTodos();
        } catch( ListaClientesException e ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid Cliente cli){
        try {
            service.salvar(cli);
        } catch( ClienteSalvoException e ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public void consultarPorId(@PathVariable Integer id) {
        try {
            service.consultarPorId(id);
        } catch( ConsultaClienteException e ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable Integer id){
        try {
            service.deletar(id);
        } catch( ClienteDeletadoException e ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("{id}")
    public void atualizar(@PathVariable Integer id, @RequestBody Cliente clienteAtualizado) {
        try {
            service.atualizar(id, clienteAtualizado);
        } catch( ClienteAtualizadoException e ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
