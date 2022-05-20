package com.pat.aviones.repository;

import com.pat.aviones.model.Comentario;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface ComentarioRepository extends CrudRepository<Comentario, String> {
    
    @Modifying // SIEMPRE PONERLO CUANDO SE MODIFICA LA BASE DE DATOS
    @Query("INSERT INTO COMENTARIOS (EMAIL,NOMBRE,ASUNTO,COMENTARIO) VALUES (:email,:nombre,:asunto,:comentario)")
    void crearComentario(String email, String nombre, String asunto, String comentario);
    
}
