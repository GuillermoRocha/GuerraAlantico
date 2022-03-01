package com.example.guerraalantico.Logica.Servicios;

import com.example.guerraalantico.DTO.NaveDTO;
import com.example.guerraalantico.DTO.NaveGuerraDTO;

public interface IServicioConfiguracion {

  NaveDTO obtenerDatosCarguero(int pCodigoNave);

  NaveGuerraDTO obtenerDatosDestructor(int pCodigoNave);

  NaveGuerraDTO obtenerDatosSubmarino(int pCodigoNave);

}
