package br.com.fiapmspedidos.application.exception;

public class IllegalRequestException extends IllegalArgumentException {

    public IllegalRequestException(String message) {
        super(message);
    }

}
