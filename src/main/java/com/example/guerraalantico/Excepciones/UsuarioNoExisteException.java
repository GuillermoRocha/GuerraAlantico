package com.example.guerraalantico.Excepciones;

public class UsuarioNoExisteException extends Exception {

  private String message;

  public UsuarioNoExisteException(String pMessage) {
    this.message = pMessage;
  }

}

