package com.pat.aviones.controller;


import com.pat.aviones.model.Usuario;
import com.pat.aviones.repository.UsuarioRepository;
import com.pat.aviones.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    //Get de todos los usuarios
    @GetMapping("/usuarios")
    public ResponseEntity<Iterable<Usuario>> retrieveUsers(@RequestParam(required=false) String estado) {
        
        Iterable<Usuario> response = usuarioService.getUsuarios(estado);
        return ResponseEntity.ok().body(response);
    }

    //Get de solamente el usuario con ese id
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> getUser(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(usuarioService.getUserById(id));
    }

    //Meter un usuario
    @PostMapping("/usuarios")
    public ResponseEntity<String> createUserById(
        @RequestBody Usuario usuario,
        BindingResult bindingResult){

        if(bindingResult.hasErrors()){
        return new ResponseEntity<String>("{\"result\" : \"KO\"}", HttpStatus.BAD_REQUEST);
        }else{
            usuarioService.createUserService(usuario);
        return new ResponseEntity<String>("{\"result\" : \"OK\"}", HttpStatus.OK);
    }

  }

    //Eliminar un usuario con ese id
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable String id) {
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
   
    //Actualizar todos los parámetros de un usuario
    @PutMapping("/usuarios")
    public ResponseEntity<Usuario> updateUser( @RequestBody Usuario usuario) {
        Usuario newUsuario = usuarioRepository.save(usuario);
        if (newUsuario == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok().body(newUsuario);
    }
    
  //Actualizar solamente el nombre de un usuario
    @PutMapping("/usuarios/{id}/{estado}")
    public ResponseEntity<String> updateUserEstado(@PathVariable String id,@PathVariable String estado) {

        Usuario newUsuario = usuarioService.getUserById(id);
        newUsuario.setEstado(estado);
        usuarioRepository.deleteById(id);
        usuarioRepository.crearUsuario(newUsuario.getId(),newUsuario.getCorreo(),newUsuario.getContrasenya(),newUsuario.getEstado());
        
        return new ResponseEntity<String>("{\"result\" : \"OK\"}", HttpStatus.OK);

    }
 
    
}
