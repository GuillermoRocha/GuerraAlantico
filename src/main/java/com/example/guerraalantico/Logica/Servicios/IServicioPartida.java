package com.example.guerraalantico.Logica.Servicios;

import com.example.guerraalantico.DTO.AltaPartidaDTO;
import com.example.guerraalantico.DTO.PartidaDTO;
import com.example.guerraalantico.DTO.PartidaDetalladaDTO;
import java.util.List;

public interface IServicioPartida {

  List<PartidaDTO> obtenerPartidas(String pNombreUsuario);

  PartidaDetalladaDTO retomarPartida(int pCodigoPartida);

  int altaPartida(AltaPartidaDTO pAltaPartida);

  void guardarPartida(PartidaDetalladaDTO partidaDetalladaDTO);

}
