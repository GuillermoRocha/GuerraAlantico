package com.example.guerraalantico.Logica.Entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Nave {

	private int idNave;
	private int resistencia;
	private int coordenadasX;
	private int coordenadaY;
	private int velocidad;

}
