package com.pat.aviones.service;

import com.pat.aviones.model.Vuelo;

public interface VueloService {
    Iterable<Vuelo> getVuelos(String salida,int precio,String diaSalida);
    Iterable<Vuelo> getVuelo(String id);
    Iterable<Vuelo> getVuelosVuelta(String salida,int precio,String diaSalida,String vuelta);

    
    
}
