package com.example.guerraalantico.Persistencia.Consultas;

import org.springframework.stereotype.Service;

@Service
public class Consultas {

  public String obtenerUsuarioPorUsuarioContrasenia() {

    String query = 	"SELECT * FROM GuerraAtlantico.Usuarios " +
        "WHERE UsuNombreUsuario = ? " +
        "AND UsuContrasenia = ?; ";

    return query;
  }

  public String obtenerUsuarioPorUsuario() {

    String query = 	"SELECT * FROM GuerraAtlantico.Usuarios " +
        "WHERE UsuNombreUsuario = ?; ";

    return query;
  }


  public String obtenerEquiposPorUsario() {

    String query = 	"SELECT * FROM GuerraAtlantico.Equipos " +
        "WHERE UsuIdUsuario = ?;";

    return query;
  }


  public String obtenerEquiposPorPartida() {

    String query = 	"SELECT * FROM GuerraAtlantico.Equipos " +
        "WHERE ParIdPartida = ?;";

    return query;
  }


  public String obtenerPartidasPorUsario() {

    String query = 	"SELECT P.* FROM GuerraAtlantico.Equipos E " +
        "JOIN GuerraAtlantico.Partidas P ON P.ParIdPartida = E.ParIdPartida " +
        "WHERE E.UsuIdUsuario = ?;";

    return query;
  }


  public String ObtenerNavesPorEquipo() {

    String query = 	"SELECT * FROM GuerraAtlantico.RelEquiposNaves " +
        "WHERE EquIdEquipo = ?;";

    return query;
  }

  public String obtenerNavesPorCodigo() {

    String query = 	"SELECT * FROM GuerraAtlantico.Naves " +
        "WHERE NavIdNave = ?;";

    return query;
  }

  public String obtenerVistasPorNave() {

    String query = 	"SELECT * FROM GuerraAtlantico.RelNavesProfundidades " +
        "WHERE NavIdNave = ?;";

    return query;
  }


  public String obtenerArmasPorNave() {

    String query = 	"SELECT * FROM GuerraAtlantico.Armas " +
        "WHERE NavIdNave = ?;";

    return query;
  }


  public String obtenerVelocidadPorNave() {

    String query = 	"SELECT * FROM GuerraAtlantico.Velocidades " +
        "WHERE NavIdNave = ?;";

    return query;
  }


  public String altaUsuario() {

    String query = 	"{INSERT INTO GuerraAtlantico.Usuarios (UsuNombreUsuario, UsuContrasenia) VALUES(?, ?, ?)}";

    return query;
  }



  public String altaPartida() {

    String query = 	"CALL GuerraAtlantico.AltaPartida(?, ?, ?)";

    return query;
  }

  public String obtenerEquiposPorUsuarioYPartida() {

    String query = 	"SELECT * FROM GuerraAtlantico.Equipos " +
        "WHERE UsuIdUsuario = ? AND ParIdPartida = ?;";

    return query;
  }
}
