package com.example.guerraalantico.Persistencia.DAO;

import com.example.guerraalantico.DTO.PartidaDTO;
import com.example.guerraalantico.Excepciones.PersistenciaException;
import com.example.guerraalantico.Logica.Entidades.PartidaDetallada;
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


  public List<PartidaDTO> obtenerPartidasBD(String pUsername) throws PersistenciaException {

    List<PartidaDTO> listaPartidas = new ArrayList<>();
    try {
      Connection con = DriverManager.getConnection
          (url, user, password);

      PreparedStatement pstm;
      pstm = con.prepareStatement(consultas.obtenerPartidasPorUsario());

      pstm.setString(1,pUsername);
      ResultSet rs = pstm.executeQuery();
      if(rs.next()){
        PartidaDTO partidaDTO = new PartidaDTO();
        listaPartidas.add(partidaDTO);
      }
      rs.close();
      pstm.close();
      con.close();
    }
    catch (SQLException e) {
      throw new PersistenciaException(e.getMessage());
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
    }
    catch (SQLException e) {
      throw new PersistenciaException(e.getMessage());
    }
    return vCodigoPartida;
  }

  public void guardarPartidaDB(PartidaDTO pPartida) throws PersistenciaException {

//    try {
//      Connection con = DriverManager.getConnection
//          (url, user, password);
//
//      PreparedStatement pstm;
//      pstm = con.prepareStatement(consultas.());
//
//      pstm.setDate(1, pPartida.getFechaAlta());
//      pstm.setDate(2, pPartida.getFechaGuardado());
//      pstm.setBoolean(3, pPartida.isFinalizada());
//      pstm.setInt(4, pPartida.getIdUsuarioGanador());
//      ResultSet rs = pstm.executeUpdate();
//      if(rs.next()){
//      }
//      rs.close();
//      pstm.close();
//      con.close();
//    }
//    catch (SQLException e) {
//      throw new PersistenciaException(e.getMessage());
//    }

  }

  public PartidaDetallada obtenerPartidaPorIdDB(int pCodigoPartida) throws PersistenciaException {

    PartidaDetallada partidaDetallada = new PartidaDetallada();
    try {
      Connection con = DriverManager.getConnection
          (url, user, password);

      PreparedStatement pstm;
      pstm = con.prepareStatement(consultas.altaPartida());

      pstm.setInt(1,pCodigoPartida);
      ResultSet rs = pstm.executeQuery();
      if(rs.next()){

      }
      rs.close();
      pstm.close();
      con.close();
    }
    catch (SQLException e) {
      throw new PersistenciaException(e.getMessage());
    }
    return partidaDetallada;
  }


}
