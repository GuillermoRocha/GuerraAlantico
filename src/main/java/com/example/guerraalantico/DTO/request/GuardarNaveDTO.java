package com.example.guerraalantico.DTO.request;

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
    private int rotacion;
}
