package com.example.guerraalantico.Logica.Servicios;

import com.example.guerraalantico.DTO.NaveDTO;
import com.example.guerraalantico.DTO.NaveGuerraDTO;
import com.example.guerraalantico.Persistencia.DAO.NavesBD;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioConfiguracion implements IServicioConfiguracion{

  @Autowired
  NavesBD navesBD;

  @Autowired
  ObjectMapper objectMapper;

  @Override
  public NaveDTO obtenerDatosCarguero(int pCodigoNave) {
      return navesBD.obtenerNaveBD(pCodigoNave);
  
  }

  @Override
  public NaveGuerraDTO obtenerDatosSubmarino(int pCodigoNave) {

      NaveDTO vNaveDTO = navesBD.obtenerNaveBD(pCodigoNave);
      NaveGuerraDTO vNaveGuerraDTO = objectMapper.convertValue(vNaveDTO, NaveGuerraDTO.class);
      vNaveGuerraDTO.setVistas(navesBD.obtenerAlcanceVistaBD(pCodigoNave));
      return vNaveGuerraDTO;
  }

  @Override
  public NaveGuerraDTO obtenerDatosDestructor(int pCodigoNave) {

      NaveDTO vNaveDTO = navesBD.obtenerNaveBD(pCodigoNave);
      NaveGuerraDTO vNaveGuerraDTO = objectMapper.convertValue(vNaveDTO, NaveGuerraDTO.class);
      vNaveGuerraDTO.setVistas(navesBD.obtenerAlcanceVistaBD(pCodigoNave));
      return vNaveGuerraDTO;
  }

}
