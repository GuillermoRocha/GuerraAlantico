package com.example.guerraalantico.Logica.Servicios;


import com.example.guerraalantico.DTO.UsuarioDTO;
import com.example.guerraalantico.Excepciones.PersistenciaException;
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
   try{
    this.usuarioBD.guardarUsuarioBD(pUsuario.getNombreUsuario(), pUsuario.getContrasenia());
   }catch (PersistenciaException e){
   }
  }

  @Override
  public boolean existeUsuario(String pNombreUsuario){
    try{
    return this.usuarioBD.existeUsuarioBD(pNombreUsuario);
    }
    catch (PersistenciaException e){
    }
    return false;
  }


  public UsuarioDTO obtenerUsuario(UsuarioDTO pUsuario)  {
    try {
      return objectMapper.convertValue(
          this.usuarioBD.obtenerUsuarioBD(pUsuario.getNombreUsuario(), pUsuario.getContrasenia()), UsuarioDTO.class);
    }
    catch (PersistenciaException e) {
    }
    return  null;
  }
}
