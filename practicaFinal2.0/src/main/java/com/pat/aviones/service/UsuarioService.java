package com.pat.aviones.service;

import com.pat.aviones.model.Usuario;

public interface UsuarioService {
    Iterable<Usuario> getUsuarios(String estado);

    Usuario updateUser(String id, Usuario usuario);
    void deleteUsuario(String id);
    Usuario getUserById(String id);
    void createUserService(Usuario usuario);
}
