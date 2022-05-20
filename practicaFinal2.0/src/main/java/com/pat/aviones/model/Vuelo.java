package com.pat.aviones.model;

import java.sql.Time;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("VUELO")
public class Vuelo {

    @Id
    private String id;

    private @Column("SALIDA") String salida;
    private @Column("DESTINO") String destino;
    private @Column("DIASALIDA") String diaSalida;
    private @Column("HORASALIDA") Time horaSalida;
    private @Column("HORALLEGADA") Time horaLlegada;
    private @Column("COMPANYIA") String companyia;
    private @Column("PRECIO") int precio;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSalida() {
        return this.salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public String getDestino() {
        return this.destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDiaSalida() {
        return this.diaSalida;
    }

    public void setDiaSalida(String diaSalida) {
        this.diaSalida = diaSalida;
    }

    public Time getHoraSalida() {
        return this.horaSalida;
    }

    public void setHoraSalida(Time horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Time getHoraLlegada() {
        return this.horaLlegada;
    }

    public void setHoraLlegada(Time horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public String getCompanyia() {
        return this.companyia;
    }

    public void setCompanyia(String companyia) {
        this.companyia = companyia;
    }

    public int getPrecio() {
        return this.precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }


}
