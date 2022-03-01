package com.example.guerraalantico.Logica.Servicios;


import com.example.guerraalantico.DTO.*;
import com.example.guerraalantico.DTO.request.GuardarNaveDTO;
import com.example.guerraalantico.DTO.response.PartidaAIniciarDTO;
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

      PartidaDetalladaDTO partidaDetalladaDTO = new PartidaDetalladaDTO();
      if(!existePartida(pCodigoPartida)) {
          throw new PartidaNoExisteException(String.format("La partida %s no existe en el sistema", pCodigoPartida));
      }
      else{
          partidaDetalladaDTO.setPartida(this.partidaBD.obtenerPartidaPorIdDB(pCodigoPartida));
          List<EquipoDTO> listaEquipos = this.equipoBD.obtenerEquiposPorPartidasBD(pCodigoPartida);
          partidaDetalladaDTO.setEquipos(listaEquipos);

          for (EquipoDTO equipo : listaEquipos) {
            equipo.setNaves(this.equipoBD.obtenerNavesPorEquipoBD(equipo.getIdEquipo()));
          }
      }
      return partidaDetalladaDTO;
  }


  public void guardarPartida(GuardarNaveDTO pGuardarNave){
        this.partidaBD.guardarPartidaDB(pGuardarNave);
  }


  @Override
  public void finalizarPartida(PartidaFinalzadaDTO pPartidaFinalizadaDTO)  {
      if(!existePartida(pPartidaFinalizadaDTO.getIdPartida())) {
          throw new PartidaNoExisteException(String.format("La partida %s no existe en el sistema", pPartidaFinalizadaDTO.getIdPartida()));
      } else {
          this.partidaBD.finalizarPartidaDB(pPartidaFinalizadaDTO.getIdPartida(), pPartidaFinalizadaDTO.getIdUsuarioGanador());
      }
  }


  public int altaPartida(AltaPartidaDTO pAltaPartida){

      int vRetorno;
      int vRandom = (Math.random() <= 0.5) ? 1 : 2;

      if(vRandom == 1){

          vRetorno = this.partidaBD.altaPartidaBD(pAltaPartida.getIdJugadorAzul(),
                  pAltaPartida.getIdJugadorRojo());
      }
      else{

          vRetorno = this.partidaBD.altaPartidaBD(pAltaPartida.getIdJugadorRojo(),
                   pAltaPartida.getIdJugadorAzul());
      }

    return vRetorno;
  }

  public PartidaAIniciarDTO obtenerPartidaAIniciar(){
      PartidaAIniciarDTO partidaAIniciarDTO = new PartidaAIniciarDTO();
      partidaAIniciarDTO.setIdPartida(this.partidaBD.obtenerPartidaAIniciarDB());

      List<EquipoDTO> listaEquipos = this.equipoBD.obtenerEquiposPorPartidasBD(partidaAIniciarDTO.getIdPartida());

      for (EquipoDTO vEquipo: listaEquipos) {

          if(vEquipo.getBando() == 1){
              partidaAIniciarDTO.setUsuarioEquipoUno(vEquipo.getIdUsuario());
          }
          else if (vEquipo.getBando() == 2){
              partidaAIniciarDTO.setUsuarioEquipoDos(vEquipo.getIdUsuario());
          }
      }

      return partidaAIniciarDTO;
  }

    private boolean existePartida(int pCodigoPartida){
     return this.partidaBD.existePartidaBD(pCodigoPartida);
  }

    public void habilitarPartida(int pIdPartida){
        if(!existePartida(pIdPartida)) {
            throw new PartidaNoExisteException(String.format("La partida %s no existe en el sistema", pIdPartida));
        } else {
            this.partidaBD.habilitarDeshabilitarPartidaBD(pIdPartida, 1);
        }

    }

    public void deshabilitarPartida(int pIdPartida){
        if(!existePartida(pIdPartida)) {
            throw new PartidaNoExisteException(String.format("La partida %s no existe en el sistema", pIdPartida));
        } else {
            this.partidaBD.habilitarDeshabilitarPartidaBD(pIdPartida, 0);
        }

    }

}
