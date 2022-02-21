package com.example.guerraalantico.Logica.Entidades;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Equipo {

	private int idEquipo;
	private int bando;
	private Usuario usuario;
	private Partida partida;
	private List<Nave> naves;
}
