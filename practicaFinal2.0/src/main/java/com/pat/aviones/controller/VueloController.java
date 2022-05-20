package com.pat.aviones.controller;


import com.pat.aviones.model.Vuelo;
import com.pat.aviones.repository.VueloRepository;
import com.pat.aviones.service.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class VueloController {
    @Autowired
    private VueloService vueloService;


    @Autowired
    private VueloRepository vueloRepository;
    
    //Get de todos los vuelos
    @GetMapping("/vuelos")
    public ResponseEntity<Iterable<Vuelo>> retrieveVuelos() {
        
        Iterable<Vuelo> response= vueloRepository.findAll();
        return ResponseEntity.ok().body(response);
    }

    //Get de solamente el usuario con ese id
    @GetMapping("/vuelos/{salida}/{precio}/{diaSalida}")
    public ResponseEntity<Iterable<Vuelo>> getVueloDeseado(@PathVariable("salida") String salida, @PathVariable("precio") int precio,@PathVariable("diaSalida") String diaSalida) {
        Iterable<Vuelo> response = vueloService.getVuelos(salida,precio,diaSalida);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/vuelos/{salida}/{precio}/{diaSalida}/{vuelta}")
    public ResponseEntity<Iterable<Vuelo>> getVueloVuelta(@PathVariable("salida") String salida, @PathVariable("precio") int precio,@PathVariable("diaSalida") String diaSalida,@PathVariable("vuelta") String vuelta) {
        Iterable<Vuelo> response = vueloService.getVuelosVuelta(salida,precio,diaSalida,vuelta);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/vuelos/{id}")
    public ResponseEntity<Iterable<Vuelo>> getVuelo(@PathVariable("id") String id) {
        Iterable<Vuelo> response = vueloService.getVuelo(id);
        return ResponseEntity.ok().body(response);
    }



 
}

