
let iniciarSesionBoton = document.getElementById("iniciarSesionBoton");

const buscarUsuario= async ()=>{
    console.log("buscar usuario");
  
    let usuarioInput=document.getElementById("nombreUsuarioInicio").value;
    let contrasenyaInput=document.getElementById("contrasenyaInicio").value;

  
    let request=await fetch("/api/v1/usuarios/"+usuarioInput, {
        method: "GET"
        }  
    );
    if(request.ok){
        //console.log(await request.json());
        datosEncontrados=await request.json();
        let id=datosEncontrados.id;
        let correo=datosEncontrados.correo;
        let contrasenya=datosEncontrados.contrasenya;
        let estado=datosEncontrados.estado;
        //MOSTRAR EL RESULTADO POR PANTALLA ENTRE LOS BOTONES
        console.log(id);
        console.log(correo);
        console.log(contrasenya);
        console.log(estado);

        if(estado=="bloq"){
            console.log("You should not pass, estás bloqueado")
            window.location.href="bloqueado.html"
        }else{
            if(estado=="admin"){
                if(contrasenyaInput==contrasenya){
                    console.log("Iniciaste sesión como administrador");
                    window.location.href="administrador.html"
                }else{
                    console.log("datos incorrectos");
                }
            } else if(estado=="user"){
                if(contrasenyaInput==contrasenya){
                    console.log("Iniciaste sesión como usuario");
                    window.location.href="iniciado.html"
                }else{
                    console.log("datos incorrectos");
                }
            } else{
                console.log("Estado incorrecto");
            }
            
        }
        
        
    }
    else{
        //SACARLO POR PANTALLA ENTRE LOS BOTONES
        console.log("Datos incorrectos");
    }
  }
  iniciarSesionBoton.addEventListener("click",(buscarUsuario));