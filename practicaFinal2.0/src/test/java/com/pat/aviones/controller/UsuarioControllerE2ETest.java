package com.pat.aviones.controller;

import com.pat.aviones.model.Usuario;
import com.pat.aviones.repository.UsuarioRepository;
import com.pat.aviones.service.UsuarioService;

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
public class UsuarioControllerE2ETest {
    

    @Autowired
    UsuarioRepository repository;

    @Autowired
    UsuarioService service;

    @Autowired
    TestRestTemplate template;

    @LocalServerPort
    private int port;



    /*
     * Esta funcion testea la extracción de todos los usuarios de la base de datos.
     */

    @Test
    public void GetAllUsuarios() {

        // Given
        Iterable<Usuario> usuarios = repository.findAll();

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/usuarios";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // When
        ResponseEntity<Iterable<Usuario>> result = template.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Iterable<Usuario>>() {
                });

        // Then
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(usuarios);

    }


    /*
     * Esta funcion testea la extracción de todos los usuarios filtrados por estado de la base de datos.
     */

    @Test
    public void getUsuariosByEstado() {

        // Given
        Iterable<Usuario> usuarios = service.getUsuarios("admin");

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/usuarios/?estado=admin";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // When
        ResponseEntity<Iterable<Usuario>> result = template.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Iterable<Usuario>>() {
                });

        // Then
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(usuarios);

    }



    /*
     * Esta funcion testea la extracción de un usuario filtrado por Id.
     */

    @Test
    public void getUsuariosById() {

        // Given
        Usuario usuario = service.getUserById("carles_olucha");

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/usuarios/carles_olucha";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // When
        ResponseEntity<Usuario> result = template.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Usuario>() {
                });

        // Then
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(usuario);

    }


    /*
     * Esta funcion testea si se crea correctamente un usuario.
     */

    @Test
    public void CrearUsuario() {

        // Given
        ResponseEntity<String> creado = new ResponseEntity<String>("{\"result\" : \"OK\"}", HttpStatus.OK);

        Usuario newUsuario = new Usuario("pakito_chocolatero", "pakito@gmail.com", "chocolate", "user");

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/usuarios";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Usuario> entity = new HttpEntity<>(newUsuario, headers);

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

    
    /*
     * Esta funcion testea si se elimina correctamente un usuario.
     */
    
    @Test
    public void deleteUsuario() {

        // Given
        ResponseEntity<String> borrado = ResponseEntity.noContent().build();

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/usuarios/carles_olucha";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // When
        ResponseEntity<String> result = template.exchange(
                url,
                HttpMethod.DELETE,
                entity,
                new ParameterizedTypeReference<String>() {
                });

        // Then
        then(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        then(result.getBody()).isEqualTo(borrado.getBody());

    }
    

    /*
     * Esta funcion testea si se actualiza correctamente un usuario.
     */

    @Test
    public void updateUsuario() {

        // Given
        Usuario usuario = new Usuario("maria_angeles", "mangeles@gmail.com", "esternocleidomastoideo", "user");

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/usuarios";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Usuario> entity = new HttpEntity<>(usuario, headers);

        // When
        ResponseEntity<Usuario> result = template.exchange(
                url,
                HttpMethod.PUT,
                entity,
                new ParameterizedTypeReference<Usuario>() {
                });

        // Then
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(usuario);

    }
    



    /*
     * Esta funcion testea si se actualiza correctamente el estado de un usuario.
     */

    @Test
    public void updateUsuarioEstado() {

        // Given
        ResponseEntity<String> actualizado = new ResponseEntity<String>("{\"result\" : \"OK\"}", HttpStatus.OK);

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/usuarios/carles_olucha/admin";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // When
        ResponseEntity<String> result = template.exchange(
                url,
                HttpMethod.PUT,
                entity,
                new ParameterizedTypeReference<String>() {
                });

        // Then
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(actualizado.getBody());

    }

}
