package io.github.matheusascencio.clientes.rest.exceptions;

public class SaveServicoException extends RuntimeException {

    public SaveServicoException(String service) {
        super("Serviço já cadastrado: " + service);
    }

}
