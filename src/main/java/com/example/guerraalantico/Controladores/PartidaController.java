package com.example.guerraalantico.Controladores;

import com.example.guerraalantico.DTO.AltaPartidaDTO;
import com.example.guerraalantico.DTO.PartidaDTO;
import com.example.guerraalantico.DTO.PartidaDetalladaDTO;
import com.example.guerraalantico.Logica.Servicios.IServicioPartida;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PartidaController {

  @Autowired
  private IServicioPartida servicioPartida;

  @Operation(summary = "Obtener las partidas de un usuario")
  @GetMapping("/partida/{pUsername}")
  public List<PartidaDTO> obtenerPartidasPorUsuario(@PathVariable String pUsername){
    return servicioPartida.obtenerPartidas(pUsername);
  }

  @Operation(summary = "Obtener los datos de una partida a retomar")
  @GetMapping("/partida/retomar/{pCodigoPartida}")
  public PartidaDetalladaDTO retomarPartida(int pCodigoPartida) {
    return servicioPartida.retomarPartida(pCodigoPartida);
  }

  @Operation(summary = "Dar de alta una partida")
  @PostMapping("/partida/alta")
  public void altaPartida(@RequestBody AltaPartidaDTO pAltaPartida){
    servicioPartida.altaPartida(pAltaPartida);
  }


  @Operation(summary = "Guardar una partida")
  @PostMapping("/partida/guardar")
  public void guardarPartidaDTO(@RequestBody PartidaDetalladaDTO pPartidaDetallada){
    servicioPartida.guardarPartida(pPartidaDetallada);
  }

}
