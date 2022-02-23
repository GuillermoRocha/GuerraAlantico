package com.example.guerraalantico.Logica.Servicios;


import com.example.guerraalantico.DTO.*;
import com.example.guerraalantico.DTO.request.GuardarNaveDTO;
import com.example.guerraalantico.Excepciones.PersistenciaException;
import com.example.guerraalantico.Persistencia.DAO.EquipoBD;
import com.example.guerraalantico.Persistencia.DAO.PartidaBD;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.Collections;
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
  private IServicioEquipo servicioEquipo;

  @Autowired
  private ObjectMapper objectMapper;


  public List<PartidaDTO> obtenerPartidas(String pNombreUsuario){
   try
   {
     return partidaBD.obtenerPartidasBD(pNombreUsuario);
   }catch (PersistenciaException e){
   }
   return Collections.emptyList();
  }

  public PartidaDetalladaDTO retomarPartida(int pCodigoPartida){
    try
    {
      PartidaDetalladaDTO partidaDetalladaDTO = new PartidaDetalladaDTO();
      partidaDetalladaDTO.setPartida(partidaBD.obtenerPartidaPorIdDB(pCodigoPartida));
      List<EquipoDTO> listaEquipos = equipoBD.obtenerEquiposPorPartidasBD(pCodigoPartida);
      partidaDetalladaDTO.setEquipos(listaEquipos);

      for (EquipoDTO equipo : listaEquipos) {
        equipo.setNaves(equipoBD.obtenerNavesPorEquipoBD(equipo.getIdEquipo()));
      }
      return partidaDetalladaDTO;

    }catch (PersistenciaException e){
    }

    return null;
  }

  public void guardarPartida(GuardarNaveDTO pGuardarNave){

    try{
        partidaBD.guardarPartidaDB(pGuardarNave);
    }catch (PersistenciaException e){
    }

  }

  @Override
  public void finalizarPartida(PartidaFinalzadaDTO pPartidaFinalizadaDTO) throws SQLException {
    partidaBD.finalizarPartidaDB(pPartidaFinalizadaDTO.getIdPartida(), pPartidaFinalizadaDTO.getIdUsuarioGanador());
  }

  public int altaPartida(AltaPartidaDTO pAltaPartida){

    int resultado = 0;
    try{
         resultado =  partidaBD.altaPartidaBD(pAltaPartida.getIdJugadorAzul(),
             pAltaPartida.getIdJugadorRojo());
   }catch (PersistenciaException e){}

    return resultado;
  }

}
