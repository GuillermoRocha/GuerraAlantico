package com.example.guerraalantico.Logica.Servicios;

import com.example.guerraalantico.DTO.AltaPartidaDTO;
import com.example.guerraalantico.DTO.PartidaDTO;
import com.example.guerraalantico.DTO.PartidaDetalladaDTO;
import com.example.guerraalantico.DTO.GuardarNaveDTO;

import java.sql.SQLException;
import java.util.List;

public interface IServicioPartida {

  List<PartidaDTO> obtenerPartidasPorUsuarioARetomar(int pIdUsuario);

  PartidaDetalladaDTO retomarPartida(int pCodigoPartida);

  int altaPartida(AltaPartidaDTO pAltaPartida);

  void guardarPartida(GuardarNaveDTO pGuardarNave);

  void finalizarPartida(int pIdPartida) throws SQLException;

}
