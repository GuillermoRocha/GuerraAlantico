package com.example.guerraalantico.Excepciones;

import org.springframework.http.HttpStatus;

public class UsuarioExistenteException extends ApiException{


    public UsuarioExistenteException(String message) {
        super(HttpStatus.BAD_REQUEST.name(), message, HttpStatus.BAD_REQUEST.value());
    }

}
