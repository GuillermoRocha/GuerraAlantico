package com.example.guerraalantico.Logica.Servicios;

import com.example.guerraalantico.DTO.EquipoDTO;
import com.example.guerraalantico.Persistencia.DAO.EquipoBD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioEquipo implements IServicioEquipo {

  @Autowired
  private EquipoBD equipoBD;

  public void guardarEquipo(EquipoDTO pEquipo){
   // equipoBD.guardarEquipoBD(pEquipo);
  }

}
