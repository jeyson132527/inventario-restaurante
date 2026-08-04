package com.restaurante.inventario.service;

import com.restaurante.inventario.entity.Usuario;
import com.restaurante.inventario.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario validarLogin(String usuario, String password) {

        Usuario u = repository.findByUsuario(usuario).orElse(null);

        if (u == null) {
            return null;
        }

        if (!Boolean.TRUE.equals(u.getActivo())) {
            return null;
        }

        if (!u.getPassword().equals(password)) {
            return null;
        }

        return u;
    }
        public java.util.List<Usuario> listar() {
    return repository.findAll();
    }

    public Usuario guardar(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}