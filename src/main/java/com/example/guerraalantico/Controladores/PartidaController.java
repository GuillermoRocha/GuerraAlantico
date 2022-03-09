package com.example.guerraalantico.Controladores;

import com.example.guerraalantico.DTO.AltaPartidaDTO;
import com.example.guerraalantico.DTO.PartidaDTO;
import com.example.guerraalantico.DTO.PartidaDetalladaDTO;
import com.example.guerraalantico.DTO.GuardarNaveDTO;
import com.example.guerraalantico.Logica.Servicios.IServicioPartida;

import java.sql.SQLException;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class PartidaController {

  @Autowired
  private IServicioPartida servicioPartida;

  @Operation(summary = "Obtener las partidas de un usuario")
  @GetMapping("/partida/{pIdUsuario}")
  public List<PartidaDTO> obtenerPartidasPorUsuario(@PathVariable int pIdUsuario){
    return servicioPartida.obtenerPartidasPorUsuarioARetomar(pIdUsuario);
  }

  @Operation(summary = "Obtener los datos de una partida a retomar")
  @GetMapping("/partida/retomar/{pCodigoPartida}")
  public PartidaDetalladaDTO retomarPartida(@PathVariable int pCodigoPartida) {
    return servicioPartida.retomarPartida(pCodigoPartida);
  }

  @Operation(summary = "Dar de alta una partida")
  @PostMapping("/partida/alta")
  public int altaPartida(@RequestBody AltaPartidaDTO pAltaPartida){
    return servicioPartida.altaPartida(pAltaPartida);
  }


  @Operation(summary = "Guardar una partida")
  @PostMapping("/partida/guardar")
  public void guardarPartidaDTO(@RequestBody GuardarNaveDTO pGuardarNaveDTO){
    servicioPartida.guardarPartida(pGuardarNaveDTO);
  }

  @Operation(summary = "Finalizar una partida")
  @PostMapping("/partida/finalizar/{pCodigoPartida}")
  public void finalizarPartida(@PathVariable int pCodigoPartida) throws SQLException {
    servicioPartida.finalizarPartida(pCodigoPartida);
  }

}
