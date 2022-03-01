package com.example.guerraalantico.Controladores;

import com.example.guerraalantico.DTO.AltaPartidaDTO;
import com.example.guerraalantico.DTO.PartidaDTO;
import com.example.guerraalantico.DTO.PartidaDetalladaDTO;
import com.example.guerraalantico.DTO.PartidaFinalzadaDTO;
import com.example.guerraalantico.DTO.request.GuardarNaveDTO;
import com.example.guerraalantico.DTO.response.PartidaAIniciarDTO;
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


  @Operation(summary = "Obtener la partida pendiente a iniciar")
  @GetMapping("/partida/iniciar")
  public PartidaAIniciarDTO obtenerPartidaAIniciar() {
    return servicioPartida.obtenerPartidaAIniciar();
  }

  @Operation(summary = "Habilitar una partida para que se pueda iniciar")
  @PostMapping("/partida/habilitar")
  public void habilitarPartida(@RequestBody int pIdPartida) {
    servicioPartida.habilitarPartida(pIdPartida);
  }

  @Operation(summary = "Deshabilitar la partida para que sea iniciada")
  @PostMapping("/partida/deshabilitar")
  public void deshabilitarPartida(@RequestBody int pIdPartida) {
    servicioPartida.deshabilitarPartida(pIdPartida);
  }



  @Operation(summary = "Obtener las partidas de un usuario")
  @GetMapping("/partida/{pUsername}")
  public List<PartidaDTO> obtenerPartidasPorUsuario(@PathVariable String pUsername){
    return servicioPartida.obtenerPartidasPorUsuarioARetomar(pUsername);
  }

  @Operation(summary = "Obtener los datos de una partida a retomar")
  @GetMapping("/partida/retomar/{pCodigoPartida}")
  public PartidaDetalladaDTO retomarPartida(int pCodigoPartida) {
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
  @PostMapping("/partida/finalizar")
  public void finalizarPartida(@RequestBody PartidaFinalzadaDTO pPartidaFinalizadaDTO) throws SQLException {
    servicioPartida.finalizarPartida(pPartidaFinalizadaDTO);
  }

}
