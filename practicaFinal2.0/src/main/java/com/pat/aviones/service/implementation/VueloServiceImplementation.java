package com.pat.aviones.service.implementation;


import com.pat.aviones.model.Vuelo;
import com.pat.aviones.repository.VueloRepository;
import com.pat.aviones.service.VueloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VueloServiceImplementation implements VueloService {
    @Autowired
    private VueloRepository vueloRepository;
    
    @Override
    public Iterable<Vuelo> getVuelos(String salida,int precio,String diaSalida) {
        String precioString=String.valueOf(precio);
        if (salida == null &&precioString==null && diaSalida==null ) {
            return vueloRepository.findAll();
        } else {
            return vueloRepository.getVueloByFiltro(salida,precio,diaSalida);
        }
    }

    @Override
    public Iterable<Vuelo> getVuelo(String id) {
        
        if (id == null ) {
            return vueloRepository.findAll();
        } else {
            return vueloRepository.getVueloById(id);
        }
    }
    @Override
    public Iterable<Vuelo> getVuelosVuelta(String salida,int precio,String diaSalida,String vuelta) {
        String precioString=String.valueOf(precio);
        if (salida == null &&precioString==null && diaSalida==null &&vuelta==null) {
            return vueloRepository.findAll();
        } else {
            return vueloRepository.getVueloVuelta(salida,precio,diaSalida,vuelta);
        }
    }


    
}

