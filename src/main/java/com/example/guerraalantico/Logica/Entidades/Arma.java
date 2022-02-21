package com.example.guerraalantico.Logica.Entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Arma {

	private int idArma;
	private String nombreArma;
	private int danio;
	private int profundidadLanzamiento;
	private int profundidadImpacto;

}
