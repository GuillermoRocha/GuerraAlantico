package com.example.guerraalantico.Logica.Servicios;

import com.example.guerraalantico.DTO.NaveDTO;
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
    public NaveDTO obtenerDatosNavePorTipo(int pidTipoNave) {
        return navesBD.obtenerNaveBD(pidTipoNave);
    }
}
