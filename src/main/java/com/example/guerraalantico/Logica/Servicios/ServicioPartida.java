package com.example.guerraalantico.Logica.Servicios;


import com.example.guerraalantico.DTO.*;
import com.example.guerraalantico.DTO.request.GuardarNaveDTO;
import com.example.guerraalantico.Excepciones.PartidaNoExisteException;
import com.example.guerraalantico.Excepciones.UsuarioNoExisteException;
import com.example.guerraalantico.Persistencia.DAO.EquipoBD;
import com.example.guerraalantico.Persistencia.DAO.PartidaBD;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioPartida implements IServicioPartida {

  @Autowired
  private PartidaBD partidaBD;

  @Autowired
  private EquipoBD equipoBD;

  @Autowired
  private IServicioUsuario servicioUsuario;


  public List<PartidaDTO> obtenerPartidasPorUsuarioARetomar(String pNombreUsuario){
      if(servicioUsuario.existeUsuarioPorNombre(pNombreUsuario))
          return this.partidaBD.obtenerPartidasBD(pNombreUsuario);
      else
          throw new UsuarioNoExisteException(String.format("El usuario %s no existe", pNombreUsuario));
  }


  public PartidaDetalladaDTO retomarPartida(int pCodigoPartida){

      PartidaDetalladaDTO vPartidaDetalladaDTO = new PartidaDetalladaDTO();
      if(!existePartida(pCodigoPartida)) {
          throw new PartidaNoExisteException(String.format("La partida %s no existe en el sistema", pCodigoPartida));
      }
      else{
          vPartidaDetalladaDTO.setPartida(this.partidaBD.obtenerPartidaPorIdDB(pCodigoPartida));
          List<EquipoDTO> vListaEquipos = this.equipoBD.obtenerEquiposPorPartidasBD(pCodigoPartida);
          vPartidaDetalladaDTO.setEquipos(vListaEquipos);

          for (EquipoDTO vEquipo : vListaEquipos) {
              vEquipo.setNaves(this.equipoBD.obtenerNavesPorEquipoBD(vEquipo.getIdEquipo()));
          }
      }
      return vPartidaDetalladaDTO;
  }


  public void guardarPartida(GuardarNaveDTO pGuardarNave){
        this.partidaBD.guardarPartidaDB(pGuardarNave);
  }

  @Override
  public void finalizarPartida(int pIdPartida)  {
      if(!existePartida(pIdPartida)) {
          throw new PartidaNoExisteException(String.format("La partida %s no existe en el sistema", pIdPartida));
      } else {
          this.partidaBD.finalizarPartidaDB(pIdPartida);
      }
  }

  public int altaPartida(AltaPartidaDTO pAltaPartida){
    return this.partidaBD.altaPartidaBD(pAltaPartida.getIdJugadorAzul(), pAltaPartida.getIdJugadorRojo());
  }

    private boolean existePartida(int pCodigoPartida){
     return this.partidaBD.existePartidaBD(pCodigoPartida);
  }

}
