package com.pat.aviones.service.implementation;


import com.pat.aviones.model.Usuario;
import com.pat.aviones.repository.UsuarioRepository;
import com.pat.aviones.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImplementation implements UsuarioService {
    @Autowired
    private UsuarioRepository userRepository;
    
    @Override
    public Iterable<Usuario> getUsuarios(String estado) {
        if (estado == null) {
            return userRepository.findAll();
        } else {
            return userRepository.getUsuariosByEstado(estado);
        }
    }

    

    @Override
    public Usuario updateUser(String id, Usuario usuario){
        if(userRepository.existsById(id)){
            return userRepository.save(usuario);
        }else{
            return null;
        }
    }

    @Override
    public void deleteUsuario(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public Usuario getUserById(String id) {
        return  userRepository.findById(id).get();
    }

    @Override
    public void createUserService(Usuario usuario){
        String id=usuario.getId();
        String correo=usuario.getCorreo();
        String contrasenya=usuario.getContrasenya();
        String estado=usuario.getEstado();
        userRepository.crearUsuario(id,correo,contrasenya,estado);
    }
      
    
}
