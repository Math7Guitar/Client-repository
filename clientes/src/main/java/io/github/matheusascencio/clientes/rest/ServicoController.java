package io.github.matheusascencio.clientes.rest;

import javax.validation.Valid;
import com.ibm.icu.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.matheusascencio.clientes.rest.dto.ServicoDTO;
import io.github.matheusascencio.clientes.rest.exceptions.SaveServicoException;
import io.github.matheusascencio.clientes.service.ServicoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/servicos")
@RequiredArgsConstructor
public class ServicoController {

    private final ServicoService service;

    @Autowired
    public ServicoController(ServicoService service) {
        this.service = service;
    }

    //CRUD
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid ServicoDTO dto) {
        try {
            service.salvar(dto);
        } catch(SaveServicoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //Personalizados

    public BigDecimal converter (String v) {
        if(v == null) {
            return null;
        } else {
            return new BigDecimal(v.replace(".", "").replace(",", "."));
        }
    }
}