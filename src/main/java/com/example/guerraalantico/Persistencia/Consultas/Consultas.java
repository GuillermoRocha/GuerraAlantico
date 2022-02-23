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
    return "CALL GuerraAtlantico.GuardarDatosEquipo(?, ?, ?, ?, ?, ?);";
  }

  public String obtenerUsuarioPorUsuario() {
    return 	"SELECT * FROM GuerraAtlantico.Usuarios " +
        "WHERE UsuNombreUsuario = ?; ";
  }


  public String obtenerEquiposPorUsario() {
    return 	"SELECT * FROM GuerraAtlantico.Equipos " +
        "WHERE UsuIdUsuario = ?;";
  }


  public String obtenerEquiposPorPartida() {
    return 	"SELECT * FROM GuerraAtlantico.Equipos " +
        "WHERE ParIdPartida = ?;";
  }


  public String obtenerPartidasPorUsario() {
   return  "SELECT P.*, U2.UsuNombreUsuario FROM GuerraAtlantico.Equipos E "+
    "JOIN GuerraAtlantico.Partidas P ON P.ParIdPartida = E.ParIdPartida "+
    "JOIN GuerraAtlantico.Usuarios U ON U.UsuIdUsuario = E.UsuIdUsuario "+
    "LEFT JOIN GuerraAtlantico.Usuarios U2 ON U2.UsuIdUsuario = P.UsuIdUsuarioGanador "+
    "WHERE U.UsuNombreUsuario = ?;";
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

  public String obtenerVistasPorNave() {

    return 	"SELECT * FROM GuerraAtlantico.RelNavesProfundidades " +
        "WHERE NavIdNave = ?;";
  }


  public String obtenerArmasPorNave() {
    return	"SELECT * FROM GuerraAtlantico.Armas " +
        "WHERE NavIdNave = ?;";
  }


  public String obtenerVelocidadPorNave() {
    return	"SELECT * FROM GuerraAtlantico.Velocidades " +
        "WHERE NavIdNave = ?;";
  }


  public String altaUsuario() {
    return	"INSERT INTO GuerraAtlantico.Usuarios (UsuNombreUsuario, UsuContrasenia) VALUES(?, ?)";
  }



  public String altaPartida() {
    return 	"CALL GuerraAtlantico.AltaPartida(?, ?, ?)";
  }

  public String obtenerEquiposPorUsuarioYPartida() {
    return	"SELECT * FROM GuerraAtlantico.Equipos " +
        "WHERE UsuIdUsuario = ? AND ParIdPartida = ?;";
  }

  public String finalizarPartida(){
    return "UPDATE GuerraAtlantico.Partidas SET ParFechaGuardado = NOW(), ParFinalizada = 1, UsuIdUsuarioGanador = ? " +
            "WHERE ParIdPartida = ?;";
  }

}