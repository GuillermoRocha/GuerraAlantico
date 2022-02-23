package com.example.guerraalantico.Persistencia.DAO;

import com.example.guerraalantico.Excepciones.PersistenciaException;
import com.example.guerraalantico.Logica.Entidades.Usuario;
import com.example.guerraalantico.Persistencia.Consultas.Consultas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UsuarioBD {

  @Autowired
  private Consultas consultas;

  @Value("${url}")
  private String url;

  @Value("${usuario}")
  private String user;

  @Value("${password}")
  private String password;


  public Usuario obtenerUsuarioBD(String pUsername, String pPassword)
      throws PersistenciaException {

    Usuario usuario = new Usuario();
    try {
      Connection con = DriverManager.getConnection
          (url, user, password);

      PreparedStatement pstm;
      pstm = con.prepareStatement(consultas.obtenerUsuarioPorUsuarioContrasenia());

      pstm.setString(1,pUsername);
      pstm.setString(2,pPassword);
      ResultSet rs = pstm.executeQuery();
      if(rs.next()){
        usuario.setIdUsuario(rs.getInt("UsuIdUsuario"));
        usuario.setNombreUsuario(rs.getString("UsuNombreUsuario"));
        usuario.setContrasenia(rs.getString("UsuContrasenia"));

      }
      rs.close();
      pstm.close();
      con.close();
    }
    catch (SQLException e) {
      throw new PersistenciaException(e.getMessage());
    }
    return usuario;
  }

  public boolean existeUsuarioBD(String pUsername) throws PersistenciaException {
    boolean userExists = false;

    try {
      Connection con = DriverManager.getConnection
          (url, user, password);

      PreparedStatement pstm;
      pstm = con.prepareStatement(consultas.obtenerUsuarioPorUsuario());

      pstm.setString(1,pUsername);
      ResultSet rs = pstm.executeQuery();
      if(rs.next()){
        userExists=true;
      }
      rs.close();
      pstm.close();
      con.close();
    }
    catch (SQLException e) {
      throw new PersistenciaException(e.getMessage());
    }
    return userExists;
  }

  public void guardarUsuarioBD(String pNombreUsuario, String pPassword) throws PersistenciaException {

    try {
      Connection con = DriverManager.getConnection
          (url, user, password);

      PreparedStatement pstm = con.prepareStatement(consultas.altaUsuario());

      pstm.setString(1,pNombreUsuario);
      pstm.setString(2,pPassword);
      pstm.executeUpdate();

      pstm.close();
      con.close();
    }
    catch (SQLException e) {
      throw new PersistenciaException(e.getMessage());
    }

  }

}
