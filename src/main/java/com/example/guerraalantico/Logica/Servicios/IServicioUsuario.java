package com.example.guerraalantico.Logica.Servicios;

import com.example.guerraalantico.DTO.UsuarioDTO;

public interface IServicioUsuario {

  void guardarUsuario(UsuarioDTO pUsuario);

  UsuarioDTO obtenerUsuario(UsuarioDTO pUsuario);

  boolean existeUsuarioPorNombre(String pNombreUsuario);


}
