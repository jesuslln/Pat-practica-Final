
//BUSCAR USUARIO

let buscar_boton = document.getElementById("boton_buscar");

const buscarUsuario= async ()=>{
    console.log("buscar usuario");
  
    let usuarioInput=document.getElementById("buscarUsuario").value;
    
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
        try{
            document.getElementById("idDesplegable").innerHTML=("id: "+id);
            document.getElementById("correoDesplegable").innerHTML=("Correo: "+ correo);
            document.getElementById("contrasenyaDesplegable").innerHTML=("Contrasenya: "+contrasenya);
            document.getElementById("estadoDesplegable").innerHTML=("Estado: "+estado);   
        }catch{
            return 0; 
        }
             
    }
    else{
        //SACARLO POR PANTALLA ENTRE LOS BOTONES
        document.getElementById("resultadoBusqueda").innerHTML=("Este usuario no existe");
        setTimeout(2000);
        location.reload();
    }
  }
buscar_boton.addEventListener("click",(buscarUsuario));




//BORRAR USUARIO

let borrar_boton = document.getElementById("botonBorrar");

const borrarUsuario=async ()=>{
    let usuarioInput=document.getElementById("buscarUsuario").value;
    if(usuarioInput=="dioni_martin"){
        console.log("No puedes borrar a esta leyenda");
        document.getElementById("idDesplegable").innerHTML=("No puedes borrar a esta leyenda");
        document.getElementById("correoDesplegable").innerHTML=("");
        document.getElementById("contrasenyaDesplegable").innerHTML=("");
        document.getElementById("estadoDesplegable").innerHTML=(""); 
    }else{

    let request=await fetch("api/v1/usuarios/"+usuarioInput, {
        method: "DELETE",
        credentials: "same-origin",
        dataType:"json",
    });
    
    if(request.ok){
        console.log("El usuario ha sido borrado");
    }
}
   
}

borrar_boton.addEventListener("click",(borrarUsuario));

//HACER ADMIN
let hacerAdminBoton = document.getElementById("hacerAdminBoton");

const hacerAdmin= async ()=>{
    console.log("Este usuario es admin ahora");

    
    let usuarioInput=document.getElementById("buscarUsuario").value;

    let request=await fetch("/api/v1/usuarios/"+usuarioInput+"/admin", {
        method: "PUT",
        credentials: "same-origin",
        headers:{
            "Content-Type":"application/json",
        },
        body:JSON.stringify({
            estado:"admin",                     //Si fuera algo un int Integer.parseInt(inputNumero),
        }),
        dataType:"json",
    });
    if(request.ok){
        console.log(await request.json());
    }
}

hacerAdminBoton.addEventListener("click",(hacerAdmin));


//BLOQUEAR USUARIO
let bloquearboton = document.getElementById("bloquearBoton");

const bloquearUsuario= async ()=>{
    console.log("Este usuario está bloqueado");

    
    let usuarioInput=document.getElementById("buscarUsuario").value;

    if(usuarioInput=="dioni_martin"){
        console.log("No puedes bloquear a esta leyenda");
        document.getElementById("idDesplegable").innerHTML=("No puedes bloquear a esta leyenda");
        document.getElementById("correoDesplegable").innerHTML=("");
        document.getElementById("contrasenyaDesplegable").innerHTML=("");
        document.getElementById("estadoDesplegable").innerHTML=(""); 
    }else{
        let request=await fetch("/api/v1/usuarios/"+usuarioInput+"/bloq", {
            method: "PUT",
            credentials: "same-origin",
            headers:{
                "Content-Type":"application/json",
            },
            body:JSON.stringify({
                estado:"bloq",                     //Si fuera algo un int Integer.parseInt(inputNumero),
            }),
            dataType:"json",
        });
        if(request.ok){
            console.log(await request.json());
        }
    }
}

bloquearboton.addEventListener("click",(bloquearUsuario));

//HACER USUARIO
let usuarioBoton = document.getElementById("hacerUserBoton");

const hacerUsuario= async ()=>{
    console.log("Este usuario está bloqueado");

    
    let usuarioInput=document.getElementById("buscarUsuario").value;
    if(usuarioInput=="dioni_martin"){
        console.log("No puedes infravalorar a esta bestia");
        document.getElementById("idDesplegable").innerHTML=("No puedes infravalorar a esta leyenda");
        document.getElementById("correoDesplegable").innerHTML=("");
        document.getElementById("contrasenyaDesplegable").innerHTML=("");
        document.getElementById("estadoDesplegable").innerHTML=(""); 
    }else{

        let request=await fetch("/api/v1/usuarios/"+usuarioInput+"/user", {
            method: "PUT",
            credentials: "same-origin",
            headers:{
                "Content-Type":"application/json",
            },
            body:JSON.stringify({
                estado:"bloq",                     //Si fuera algo un int Integer.parseInt(inputNumero),
            }),
            dataType:"json",
        });
        if(request.ok){
            console.log(await request.json());
        }
    }
}

usuarioBoton.addEventListener("click",(hacerUsuario));





