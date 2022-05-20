//BUSCAR USUARIO-> ORDENAR CÓDIGO

let buscar_vuelos_boton = document.getElementById("buscarVueloBoton");

const buscarVuelo = async () => {
  let fechaIda = document.getElementById("inputCheckIn").value;
  let fechaIdaDate = new Date(fechaIda);
  let fechaIdaDia = fechaIdaDate.getDate();
  let fechaIdaMonth = fechaIdaDate.getMonth() + 1;
  let fechaIdaYear = fechaIdaDate.getFullYear();

  let fechaIdaDiaString = fechaIdaDia.toString();
  let fechaIdaMonthString = fechaIdaMonth.toString();
  let fechaIdaYearString = fechaIdaYear.toString();

  //Afegir el 0 dia i mes
  if (fechaIdaDia < 10) {
    fechaIdaDiaString = "0" + fechaIdaDiaString;
  }
  if (fechaIdaMonth < 10) {
    fechaIdaMonthString = "0" + fechaIdaMonthString;
  }

  let fechaIdaString =
    fechaIdaDiaString + fechaIdaMonthString + fechaIdaYearString; //+Date.valueOf(fechaIdaMonth)+Date.valueOf(fechaIdaYear);
  console.log(fechaIdaString);
  //dia+mes+anyo

  let fechaVuelta = document.getElementById("inputCheckOut").value;
  let fechaVueltaDate = new Date(fechaVuelta);
  let fechaVueltaDia = fechaVueltaDate.getDate();
  let fechaVueltaMonth = fechaVueltaDate.getMonth() + 1;
  let fechaVueltaYear = fechaVueltaDate.getFullYear();

  let fechaVueltaDiaString = fechaVueltaDia.toString();
  let fechaVueltaMonthString = fechaVueltaMonth.toString();
  let fechaVueltaYearString = fechaVueltaYear.toString();

  //Afegir el 0 dia i mes
  if (fechaVueltaDia < 10) {
    fechaVueltaDiaString = "0" + fechaVueltaDiaString;
  }
  if (fechaVueltaMonth < 10) {
    fechaVueltaMonthString = "0" + fechaVueltaMonthString;
  }

  let fechaVueltaString =
    fechaVueltaDiaString + fechaVueltaMonthString + fechaVueltaYearString; //+Date.valueOf(fechaIdaMonth)+Date.valueOf(fechaIdaYear);
  console.log(fechaVueltaString);

  let precio = document.getElementById("inputPrecioMaximo").value;

  let aeropuerto1 = document.getElementById("salida1").value;

  let aeropuerto2 = document.getElementById("salida2").value;

  let request = await fetch(
    "/api/v1/vuelos/" + aeropuerto1 + "/" + precio + "/" + fechaIdaString,
    {
      method: "GET",
    }
  );
  if (request.ok) {
    //console.log(await request.json());
    ida1 = await request.json();
    console.log(await ida1);
    let request2 = await fetch(
      "/api/v1/vuelos/" + aeropuerto2 + "/" + precio + "/" + fechaIdaString,
      {
        method: "GET",
      }
    );
    if (request.ok) {
      //console.log(await request.json());
      ida2 = await request2.json();
      console.log(await ida2);

      destinos1 = [];
      destinos2 = [];
      for (let i = 0; i < ida1.length; i++) {
        destinos1.push(ida1[i].destino);
      }
      for (let i = 0; i < ida2.length; i++) {
        destinos2.push(ida2[i].destino);
      }
      console.log(destinos1);
      console.log(destinos2);

      destinosDeInteres = [];
      for (let i = 0; i < destinos1.length; i++) {
        if (destinos2.includes(destinos1[i])) {
          destinosDeInteres.push(destinos1[i]);
        }
      }
      destinosFiltrados = [];
      //Filtrar para hacer solamente únicos:
      for (var i = 0; i < destinosDeInteres.length; i++) {
        const elemento = destinosDeInteres[i];

        if (!destinosFiltrados.includes(destinosDeInteres[i])) {
          destinosFiltrados.push(elemento);
        }
      }

      console.log(destinosFiltrados);

      amor = [];
      for (let i = 0; i < destinosFiltrados.length; i++) {
        let request3 = await fetch(
          "/api/v1/vuelos/" +
            destinosFiltrados[i] +
            "/" +
            precio +
            "/" +
            fechaVueltaString +
            "/" +
            aeropuerto1,
          {
            method: "GET",
          }
        );
        if (request.ok) {
          vuelta1 = await request3.json();
          console.log(vuelta1);
          let request4 = await fetch(
            "/api/v1/vuelos/" +
              destinosFiltrados[i] +
              "/" +
              precio +
              "/" +
              fechaVueltaString +
              "/" +
              aeropuerto2,
            {
              method: "GET",
            }
          );
          if (request.ok) {
            //console.log(await request.json());
            vuelta2 = await request4.json();

            if (vuelta1.length != 0 && vuelta2.length != 0) {
              amor.push(destinosFiltrados[i]);
            }
          }
        }
      }
      console.log(amor);

      //////////////////////////////////////////////////////////////////////////////////////////////////////
      //Obtener a partir del destino la combinación que deben tomar:
      definitivo = [];
      for (let i = 0; i < amor.length; i++) {
        let request5 = await fetch(
          "/api/v1/vuelos/" +
            aeropuerto1 +
            "/" +
            precio +
            "/" +
            fechaVueltaString +
            "/" +
            amor[i],
          {
            method: "GET",
          }
        );
        if (request.ok) {
          amorIda1 = await request5.json();
          //console.log(amorIda1);
          let request6 = await fetch(
            "/api/v1/vuelos/" +
              aeropuerto2 +
              "/" +
              precio +
              "/" +
              fechaVueltaString +
              "/" +
              amor[i],
            {
              method: "GET",
            }
          );
          if (request.ok) {
            //console.log(await request.json());
            amorIda2 = await request6.json();
            amorVuelta1 = vuelta1;
            amorVuelta2 = vuelta2;

            //Resultados
            console.log(amorIda1);
            console.log(amorIda2);
            console.log(amorVuelta1);
            console.log(amorVuelta2);

            for(i=0;i<amor.length;i++){
                $("#cajaResultado").append(
                '<div class=" tm-bg-img" id="tm-section-10">'+
                '<div class="row">'+
                    '<div class="tm-bg-white ie-container-width-fix-2">'+
                        '<div class="container ie-h-align-center-fix">'+
                            '<div class="row">'+
                                '<div class="col-xs-12 ml-auto mr-auto ie-container-width-fix">'+
                                    '<div id="resultadoIda1'+i.toString()+'"></div>'+
                                    '<table class="table table-striped tabla-resultados" id="resultadoTablaIda1'+i.toString()+'" >'+
                                    '</table>'+

                                    '<div id="resultadoIda2'+i.toString()+'"></div>'+
                                    '<table class="table table-striped tabla-resultados" id="resultadoTablaIda2'+i.toString()+'" >'+
                                    '</table>'+

                                    '<div id="resultadoVuelta1'+i.toString()+'"></div>'+
                                    '<table class="table table-striped tabla-resultados" id="resultadoTablaVuelta1'+i.toString()+'" >'+
                                    '</table>'+

                                    '<div id="resultadoVuelta2'+i.toString()+'"></div>'+
                                    '<table class="table table-striped tabla-resultados" id="resultadoTablaVuelta2'+i.toString()+'" > '+
                                    '</table>'+
                                '</div></div></div></div></div></div>') 
                
                    $("#resultadoIda1"+i.toString()).append(
                        '<h2>'+amor[i]+'</h2>'+
                        '<h5>Viaje ida primera persona</h5>'
                    )
                    $("#resultadoTablaIda1"+i.toString()).append(
                    '<tr><td>Salida</td>'+
                            '<td>Hora salida</td>' + 
                            '<td>Hora llegada</td>'+
                            '<td>Compañia</td>'+
                            '<td>Precio</td>');
        
                    for (k = 0; k < amorIda1.length; k++){
                        $("#resultadoTablaIda1"+i.toString()).append('<tr>' + 
                            '<td align="center" style="dislay: none;">' + amorIda1[k].salida + '</td>'+
                            '<td align="center" style="dislay: none;">' + amorIda1[k].horaSalida + '</td>'+
                            '<td align="center" style="dislay: none;">' + amorIda1[k].horaLlegada + '</td>'+
                            '<td align="center" style="dislay: none;">' + amorIda1[k].companyia + '</td>'+
                            '<td align="center" style="dislay: none;">' + amorIda1[k].precio + '</td>'+'</tr>')
                    }
        
                    $("#resultadoIda2"+i.toString()).append(
                        '<h5>Viaje ida segunda persona</h5>'
                    )
                    $("#resultadoTablaIda2"+i.toString()).append(
                    '<tr><td>Salida</td>'+
                            '<td>Hora salida</td>' + 
                            '<td>Hora llegada</td>'+
                            '<td>Compañia</td>'+
                            '<td>Precio</td>');
        
                    for (k = 0; k < amorIda2.length; k++){
                        $("#resultadoTablaIda2"+i.toString()).append('<tr>' + 
                            '<td align="center" style="dislay: none;">' + amorIda2[k].salida + '</td>'+
                            '<td align="center" style="dislay: none;">' + amorIda2[k].horaSalida + '</td>'+
                            '<td align="center" style="dislay: none;">' + amorIda2[k].horaLlegada + '</td>'+
                            '<td align="center" style="dislay: none;">' + amorIda2[k].companyia + '</td>'+
                            '<td align="center" style="dislay: none;">' + amorIda2[k].precio + '</td>'+'</tr>')
                    }
        
                    $("#resultadoVuelta1"+i.toString()).append(
                        '<h5>Viaje vuelta primera persona</h5>'
                    )
                    $("#resultadoTablaVuelta1"+i.toString()).append(
                    '<tr><td>Salida</td>'+
                            '<td>Hora salida</td>' + 
                            '<td>Hora llegada</td>'+
                            '<td>Compañia</td>'+
                            '<td>Precio</td>');
        
                    for (k = 0; k < amorVuelta1.length; k++){
                        $("#resultadoTablaVuelta1"+i.toString()).append('<tr>' + 
                            '<td align="center" style="dislay: none;">' + amorVuelta1[k].salida + '</td>'+
                            '<td align="center" style="dislay: none;">' + amorVuelta1[k].horaSalida + '</td>'+
                            '<td align="center" style="dislay: none;">' + amorVuelta1[k].horaLlegada + '</td>'+
                            '<td align="center" style="dislay: none;">' + amorVuelta1[k].companyia + '</td>'+
                            '<td align="center" style="dislay: none;">' + amorVuelta1[k].precio + '</td>'+'</tr>')
                    }
        
                    $("#resultadoVuelta2"+i.toString()).append(
                        '<h5>Viaje vuelta segunda persona</h5>'
                    )
                    $("#resultadoTablaVuelta2"+i.toString()).append(
                    '<tr><td>Salida</td>'+
                            '<td>Hora salida</td>' + 
                            '<td>Hora llegada</td>'+
                            '<td>Compañia</td>'+
                            '<td>Precio</td>');
        
                    for (k = 0; k < amorVuelta2.length; k++){
                        $("#resultadoTablaVuelta2"+i.toString()).append('<tr>' + 
                            '<td align="center" style="dislay: none;">' + amorVuelta2[k].salida + '</td>'+
                            '<td align="center" style="dislay: none;">' + amorVuelta2[k].horaSalida + '</td>'+
                            '<td align="center" style="dislay: none;">' + amorVuelta2[k].horaLlegada + '</td>'+
                            '<td align="center" style="dislay: none;">' + amorVuelta2[k].companyia + '</td>'+
                            '<td align="center" style="dislay: none;">' + amorVuelta2[k].precio + '</td>'+'</tr>')
                    }
                            
            }

          }
        }
      }

      /*
            EL CÓDIGO CONTINÚA AQUÍ:
            0) Hacer una funcion para transformas las fechas y ordenar el código y una función de buscarVuelos para no repetir el código tanto
            1) Hacer el pen pineapple de las listas
            2) Obtener los resultados de ida
            3) Por cada destino del resultado mirar si hay vuelo de vuelta
                si lo hay pues anyadir en una lista de resultadoDefinitivo (cada resultado definitivo deberá tener 4 vuelos)
                Si no eliminar.
            4) meterlo en la página guay

            */
    } 
  }
  if(amor.length==0 ){
    console.log("No hay vuelos disponibles");
    window.location.href="noResultado.html";
    //redirigir a la página de lo lamentamos y poner el formulario para buscar+el lo lamentamos
    }else{
        console.log("Redirigir a la página de resultados");
        //página resultados
        //window.location.href="resultado.html";
    }

  
};
buscar_vuelos_boton.addEventListener("click", buscarVuelo);


    
    
        
/*
$("#Table").append('<tr><td>Id</td>'+
            '<td>Nombre</td>' + 
            '<td>Descripcion</td>');
        for (i = 0; i < datos.length; i++){
        
         $("#Table").append('<tr>' + 
            '<td align="center" style="dislay: none;">' + datos[i].id + '</td>'+
            '<td align="center" style="dislay: none;">' + datos[i].nombreDispositivo + '</td>'+
            '<td align="center" style="dislay: none;">' + datos[i].descripcion + '</td>'+'</tr>');
        }
*/
