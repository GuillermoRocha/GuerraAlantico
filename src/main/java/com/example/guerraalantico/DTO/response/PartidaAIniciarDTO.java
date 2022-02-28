package com.example.guerraalantico.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartidaAIniciarDTO {

    private int idPartida;

    private int usuarioEquipoUno;
    private int usuarioEquipoDos;
}
