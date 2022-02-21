package com.example.guerraalantico.Logica.Servicios;

import com.example.guerraalantico.DTO.UsuarioDTO;

public interface IServicioUsuario {

  void guardarUsuario(UsuarioDTO pUsuario);

  boolean existeUsuario(String pNombreUsuario);

  UsuarioDTO obtenerUsuario(UsuarioDTO pUsuario);


}
