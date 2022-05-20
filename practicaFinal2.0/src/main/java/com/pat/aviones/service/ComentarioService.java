package com.pat.aviones.service;

import com.pat.aviones.model.Comentario;

public interface ComentarioService {

    public Iterable<Comentario> getComentarios();

    public void crearComentario(Comentario comentario);
    
}
