function validarFormulario(){

    //SACAR LAS TRES FECHAS
    const fechaActual = new Date(Date.now());
    let diaActual=fechaActual.getDate();
    let mesActual=fechaActual.getMonth();
    let anyoActual=fechaActual.getFullYear();
    let fechavuelta= document.getElementById("inputCheckOut").value;
    let fechaida= document.getElementById("inputCheckIn").value;

    if(fechaida=="" || fechavuelta==""){
         alert("Debe incluir las dos fechas");
    }else{
        console.log("Empezamos a validar");
        let fechaVueltaBien=new Date(fechavuelta);
        let anyovuelta=fechaVueltaBien.getFullYear();
        let mesvuelta=fechaVueltaBien.getMonth()+1;
        let diavuelta=fechaVueltaBien.getDate();

        let fechaIdaBien=new Date(fechaida);
        let anyoida=fechaIdaBien.getFullYear();
        let mesida=fechaIdaBien.getMonth()+1;
        let diaida=fechaIdaBien.getDate();

        //CONDICIONES:
            //1) Que la de ida sea mayor que la actual
            //2) Que la de vuelta sea mayor que la actual
            //3) Que la de vuelta sea mayor que la de ida
            
            if(Date.parse(fechaActual) <= Date.parse(fechaIdaBien) && Date.parse(fechaActual) <= Date.parse(fechaVueltaBien) && 
            Date.parse(fechaIdaBien) <= Date.parse(fechaVueltaBien) && (anyoida<anyoActual+2) && (anyovuelta<=anyoActual+2)){
                
                console.log("Fechas correcto");
             }else{
                 alert("Revise las fechas");
             }
     }
    
    //VALIDAR PRECIO
    let precio=document.getElementById("inputPrecioMaximo").value;
    precioInt= parseInt(precio);
    console.log("Es un número, muy bien");
    console.log(precioInt);
    if(isNaN(precioInt)){
        alert("Debe de ser un número");
    }
    else{
        if(precioInt<=0){ //que sea mayor que 0 Y QUE SEA UN NÚMERO
            alert("El precio debe ser positivo");
        }else if(precioInt>=1000){
            console.log("Veo que estás enamorado");
        } else {
            console.log("El precio está bien");
        }
    }
    
    

    //VALIDAR LOS ORÍGENES CUANDO TENGAMOS LA BASE DE DATOS DEBE ESTAR INCLUÍDO AHÍ
    let salida1=document.getElementById("salida1").value;
    let salida2=document.getElementById("salida2").value;
    if(salida1=="1"){ //que sea real el sitio (según la API)
        console.log("está mal la salida1")

    }else if(salida2=="1"){ //que sea real el sitio (según la API)
        console.log("está mal la salida2")
    } else{
        console.log("todo correcto")
    }
}

function validarFormularioContato(){
    let nombre=document.getElementById("contact_name").value;
    let email=document.getElementById("contact_email").value;
    let asunto=document.getElementById("contact_subject").value;
    let mensaje=document.getElementById("contact_message").value;

    if((nombre=="") || (nombre.length>50) || (nombre.length<3)){             //que sea un string de como mucho 50 caracteres mayor de 1 letra
        alert("Incluya un nombre real");
    } else if((email=="") || (email.length<6) || (email.length>50) ||
            (!email.includes("@")) || (!email.includes(".")) || (email.includes(" "))){        //que tenga forma de correo electrónico mayor de 10
        alert("Revise el email")
    } else if(asunto=="" || (asunto.length<3) || (asunto.length>50)){                          //que sea un string que puede tener números de como mucho 50 caracteres que sea mayor de 3
        alert("Debe de incluir un asunto válido");
    } else if(mensaje=="" || (mensaje.length>300))                              //que tenga como mucho 300 caracteres
    {
        alert("Mucho texto (Yoda)");
    }else{
        console.log("todo correcto")
    }

}

function validarFormularioInicio(){
    console.log("Iniciaste sesión");
}

function validarFormularioRegistro(){
    
    let usuario=document.getElementById("nombreUsuarioRegistro").value;
    let correo=document.getElementById("correoRegistro").value;
    let contrasenya=document.getElementById("contrasenyaRegistro").value;
    let repcontrasenya=document.getElementById("contrasenyaRegistro2").value;
    
    if((usuario=="") || (usuario.length>20) || (usuario.length<3)){             //que sea un string de como mucho 50 caracteres mayor de 1 letra
        alert("El nombre debe incluir entre 3 y 20 caracteres");
    } else if((correo=="") || (correo.length<6) || (correo.length>50) ||
    (!correo.includes("@")) || (!correo.includes(".")) || (correo.includes(" "))){        //que tenga forma de correo electrónico mayor de 10
        alert("Revise el email");
    } else if(contrasenya!=repcontrasenya){
        alert("Las contraseñas deben de coincidir");
    } else{
        console.log("Todo correcto");
    }

}

function irseLogin(){
    console.log("Me voy a login");
    window.location="login.html";

}

function irseRegistrarse(){
    console.log("Me voy a registrar");
    window.location="registrarse.html";

}
function irseInicio(){
    console.log("Me voy a inicio");
    window.location="index.html";
}

function validarFormularioAdministrador(){
    console.log("Vamos a ver si existe ese usuario");
}

function administradorBloquear(){
    console.log("Usuario bloqueado");
}
function administradorCrear(){
    console.log("Usuario es admin");
}
function administradorBorrar(){
    console.log("Borrar usuario");
}
function cerrarSesionUsuario(){
    console.log("Ha cerrado sesión");
}
function irCorreos(){
    window.location="correos.html";
}

let comentario_boton = document.getElementById("comentarioId");

comentario_boton.addEventListener(
    "click",
    (postCoemntario = async () => {
        console.log("hola");
      //Obtenemos los datos del formulario
      let nombre = document.getElementById("contact_name").value;
      let email = document.getElementById("contact_email").value;
      let asunto = document.getElementById("contact_subject").value;
      let comentarios = document.getElementById("contact_message").value;
      console.log(nombre);
      console.log(email);
      console.log(asunto);
      console.log(comentarios);  
  
      //Ahora debemos de hacer un fetch de contactos ?
      let request = await fetch("/api/v1/comentarios", {
        method: "POST",
        credentials: "same-origin",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
            email: email,
            nombre: nombre,
            asunto: asunto,
            comentarios: comentarios
        }),
        dataType: "json",
      });
  
      if (request.ok) {
        console.log("Success!");
      }
    })
  );