package com.example.guerraalantico.Persistencia.DAO;

import com.example.guerraalantico.DTO.PartidaDTO;
import com.example.guerraalantico.DTO.GuardarNaveDTO;
import com.example.guerraalantico.Excepciones.PersistenciaException;
import com.example.guerraalantico.Persistencia.Consultas.Consultas;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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


  public List<PartidaDTO> obtenerPartidasBD(int pIdUsuario) throws PersistenciaException {

    List<PartidaDTO> vListaPartidas = new ArrayList<>();
    try {
      Connection con = DriverManager.getConnection
          (url, user, password);

      PreparedStatement pstm;
      pstm = con.prepareStatement(consultas.obtenerPartidasPorUsario());

      pstm.setInt(1,pIdUsuario);
      pstm.setInt(2,pIdUsuario);
      ResultSet rs = pstm.executeQuery();
      while(rs.next()){
        PartidaDTO vPartidaDTO = new PartidaDTO(rs.getInt("ParIdPartida"), rs.getDate("ParFechaAlta"),
                rs.getDate("ParFechaGuardado"), rs.getBoolean("ParFinalizada"), rs.getString("Rival"));
        vListaPartidas.add(vPartidaDTO);
      }
      rs.close();
      pstm.close();
      con.close();

    } catch (SQLException e) {
        throw new PersistenciaException();
    }
    return vListaPartidas;

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

  public void guardarPartidaDB(GuardarNaveDTO pGuardarNave) throws PersistenciaException {

    try {
      Connection con = DriverManager.getConnection
          (url, user, password);

      CallableStatement cstmt = con.prepareCall(consultas.guardarPartida());
      cstmt.setInt(1, pGuardarNave.getIdBando());
      cstmt.setInt(2, pGuardarNave.getIdPartida());
      cstmt.setInt(3, pGuardarNave.getIdNave());
      cstmt.setInt(4, pGuardarNave.getCoordenadaX());
      cstmt.setInt(5, pGuardarNave.getCoordenadaY());
      cstmt.setInt(6, pGuardarNave.getResistencia());
      cstmt.setInt(7, pGuardarNave.getProfundidadActual());
      cstmt.setFloat(8, pGuardarNave.getRotacion());

      cstmt.executeUpdate();

      cstmt.close();
      con.close();

    } catch (SQLException e) {
        throw new PersistenciaException();
    }
  }

  public PartidaDTO obtenerPartidaPorIdDB(int pCodigoPartida) throws PersistenciaException {

    PartidaDTO vPartidaDTO = new PartidaDTO();
    try {
      Connection con = DriverManager.getConnection
          (url, user, password);

      PreparedStatement pstm = con.prepareStatement(consultas.obtenerPartidaporId());
      pstm.setInt(1,pCodigoPartida);

      ResultSet rs = pstm.executeQuery();
      if(rs.next()){
        vPartidaDTO.setIdPartida(rs.getInt("ParIdPartida"));
        vPartidaDTO.setFechaAlta(rs.getDate("ParFechaAlta"));
        vPartidaDTO.setFechaGuardado(rs.getDate("ParFechaGuardado"));
        vPartidaDTO.setFinalizada(rs.getBoolean("ParFinalizada"));
      }
      rs.close();
      pstm.close();
      con.close();
    }
    catch (SQLException e) {
      throw new PersistenciaException();
    }
    return vPartidaDTO;
  }


  public void finalizarPartidaDB(int pidPartida) throws PersistenciaException {

    try {
      Connection con = DriverManager.getConnection
              (url, user, password);
      PreparedStatement pstm = con.prepareStatement(consultas.finalizarPartida());
      pstm.setInt(1, pidPartida);

      pstm.executeUpdate();

      pstm.close();
      con.close();

    } catch (SQLException e) {
         throw new PersistenciaException();
    }
  }

    public boolean existePartidaBD (int pCodigoPartida){
      boolean vExiste;

      try {
        Connection con = DriverManager.getConnection
                (url, user, password);

        PreparedStatement pstm = con.prepareStatement(consultas.obtenerPartidaporId());
        pstm.setInt(1, pCodigoPartida);

        ResultSet rs = pstm.executeQuery();
        vExiste = rs.next();

        rs.close();
        pstm.close();
        con.close();
      } catch (SQLException e) {
          throw new PersistenciaException();
      }
      return vExiste;

    }

}
