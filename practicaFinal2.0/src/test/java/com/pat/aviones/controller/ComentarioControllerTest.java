package com.pat.aviones.controller;

import com.pat.aviones.model.Comentario;
import com.pat.aviones.repository.ComentarioRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ComentarioControllerTest {

    @Autowired
    ComentarioRepository repository;


    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate template;


    /*
     * Esta funcion testea la extracción de comentarios.
     */


    @Test
    public void GetComentariosTest() {

        // Given
        Iterable<Comentario> comentarios = repository.findAll();

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/comentarios";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // When
        ResponseEntity<Iterable<Comentario>> result = template.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Iterable<Comentario>>() {
                });

        // Then
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(comentarios);

    }


    /*
     * Esta funcion testea si se crea correctamente un comentario.
     */

    @Test
    public void CrearComentario() {

        // Given
        ResponseEntity<String> creado = new ResponseEntity<String>("{\"result\" : \"OK\"}", HttpStatus.OK);

        Comentario newComentario = new Comentario("atunymillo@gmail.com", "atunymillo", "fleje crema", "Muchas gracias por la página, me gustaría que se pudiera guardar vuelos.");

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/comentarios";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Comentario> entity = new HttpEntity<>(newComentario, headers);

        // When
        ResponseEntity<String> result = template.exchange(
                url,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<String>() {
                });

        // Then
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(creado.getBody());

    }
    
}
