package com.example.guerraalantico.DTO;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartidaDTO {

  private Integer idPartida;
  @JsonFormat(pattern="dd-MM-yyyy")
  private Date fechaAlta;
  @JsonFormat(pattern="dd-MM-yyyy")
  private Date fechaGuardado;
  private boolean finalizada;
  private String jugadorRival;


}
