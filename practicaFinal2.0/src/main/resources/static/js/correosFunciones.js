const displaycomentarios = async () =>{

    let request = await fetch(
        "/api/v1/comentarios",
        {
          method: "GET",
        }
      );
      if (request.ok) {
        //console.log(await request.json());
        correos = await request.json();
        console.log(await correos);
        $("#comentariosPágina").append('<h5>Comentarios</h5>');

        $("#comentariosPágina").append(
            '<table class="table table-striped">'+
                '<tr><td>Nombre</td>'+
                '<td>Correo</td>' +
                '<td>Asunto</td>' + 
                '<td>Comentario</td>');
        
        for (k = 0; k < correos.length; k++){
            $("#comentariosPágina").append('<tr>' + 
                '<td align="center" style="dislay: none;">' + correos[k].nombre + '</td>'+
                '<td align="center" style="dislay: none;">' + correos[k].email + '</td>'+
                '<td align="center" style="dislay: none;">' + correos[k].asunto + '</td>'+
                '<td align="center" style="dislay: none;">' + correos[k].comentarios + '</td></table>')
        }
}
}
displaycomentarios();

