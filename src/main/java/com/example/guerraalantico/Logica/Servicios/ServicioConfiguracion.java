package com.example.guerraalantico.Logica.Servicios;

import com.example.guerraalantico.DTO.NaveDTO;
import com.example.guerraalantico.DTO.NaveGuerraDTO;
import com.example.guerraalantico.DTO.SubmarinoDTO;
import com.example.guerraalantico.Excepciones.PersistenciaException;
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
  public SubmarinoDTO obtenerDatosSubmarino(int pCodigoNave) {

      SubmarinoDTO submarinoDTO = objectMapper.convertValue(navesBD.obtenerNaveBD(pCodigoNave), SubmarinoDTO.class);
      submarinoDTO.setVelocidades(navesBD.obtenerVelocidadesBD(pCodigoNave));
      submarinoDTO.setVistas(navesBD.obtenerAlcanceVistaBD(pCodigoNave));
      submarinoDTO.setArmas(navesBD.obtenerArmasBD(pCodigoNave));
      return submarinoDTO;

  }

  @Override
  public NaveGuerraDTO obtenerDatosDestructor(int pCodigoNave) {

      NaveDTO naveDTO = navesBD.obtenerNaveBD(pCodigoNave);
      NaveGuerraDTO naveGuerraDTO = objectMapper.convertValue(naveDTO, NaveGuerraDTO.class);
      naveGuerraDTO.setArmas(navesBD.obtenerArmasBD(pCodigoNave));
      naveGuerraDTO.setVistas(navesBD.obtenerAlcanceVistaBD(pCodigoNave));
      return naveGuerraDTO;
  }

}
