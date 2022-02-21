package com.example.guerraalantico.Logica.Servicios;


import com.example.guerraalantico.DTO.AltaPartidaDTO;
import com.example.guerraalantico.DTO.EquipoDTO;
import com.example.guerraalantico.DTO.PartidaDTO;
import com.example.guerraalantico.DTO.PartidaDetalladaDTO;
import com.example.guerraalantico.Excepciones.PersistenciaException;
import com.example.guerraalantico.Persistencia.DAO.EquipoBD;
import com.example.guerraalantico.Persistencia.DAO.PartidaBD;
import com.fasterxml.jackson.databind.ObjectMapper;
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
      PartidaDetalladaDTO partidaDetalladaDTO  = objectMapper.convertValue(partidaBD.obtenerPartidaPorIdDB(pCodigoPartida),
          PartidaDetalladaDTO.class);
      List<EquipoDTO> listaEquipos = equipoBD.obtenerEquiposPorPartidasBD(pCodigoPartida);
      partidaDetalladaDTO.setEquipos(listaEquipos);

      return partidaDetalladaDTO;

    }catch (PersistenciaException e){
    }

    return null;
  }

  public void guardarPartida(PartidaDetalladaDTO pPartidaDetallada){

    try{
      partidaBD.guardarPartidaDB(pPartidaDetallada.getPartida());
      for (EquipoDTO equipo : pPartidaDetallada.getEquipos()
      ) {
        servicioEquipo.guardarEquipo(equipo);
      }
    }catch (PersistenciaException e){
    }

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
