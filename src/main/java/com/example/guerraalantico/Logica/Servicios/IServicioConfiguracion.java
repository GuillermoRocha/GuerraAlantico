package com.example.guerraalantico.Logica.Servicios;

import com.example.guerraalantico.DTO.NaveDTO;
import com.example.guerraalantico.DTO.NaveGuerraDTO;
import com.example.guerraalantico.DTO.SubmarinoDTO;

public interface IServicioConfiguracion {

  NaveDTO obtenerDatosCarguero(int pCodigoNave);

  NaveGuerraDTO obtenerDatosDestructor(int pCodigoNave);

  SubmarinoDTO obtenerDatosSubmarino(int pCodigoNave);


}
