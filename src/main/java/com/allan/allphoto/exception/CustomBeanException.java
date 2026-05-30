package com.allan.allphoto.exception;

public class CustomBeanException extends RuntimeException {
    public CustomBeanException(String mensagem) {
        super(mensagem);
    }
}