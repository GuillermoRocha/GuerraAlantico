package com.example.guerraalantico.Persistencia.DAO;

import com.example.guerraalantico.DTO.AlcanceVistaDTO;
import com.example.guerraalantico.DTO.NaveDTO;
import com.example.guerraalantico.Excepciones.PersistenciaException;
import com.example.guerraalantico.Persistencia.Consultas.Consultas;
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
public class NavesBD {


  @Autowired
  private Consultas consultas;

  @Value("${url}")
  private String url;

  @Value("${usuario}")
  private String user;

  @Value("${password}")
  private String password;


  public NaveDTO obtenerNaveBD(int pCodigoNave) throws PersistenciaException {

    NaveDTO vNaveDTO = new NaveDTO();
    try {
      Connection con = DriverManager.getConnection
          (url, user, password);

      PreparedStatement pstm;
      pstm = con.prepareStatement(consultas.obtenerNavesPorCodigo());

      pstm.setInt(1,pCodigoNave);
      ResultSet rs = pstm.executeQuery();
      if(rs.next()){
        vNaveDTO.setIdTipoNave(rs.getInt("NavIdNave"));
        vNaveDTO.setTipoNave(rs.getString("NavTipoNave"));
        vNaveDTO.setResistencia(rs.getInt("NavResistencia"));
        vNaveDTO.setVelocidad(rs.getInt("NavVelocidad"));
      }
      rs.close();
      pstm.close();
      con.close();

    } catch (SQLException e) {
      throw new PersistenciaException();
    }
    return vNaveDTO;

  }


  public List<AlcanceVistaDTO> obtenerAlcanceVistaBD(int pCodigoNave) throws PersistenciaException {

    List<AlcanceVistaDTO> vListaAlcanceVistas = new ArrayList<>();
    try {
      Connection con = DriverManager.getConnection
          (url, user, password);

      PreparedStatement pstm;
      pstm = con.prepareStatement(consultas.obtenerVistasPorNave());

      pstm.setInt(1,pCodigoNave);
      ResultSet rs = pstm.executeQuery();
      while(rs.next()){
        AlcanceVistaDTO vAlcanceVistaDTO = new AlcanceVistaDTO(rs.getInt("NavProfundidadNave"),
            rs.getInt("NavProfundidadVista"), rs.getInt("RNPAlcanceVista"));
        vListaAlcanceVistas.add(vAlcanceVistaDTO);
      }
      rs.close();
      pstm.close();
      con.close();

    } catch (SQLException e) {
      throw new PersistenciaException();
    }
    return vListaAlcanceVistas;

  }

}
