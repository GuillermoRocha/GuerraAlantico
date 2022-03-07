package com.example.guerraalantico.DTO;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartidaDTO {

  private Integer idPartida;
  private Date fechaAlta;
  private Date fechaGuardado;
  private boolean finalizada;
  private String jugadorRival;


}
