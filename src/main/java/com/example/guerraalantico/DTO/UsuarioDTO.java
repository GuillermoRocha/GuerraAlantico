package com.example.guerraalantico.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

  private int idUsuario;
  private String nombreUsuario;
  private String contrasenia;

}
