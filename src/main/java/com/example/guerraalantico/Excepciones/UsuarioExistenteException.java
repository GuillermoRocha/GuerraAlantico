package com.example.guerraalantico.Excepciones;

public class UsuarioExistenteException extends ApiException{

    public static final String BAD_REQUEST_ERROR_CODE = "bad_request";

    public UsuarioExistenteException(String message) {
        super("", message, 400);
    }


}
