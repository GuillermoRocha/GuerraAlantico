package com.example.guerraalantico.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GuardarNaveDTO {

    private int idBando;
    private int idPartida;
    private int idNave;
    private int coordenadaX;
    private int coordenadaY;
    private int resistencia;
    private int profundidadActual;
    private float rotacion;
}
