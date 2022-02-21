package com.example.guerraalantico.DTO;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NaveGuerraDTO extends NaveDTO{

	private int profundidadActual;
	private List<ArmaDTO> armas;
	private List<AlcanceVistaDTO> vistas;

}
