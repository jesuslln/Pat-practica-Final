let registrarse_boton = document.getElementById("boton_registrarse");

registrarse_boton.addEventListener(
    "click",
    (postUsuario = async () => {
      //Obtenemos los datos del formulario
      let id = document.getElementById("nombreUsuarioRegistro").value;
      let correo = document.getElementById("correoRegistro").value;
      let contrasenya = document.getElementById("contrasenyaRegistro").value;
      let estado="user";
  
      //Ahora debemos de hacer un fetch de contactos ?
      let request = await fetch("/api/v1/usuarios", {
        method: "POST",
        credentials: "same-origin",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
            id: id,
            correo: correo,
            contrasenya: contrasenya,
            estado: estado,
        }),
        dataType: "json",
      });
  
      if (request.ok) {
        console.log("Success!");
      }
    })
  );

