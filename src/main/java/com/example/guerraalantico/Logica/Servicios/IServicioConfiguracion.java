package com.example.guerraalantico.Logica.Servicios;

import com.example.guerraalantico.DTO.NaveDTO;

public interface IServicioConfiguracion {

  NaveDTO obtenerDatosNavePorTipo(int pidTipoNave);

  /*
  NaveDTO obtenerDatosCarguero(int pCodigoNave);

  NaveGuerraDTO obtenerDatosDestructor(int pCodigoNave);

  NaveGuerraDTO obtenerDatosSubmarino(int pCodigoNave);
*/
}
