package com.example.guerraalantico.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NaveDTO {

	private int idTipoNave;
	private String tipoNave;
	private int coordenadasX;
	private int coordenadaY;
	private int resistencia;
	private int profundidadActual;
	private int velocidad;

}
