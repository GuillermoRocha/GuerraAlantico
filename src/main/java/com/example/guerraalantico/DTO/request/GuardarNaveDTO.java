package com.example.guerraalantico.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GuardarNaveDTO {

    private int idEquipo;
    private int idNave;
    private int coordenadasX;
    private int coordenadaY;
    private int resistencia;
    private int profundidadActual;
}
