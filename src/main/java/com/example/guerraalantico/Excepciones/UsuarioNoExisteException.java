package com.example.guerraalantico.Excepciones;

import org.springframework.http.HttpStatus;

public class UsuarioNoExisteException extends ApiException {


  public UsuarioNoExisteException(String message) {
    super(HttpStatus.NOT_FOUND.name(), message, HttpStatus.NOT_FOUND.value());
  }

}

