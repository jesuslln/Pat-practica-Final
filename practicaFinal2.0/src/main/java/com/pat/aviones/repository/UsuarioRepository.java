package com.pat.aviones.repository;

import com.pat.aviones.model.Usuario;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, String> {
    
    //Filtros
    //Estado
    @Query("SELECT * FROM USUARIO WHERE USUARIO.ESTADO= :estado")
    public Iterable<Usuario> getUsuariosByEstado(String estado);
    

    //Crear
    @Modifying //SIEMPRE PONERLO CUANDO SE MODIFICA LA BASE DE DATOS
    @Query("INSERT INTO USUARIO (ID,CORREO,CONTRASENYA,ESTADO) VALUES (:id,:correo,:contrasenya,:estado)")
    void crearUsuario(String id, String correo, String contrasenya, String estado);

    
    //Eliminar
    @Query("DELETE FROM USUARIO WHERE USUARIO.ID = :userId")
    public void eliminarUsuario(String userId);
    

    //Actualizar
    @Query("UPDATE USUARIO SET USUARIO.ESTADO= :estado WHERE USUARIO.ID = :Id")
    public Usuario updateUsuarioEstado(String userEstado,String userId);
    
}
