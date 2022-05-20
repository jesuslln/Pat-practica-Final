package com.pat.aviones.controller;

import com.pat.aviones.model.Vuelo;
import com.pat.aviones.repository.VueloRepository;
import com.pat.aviones.service.VueloService;

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
public class VueloControllerE2ETest {
    
    @Autowired
    VueloRepository repository;

    @Autowired
    VueloService service;

    @Autowired
    TestRestTemplate template;

    @LocalServerPort
    private int port;


    /*
    Esta funcion testea la extracción de todos los vuelos de la base de datos.
    */
    @Test
    public void GetAllVuelosTest() {

        // Given
        Iterable<Vuelo> vuelos = repository.findAll();

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/vuelos";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // When
        ResponseEntity<Iterable<Vuelo>> result = template.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Iterable<Vuelo>>() {
                });

        // Then
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(vuelos);

    }



    /*
     * Esta funcion testea la extracción de un vuelo filtrado por id de la base de datos.
     */
    @Test
    public void GetVueloByIdTest() {

        // Given
        Iterable<Vuelo> vuelo = repository.getVueloById("1");

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/vuelos/1";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // When
        ResponseEntity<Iterable<Vuelo>> result = template.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Iterable<Vuelo>>() {
                });

        // Then
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(vuelo);

    }
    
    /*
    Esta función testea la función VueloDeseado que devuelve todos los vuelos disponibles que salen de una ubicación.
    */

    @Test
    public void GetVueloDeseadoTest() {

        // Given
        Iterable<Vuelo> vuelosLp = service.getVuelos("Las Palmas", 100, "12062022");
        Iterable<Vuelo> vuelosOnda = service.getVuelos("Onda", 100, "12062022");

        String url_lp = "http://localhost:" + Integer.toString(port) + "/api/v1/vuelos/Las Palmas/100/12062022";
        String url_onda = "http://localhost:" + Integer.toString(port) + "/api/v1/vuelos/Onda/100/12062022";

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // When
        ResponseEntity<Iterable<Vuelo>> result_lp = template.exchange(
                url_lp,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Iterable<Vuelo>>() {
                });

        ResponseEntity<Iterable<Vuelo>> result_onda = template.exchange(
                url_onda,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Iterable<Vuelo>>() {
                });

        // Then
        then(result_lp.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result_lp.getBody()).isEqualTo(vuelosLp);

        then(result_onda.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result_onda.getBody()).isEqualTo(vuelosOnda);

    }
    



    /*
     * Esta función testea la función VueloVuelta que permite encontrar vuelos que partan de un origen a un destino específico.
     */

    @Test
    public void GetVueloVueltaTest() {

        // Given
        Iterable<Vuelo> vuelosLp = service.getVuelosVuelta("Las Palmas", 100, "12062022", "Madrid");
        Iterable<Vuelo> vuelosOnda = service.getVuelosVuelta("Onda", 100, "12062022", "Madrid");
        // si no hay vuelos debería devolver una lista vacía.
        Iterable<Vuelo> vuelosJapon = service.getVuelosVuelta("Onda", 100, "12062022", "Japón");

        String url_lp = "http://localhost:" + Integer.toString(port) + "/api/v1/vuelos/Las Palmas/100/12062022/Madrid";
        String url_onda = "http://localhost:" + Integer.toString(port) + "/api/v1/vuelos/Onda/100/12062022/Madrid";
        String url_japon = "http://localhost:" + Integer.toString(port) + "/api/v1/vuelos/Onda/100/12062022/Japón";

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // When
        ResponseEntity<Iterable<Vuelo>> result_lp = template.exchange(
                url_lp,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Iterable<Vuelo>>() {
                });

        ResponseEntity<Iterable<Vuelo>> result_onda = template.exchange(
                url_onda,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Iterable<Vuelo>>() {
                });


        ResponseEntity<Iterable<Vuelo>> result_japon = template.exchange(
                url_japon,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Iterable<Vuelo>>() {
                });     

        // Then
        then(result_lp.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result_lp.getBody()).isEqualTo(vuelosLp);

        then(result_onda.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result_onda.getBody()).isEqualTo(vuelosOnda);

        then(result_japon.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result_japon.getBody()).isEqualTo(vuelosJapon);

    }

    



}
