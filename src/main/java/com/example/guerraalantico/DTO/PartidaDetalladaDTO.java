package com.example.guerraalantico.DTO;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartidaDetalladaDTO {

  private PartidaDTO partida;

  private List<EquipoDTO> equipos;
}
