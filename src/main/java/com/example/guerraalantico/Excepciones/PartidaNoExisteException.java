package com.example.guerraalantico.Excepciones;

import org.springframework.http.HttpStatus;

public class PartidaNoExisteException extends ApiException {


    public PartidaNoExisteException(String message) {
        super(HttpStatus.NOT_FOUND.name(), message, HttpStatus.NOT_FOUND.value());
    }

}
