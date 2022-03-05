package com.example.guerraalantico.Persistencia.Consultas;

import org.springframework.stereotype.Service;

@Service
public class Consultas {

  public String obtenerUsuarioPorUsuarioContrasenia() {
    return "SELECT * FROM GuerraAtlantico.Usuarios " +
        "WHERE UsuNombreUsuario = ? " +
        "AND UsuContrasenia = ?; ";

  }

  public String guardarPartida(){
    return "CALL GuerraAtlantico.GuardarDatosEquipo(?, ?, ?, ?, ?, ?, ?, ?);";
  }

  public String obtenerUsuarioPorUsuario() {
    return 	"SELECT * FROM GuerraAtlantico.Usuarios " +
        "WHERE UsuNombreUsuario = ?; ";
  }

  public String obtenerEquiposPorPartida() {
    return 	"SELECT * FROM GuerraAtlantico.Equipos " +
        "WHERE ParIdPartida = ?;";
  }


  public String obtenerPartidasPorUsario() {
   return "SELECT P.* FROM GuerraAtlantico.Equipos E "+
          "JOIN GuerraAtlantico.Partidas P ON P.ParIdPartida = E.ParIdPartida "+
          "JOIN GuerraAtlantico.Usuarios U ON U.UsuIdUsuario = E.UsuIdUsuario "+
          "WHERE U.UsuNombreUsuario = ? AND P.ParFinalizada = 0 AND P.ParFechaGuardado IS NOT NULL;";

  }

  public String obtenerPartidaporId() {
    return  "SELECT * FROM GuerraAtlantico.Partidas WHERE ParIdPartida = ?;";
  }


  public String obtenerNavesPorEquipo() {
    return	"SELECT * FROM GuerraAtlantico.RelEquiposNaves R "+
            "INNER JOIN GuerraAtlantico.Naves N ON N.NavIdNave = R.NavIdNave "+
            "WHERE EquIdEquipo = ?;";
  }

  public String obtenerNavesPorCodigo() {

    return  "SELECT * FROM GuerraAtlantico.Naves " +
        "WHERE NavIdNave = ?;";

  }

  public String altaUsuario() {
    return	"INSERT INTO GuerraAtlantico.Usuarios (UsuNombreUsuario, UsuContrasenia) VALUES(?, ?)";
  }

  public String altaPartida() {
    return 	"CALL GuerraAtlantico.AltaPartida(?, ?, ?)";
  }


  public String finalizarPartida(){
    return "UPDATE GuerraAtlantico.Partidas SET ParFechaGuardado = NOW(), ParFinalizada = 1 " +
            "WHERE ParIdPartida = ?;";
  }

}
