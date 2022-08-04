package io.github.matheusascencio.clientes.rest.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.matheusascencio.clientes.model.entity.Usuario;
import io.github.matheusascencio.clientes.rest.exceptions.ConsultUserException;
import io.github.matheusascencio.clientes.rest.exceptions.UsuarioCadastradoException;
import io.github.matheusascencio.clientes.rest.service.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    //CRUD
    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid Usuario usuario) {
        try {
            service.salvar(usuario);
        } catch( UsuarioCadastradoException e ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<List<Usuario>> list() {

        log.info("User List Accessed!");

        try {
            return ResponseEntity.ok().body(this.service.obterUsuarios());
        } catch(ConsultUserException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<Usuario> getUser(@PathVariable Integer id) {

        log.info("Get User Accessed!");

        try {
            return ResponseEntity.ok().body(this.service.consultar(id));
        } catch(ConsultUserException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
