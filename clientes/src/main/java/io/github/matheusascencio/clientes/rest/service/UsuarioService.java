package io.github.matheusascencio.clientes.rest.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import io.github.matheusascencio.clientes.model.entity.Usuario;
import io.github.matheusascencio.clientes.rest.exceptions.UsuarioCadastradoException;
import io.github.matheusascencio.clientes.rest.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    //CRUD
    public Usuario salvar(Usuario usuario) {
        boolean exists = repository.existsByUsername(usuario.getUsername());
        if(exists) {
            throw new UsuarioCadastradoException(usuario.getUsername());
        }
        return repository.save(usuario);
    }

    
    public List<Usuario> obterUsuarios() {
        return repository.findAll();
    }


    public Usuario consultar(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Usuario usuarioAtualizado) {
        repository.findById(id).map( usuario -> {
            usuarioAtualizado.setId(usuario.getId());
            return repository.save(usuarioAtualizado);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_MODIFIED));
    }
    

    public void deletar(@PathVariable Integer id) {
        repository.findById(id).map( usuario -> {
            repository.delete(usuario);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuário inexistente"));
        return User.builder().username(user.getUsername()).password(user.getPassword()).roles("USER").build();
    }
}
