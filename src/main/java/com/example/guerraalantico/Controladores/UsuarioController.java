package com.example.guerraalantico.Controladores;

import com.example.guerraalantico.DTO.UsuarioDTO;
import com.example.guerraalantico.Excepciones.PersistenciaException;
import com.example.guerraalantico.Excepciones.UsuarioExistenteException;
import com.example.guerraalantico.Logica.Servicios.IServicioUsuario;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

  @Autowired
  IServicioUsuario servicioUsuario;

  @Operation(summary = "Dar de alta un usuario")
  @PostMapping("/usuario/alta")
  public void guardarUsuario(@RequestBody UsuarioDTO pUsuario) throws PersistenciaException, UsuarioExistenteException {
    servicioUsuario.guardarUsuario(pUsuario);
  }

  @Operation(summary = "Obtener un usuario")
  @PostMapping("/usuario")
  public UsuarioDTO obtenerUsuario(@RequestBody UsuarioDTO pUsuario){

    return this.servicioUsuario.obtenerUsuario(pUsuario);
//    if(usuarioDTO.getIdUsuario() != 0)
//    {
//      return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
//    }
//    else
//      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

}
