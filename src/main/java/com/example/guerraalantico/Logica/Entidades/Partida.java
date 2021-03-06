package com.example.guerraalantico.Logica.Entidades;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Partida {

	private int idPartida;
	private LocalDate fechaAlta;
	private LocalDate fechaGuardado;
	private boolean finalizada;
	private String nombreUsuarioGanador;

}
