package com.example.guerraalantico.Logica.Entidades;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Submarino extends NaveGuerra {
	
	private List<Velocidad> velocidades;

}
