package com.pat.aviones.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("COMENTARIOS")
public class Comentario {

    @Id
    private @Column("EMAIL") String email;
    private @Column("NOMBRE") String nombre;
    private @Column("ASUNTO") String asunto;
    private @Column("COMENTARIO") String comentarios;



    public Comentario(String email, String nombre, String asunto, String comentarios) {
        this.email = email;
        this.nombre = nombre;
        this.asunto = asunto;
        this.comentarios = comentarios;
    }



    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public String getNombre() {
        return nombre;
    }



    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getAsunto() {
        return asunto;
    }



    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }



    public String getComentarios() {
        return comentarios;
    }



    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((asunto == null) ? 0 : asunto.hashCode());
        result = prime * result + ((comentarios == null) ? 0 : comentarios.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        return result;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Comentario other = (Comentario) obj;
        if (asunto == null) {
            if (other.asunto != null)
                return false;
        } else if (!asunto.equals(other.asunto))
            return false;
        if (comentarios == null) {
            if (other.comentarios != null)
                return false;
        } else if (!comentarios.equals(other.comentarios))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        return true;
    }

   
}
