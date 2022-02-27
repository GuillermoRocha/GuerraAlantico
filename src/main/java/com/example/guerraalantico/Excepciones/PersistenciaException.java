package com.example.guerraalantico.Excepciones;

import org.springframework.http.HttpStatus;

public class PersistenciaException extends ApiException {


  public PersistenciaException() {
    super(HttpStatus.INTERNAL_SERVER_ERROR.name(), "Se ha producido un error interno.", HttpStatus.INTERNAL_SERVER_ERROR.value());
  }

  public PersistenciaException(String message) {
    super(HttpStatus.INTERNAL_SERVER_ERROR.name(), message, HttpStatus.INTERNAL_SERVER_ERROR.value());
  }

}
