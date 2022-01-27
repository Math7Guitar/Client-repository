package io.github.matheusascencio.clientes.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.matheusascencio.clientes.model.entity.Usuario;
import io.github.matheusascencio.clientes.rest.exceptions.UsuarioCadastradoException;
import io.github.matheusascencio.clientes.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    
    @Autowired
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    //CRUD
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid Usuario usuario) {
        try {
            service.salvar(usuario);
        } catch( UsuarioCadastradoException e ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
