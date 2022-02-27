package com.example.guerraalantico.Logica.Servicios;


import com.example.guerraalantico.DTO.UsuarioDTO;
import com.example.guerraalantico.Excepciones.UsuarioExistenteException;
import com.example.guerraalantico.Excepciones.UsuarioNoExisteException;
import com.example.guerraalantico.Persistencia.DAO.UsuarioBD;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioUsuario implements IServicioUsuario {

  @Autowired
  private UsuarioBD usuarioBD;

  @Autowired
  private ObjectMapper objectMapper;


  @Override
  public void guardarUsuario(UsuarioDTO pUsuario)  {

      if(!existeUsuarioPorNombre(pUsuario.getNombreUsuario())) {
          this.usuarioBD.guardarUsuarioBD(pUsuario.getNombreUsuario(), pUsuario.getContrasenia());
      } else {
          throw new UsuarioExistenteException(String.format("Usuario %s en uso, ingrese otro nombre.",
                  pUsuario.getNombreUsuario()));
      }
  }

  public UsuarioDTO obtenerUsuario(UsuarioDTO pUsuario) {
      if(existeUsuarioPorNombreYContrasenia(pUsuario)) {
          return objectMapper.convertValue(
                  this.usuarioBD.obtenerUsuarioBD(pUsuario.getNombreUsuario(),
                          pUsuario.getContrasenia()), UsuarioDTO.class);
      } else {
          throw new UsuarioNoExisteException(String.format("No se reconoce el usuario/contraseña ingresado.",
                  pUsuario.getNombreUsuario()));
      }
  }

    public boolean existeUsuarioPorNombre(String pNombreUsuario){
        return this.usuarioBD.existeUsuarioBD(pNombreUsuario);
    }

    private boolean existeUsuarioPorNombreYContrasenia(UsuarioDTO pUsuario){
        return this.usuarioBD.existeUsuarioPorNombreYContraseniaBD(pUsuario);
    }


}
