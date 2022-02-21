package com.example.guerraalantico.Excepciones;

public class PersistenciaException extends Exception {

  private String message;

  public PersistenciaException(String pMessage) {
    this.message = pMessage;
  }

}
