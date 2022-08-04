package io.github.matheusascencio.clientes.rest.exceptions;

public class ConsultUserException extends RuntimeException {

    public ConsultUserException(Integer id) {
        super("User not found: " + id);
    }

}
