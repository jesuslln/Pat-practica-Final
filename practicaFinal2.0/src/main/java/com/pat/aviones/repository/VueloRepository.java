package com.pat.aviones.repository;

import com.pat.aviones.model.Vuelo;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VueloRepository extends CrudRepository<Vuelo, String> {
    
    //Filtros
    //Salida, destino,precio, dia,
    
/*Filtro
Salida,fecha,dinero
*/

    @Query("SELECT * FROM VUELO WHERE VUELO.SALIDA= :salida  AND VUELO.PRECIO<= :precio AND VUELO.DIASALIDA= :diaSalida") //solamente salida, en un futuro más cosas
    public Iterable<Vuelo> getVueloByFiltro(String salida,int precio, String diaSalida);


    @Query("SELECT * FROM VUELO WHERE VUELO.ID= :id") //solamente salida, en un futuro más cosas
    public Iterable<Vuelo> getVueloById(String id);

    @Query("SELECT * FROM VUELO WHERE VUELO.SALIDA= :salida  AND VUELO.PRECIO<= :precio AND VUELO.DIASALIDA= :diaSalida AND VUELO.DESTINO= :vuelta") //solamente salida, en un futuro más cosas
    public Iterable<Vuelo> getVueloVuelta(String salida,int precio, String diaSalida,String vuelta);

}
