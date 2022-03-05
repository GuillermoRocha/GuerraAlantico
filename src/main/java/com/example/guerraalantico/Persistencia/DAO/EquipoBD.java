package com.example.guerraalantico.Persistencia.DAO;

import com.example.guerraalantico.DTO.EquipoDTO;
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
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class EquipoBD {

  @Autowired
  private Consultas consultas;

  @Value("${url}")
  private String url;

  @Value("${usuario}")
  private String user;

  @Value("${password}")
  private String password;


  public  List<EquipoDTO> obtenerEquiposPorPartidasBD(int pCodigoPartida) throws PersistenciaException {

    List<EquipoDTO> vListaEquipos = new ArrayList<>();

    try {
      Connection con = DriverManager.getConnection
          (url, user, password);

      PreparedStatement pstm;
      pstm = con.prepareStatement(consultas.obtenerEquiposPorPartida());

      pstm.setInt(1,pCodigoPartida);
      ResultSet rs = pstm.executeQuery();
      while(rs.next()){
        EquipoDTO vEquipoDTO = new EquipoDTO(rs.getInt("EquIdEquipo"), rs.getInt("BanIdBando"), rs.getInt("UsuIdUsuario"));
        vListaEquipos.add(vEquipoDTO);
      }
      rs.close();
      pstm.close();
      con.close();
    }
    catch (SQLException e) {
      throw new PersistenciaException(e.getMessage());
    }
    return vListaEquipos;
  }

  public  List<NaveDTO> obtenerNavesPorEquipoBD(int pCodigoEquipo) throws PersistenciaException {

    List<NaveDTO> vListaNaves = new ArrayList<>();

    try {
      Connection con = DriverManager.getConnection
              (url, user, password);

      PreparedStatement pstm;
      pstm = con.prepareStatement(consultas.obtenerNavesPorEquipo());

      pstm.setInt(1,pCodigoEquipo);
      ResultSet rs = pstm.executeQuery();
      while(rs.next()){
        NaveDTO vNaveDTO = new NaveDTO(rs.getInt("NavIdNave"), rs.getString("NavTipoNave"),
                rs.getInt("RENCoordenadasX"), rs.getInt("RenCoordenadasY"),
                rs.getInt("RENResistenciaActual"), rs.getInt("RenProfundidadActual"),
                rs.getInt("NavVelocidad"), rs.getFloat("RENRotacion"));
        vListaNaves.add(vNaveDTO);
      }
      rs.close();
      pstm.close();
      con.close();
    }
    catch (SQLException e) {
      throw new PersistenciaException(e.getMessage());
    }
    return vListaNaves;
  }


}
