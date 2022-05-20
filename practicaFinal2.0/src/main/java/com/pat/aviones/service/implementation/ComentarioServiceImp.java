package com.pat.aviones.service.implementation;

import com.pat.aviones.model.Comentario;
import com.pat.aviones.repository.ComentarioRepository;
import com.pat.aviones.service.ComentarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ComentarioServiceImp implements ComentarioService {
    

    @Autowired
    ComentarioRepository repository;


    @Override
    public Iterable<Comentario> getComentarios() {
        return repository.findAll();
    }

    @Override
    public void crearComentario(Comentario comentario) {
            
        String email= comentario.getEmail();
        String nombre= comentario.getNombre();
        String asunto=comentario.getAsunto();
        String comentarios=comentario.getComentarios();
        repository.crearComentario(email,nombre,asunto,comentarios);
    
    }


    
}
