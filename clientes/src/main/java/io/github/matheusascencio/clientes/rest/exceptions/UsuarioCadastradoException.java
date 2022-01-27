package io.github.matheusascencio.clientes.rest.exceptions;

public class UsuarioCadastradoException extends RuntimeException {

    public UsuarioCadastradoException(String Username) {
        super("usuário já cadastrado para o username " + Username);
    }

}
