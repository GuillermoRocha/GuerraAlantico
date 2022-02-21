package com.example.guerraalantico.DTO;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartidaDTO {

  private int idPartida;
  private LocalDate fechaAlta;
  private LocalDate fechaGuardado;
  private boolean finalizada;
  private int idUsuarioGanador;

}
