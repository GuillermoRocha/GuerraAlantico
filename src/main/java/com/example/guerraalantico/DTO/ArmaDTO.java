package com.example.guerraalantico.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArmaDTO {

	private int idArma;
	private String nombreArma;
	private int danio;
	private int alcance;
	private int profundidadLanzamiento;
	private int profundidadImpacto;

}
