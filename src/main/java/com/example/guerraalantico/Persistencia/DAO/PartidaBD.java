package com.example.guerraalantico.Persistencia.DAO;

import com.example.guerraalantico.DTO.PartidaDTO;
import com.example.guerraalantico.DTO.PartidaDetalladaDTO;
import com.example.guerraalantico.DTO.request.GuardarNaveDTO;
import com.example.guerraalantico.Excepciones.PersistenciaException;
import com.example.guerraalantico.Logica.Entidades.PartidaDetallada;
import com.example.guerraalantico.Persistencia.Consultas.Consultas;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PartidaBD {

  @Autowired
  private Consultas consultas;

  @Value("${url}")
  private String url;

  @Value("${usuario}")
  private String user;

  @Value("${password}")
  private String password;


  public List<PartidaDTO> obtenerPartidasBD(String pUsername) throws PersistenciaException {

    List<PartidaDTO> listaPartidas = new ArrayList<>();
    try {
      Connection con = DriverManager.getConnection
          (url, user, password);

      PreparedStatement pstm;
      pstm = con.prepareStatement(consultas.obtenerPartidasPorUsario());

      pstm.setString(1,pUsername);
      ResultSet rs = pstm.executeQuery();
      while(rs.next()){
        PartidaDTO partidaDTO = new PartidaDTO(rs.getInt("ParIdPartida"), rs.getDate("ParFechaAlta"),
                rs.getDate("ParFechaGuardado"), rs.getBoolean("ParFinalizada"), rs.getString("UsuNombreUsuario"));
        listaPartidas.add(partidaDTO);
      }
      rs.close();
      pstm.close();
      con.close();

    } catch (SQLException e) {
        throw new PersistenciaException();
    }
    return listaPartidas;

  }

  public int altaPartidaBD(int pIdUsuarioAzul, int pIdUsuarioRojo) throws PersistenciaException {

    int vCodigoPartida = 0;
    try {
      Connection con = DriverManager.getConnection
          (url, user, password);

      CallableStatement cstmt = con.prepareCall(consultas.altaPartida());
      cstmt.setInt(1, pIdUsuarioAzul);
      cstmt.setInt(2, pIdUsuarioRojo);
      cstmt.registerOutParameter(3, java.sql.Types.INTEGER);

     cstmt.executeUpdate();
     vCodigoPartida = cstmt.getInt(3); // index-based

      cstmt.close();
      con.close();

    } catch (SQLException e) {
       throw new PersistenciaException();
    }
    return vCodigoPartida;
  }

  public void guardarPartidaDB(GuardarNaveDTO pGuardarNavea) throws PersistenciaException {

    try {
      Connection con = DriverManager.getConnection
          (url, user, password);

      CallableStatement cstmt = con.prepareCall(consultas.guardarPartida());
      cstmt.setInt(1, pGuardarNavea.getIdEquipo());
      cstmt.setInt(2, pGuardarNavea.getIdNave());
      cstmt.setInt(3, pGuardarNavea.getCoordenadasX());
      cstmt.setInt(4, pGuardarNavea.getCoordenadaY());
      cstmt.setInt(5, pGuardarNavea.getResistencia());
      cstmt.setInt(6, pGuardarNavea.getProfundidadActual());

      cstmt.executeUpdate();

      cstmt.close();
      con.close();

    } catch (SQLException e) {
        throw new PersistenciaException();
    }
  }

  public PartidaDTO obtenerPartidaPorIdDB(int pCodigoPartida) throws PersistenciaException {

    PartidaDTO partidaDTO = new PartidaDTO();
    try {
      Connection con = DriverManager.getConnection
          (url, user, password);

      PreparedStatement pstm = con.prepareStatement(consultas.obtenerPartidaporId());
      pstm.setInt(1,pCodigoPartida);

      ResultSet rs = pstm.executeQuery();
      if(rs.next()){
        partidaDTO.setIdPartida(rs.getInt("ParIdPartida"));
        partidaDTO.setFechaAlta(rs.getDate("ParFechaAlta"));
        partidaDTO.setFechaGuardado(rs.getDate("ParFechaGuardado"));
        partidaDTO.setFinalizada(rs.getBoolean("ParFinalizada"));
      }
      rs.close();
      pstm.close();
      con.close();
    }
    catch (SQLException e) {
      throw new PersistenciaException();
    }
    return partidaDTO;
  }


  public void finalizarPartidaDB(int pidPartida, int pIdUsuarioGanador) throws PersistenciaException {

    try {
      Connection con = DriverManager.getConnection
              (url, user, password);
      PreparedStatement pstm = con.prepareStatement(consultas.finalizarPartida());
      pstm.setInt(1, pidPartida);
      pstm.setInt(2, pIdUsuarioGanador);

      pstm.executeUpdate();

      pstm.close();
      con.close();

    } catch (SQLException e) {
         throw new PersistenciaException();
    }
  }

    public boolean existePartidaBD (int pCodigoPartida){

      boolean existe;

      try {
        Connection con = DriverManager.getConnection
                (url, user, password);

        PreparedStatement pstm = con.prepareStatement(consultas.obtenerPartidaporId());
        pstm.setInt(1, pCodigoPartida);

        ResultSet rs = pstm.executeQuery();
        existe = rs.next();

        rs.close();
        pstm.close();
        con.close();
      } catch (SQLException e) {
          throw new PersistenciaException();
      }
      return existe;

    }

}
