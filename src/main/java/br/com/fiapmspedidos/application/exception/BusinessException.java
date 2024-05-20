package br.com.fiapmspedidos.application.exception;

public class BusinessException extends RuntimeException {

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }
}