package com.example.guerraalantico.Persistencia.DAO;

import com.example.guerraalantico.DTO.AlcanceVistaDTO;
import com.example.guerraalantico.DTO.ArmaDTO;
import com.example.guerraalantico.DTO.NaveDTO;
import com.example.guerraalantico.DTO.PartidaDTO;
import com.example.guerraalantico.DTO.VelocidadDTO;
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

    NaveDTO naveDTO = new NaveDTO();
    try {
      Connection con = DriverManager.getConnection
          (url, user, password);

      PreparedStatement pstm;
      pstm = con.prepareStatement(consultas.obtenerNavesPorCodigo());

      pstm.setInt(1,pCodigoNave);
      ResultSet rs = pstm.executeQuery();
      if(rs.next()){
        naveDTO.setIdNave(rs.getInt("NavIdNave"));
        naveDTO.setTipoNave(rs.getString("NavTipoNave"));
        naveDTO.setResistencia(rs.getInt("NavResistencia"));
      }
      rs.close();
      pstm.close();
      con.close();
    }
    catch (SQLException e) {
      throw new PersistenciaException(e.getMessage());
    }
    return naveDTO;

  }


  public List<VelocidadDTO> obtenerVelocidadesBD(int pCodigoNave) throws PersistenciaException {

    List<VelocidadDTO> listaVelocidades = new ArrayList<>();
    try {
      Connection con = DriverManager.getConnection
          (url, user, password);

      PreparedStatement pstm;
      pstm = con.prepareStatement(consultas.obtenerVelocidadPorNave());

      pstm.setInt(1,pCodigoNave);
      ResultSet rs = pstm.executeQuery();
      while(rs.next()){
        VelocidadDTO velocidadDTO = new VelocidadDTO(rs.getInt("VelProfundidadNave"),
            rs.getInt("VelVelocidad"));
        listaVelocidades.add(velocidadDTO);
      }
      rs.close();
      pstm.close();
      con.close();
    }
    catch (SQLException e) {
      throw new PersistenciaException(e.getMessage());
    }
    return listaVelocidades;

  }

  public List<AlcanceVistaDTO> obtenerAlcanceVistaBD(int pCodigoNave) throws PersistenciaException {

    List<AlcanceVistaDTO> listaAlcanceVistas = new ArrayList<>();
    try {
      Connection con = DriverManager.getConnection
          (url, user, password);

      PreparedStatement pstm;
      pstm = con.prepareStatement(consultas.obtenerVistasPorNave());

      pstm.setInt(1,pCodigoNave);
      ResultSet rs = pstm.executeQuery();
      while(rs.next()){
        AlcanceVistaDTO alcanceVistaDTO = new AlcanceVistaDTO(rs.getInt("NavProfundidadNave"),
            rs.getInt("NavProfundidadVista"), rs.getInt("RNPAlcanceVista"));
        listaAlcanceVistas.add(alcanceVistaDTO);
      }
      rs.close();
      pstm.close();
      con.close();
    }
    catch (SQLException e) {
      throw new PersistenciaException(e.getMessage());
    }
    return listaAlcanceVistas;

  }

  public List<ArmaDTO> obtenerArmasBD(int pCodigoNave) throws PersistenciaException {

    List<ArmaDTO> listaArmas = new ArrayList<>();
    try {
      Connection con = DriverManager.getConnection
          (url, user, password);

      PreparedStatement pstm;
      pstm = con.prepareStatement(consultas.obtenerArmasPorNave());

      pstm.setInt(1,pCodigoNave);
      ResultSet rs = pstm.executeQuery();
      while(rs.next()){
        ArmaDTO armaDTO = new ArmaDTO(rs.getInt("ArmIdArma"), rs.getString("ArmNombre"), rs.getInt("ArmDanio"),
            rs.getInt("ArmAlcanceArma"), rs.getInt("ArmProfundidadLanzamiento"), rs.getInt("ArmProfundidadImpacto"));
        listaArmas.add(armaDTO);
      }
      rs.close();
      pstm.close();
      con.close();
    }
    catch (SQLException e) {
      throw new PersistenciaException(e.getMessage());
    }
    return listaArmas;
  }

}
