package com.pat.aviones.controller;

import com.pat.aviones.model.Comentario;
import com.pat.aviones.service.ComentarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    // Get de todos los usuarios
    @GetMapping("/comentarios")
    public ResponseEntity<Iterable<Comentario>> retrieveComentarios() {

        Iterable<Comentario> response = comentarioService.getComentarios();
        return ResponseEntity.ok().body(response);
    }


    // Meter un usuario
    @PostMapping("/comentarios")
    public ResponseEntity<String> createComentario(
            @RequestBody Comentario comentario,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<String>("{\"result\" : \"KO\"}", HttpStatus.BAD_REQUEST);
        } else {
            comentarioService.crearComentario(comentario);
            return new ResponseEntity<String>("{\"result\" : \"OK\"}", HttpStatus.OK);
        }

    }


}


