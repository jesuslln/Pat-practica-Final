package com.pat.aviones.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("USUARIO")
public class Usuario {
    @Id
    private String id;

    private String correo;
    private String contrasenya;
    private String estado;

    public Usuario(String id, String correo, String contrasenya, String estado) {
        this.id = id;
        this.correo = correo;
        this.contrasenya = contrasenya;
        this.estado = estado;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenya() {
        return this.contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    } 
}
