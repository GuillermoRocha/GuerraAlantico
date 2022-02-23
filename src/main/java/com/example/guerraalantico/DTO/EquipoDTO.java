package com.example.guerraalantico.DTO;

import com.example.guerraalantico.Logica.Entidades.Usuario;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipoDTO {

	private int idEquipo;
	private int bando;
	private int idUsuario;
	private List<NaveDTO> naves;

	public EquipoDTO(int pIdEquipo, int pIdBando, int pIdUsuario){
		this.idEquipo = pIdEquipo;
		this.bando = pIdBando;
		this.idUsuario =pIdUsuario;
	}
}
