package com.example.guerraalantico.Controladores;

import com.example.guerraalantico.DTO.NaveDTO;
import com.example.guerraalantico.DTO.NaveGuerraDTO;
import com.example.guerraalantico.DTO.SubmarinoDTO;
import com.example.guerraalantico.Logica.Servicios.IServicioConfiguracion;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ConfiguracionController {

  @Autowired
  IServicioConfiguracion servicioConfiguracion;

  @Operation(summary = "Obtener la configuración del carguero")
  @GetMapping("/carguero")
  public NaveDTO obtenerDatosCarguero(){
    return servicioConfiguracion.obtenerDatosCarguero(1);
  }

  @Operation(summary = "Obtener la configuración del destructor")
  @GetMapping("/destructor")
  public NaveGuerraDTO obtenerDatosDestructor(){
    return servicioConfiguracion.obtenerDatosDestructor(2);
  }

  @Operation(summary = "Obtener la configuración del submarino")
  @GetMapping("/submarino")
  public SubmarinoDTO obtenerDatosSubmarino(){
    return servicioConfiguracion.obtenerDatosSubmarino(3);
  }
}
