package com.culysoft.gestaovenda.modelo.exception;

public class NegocioException  extends  RuntimeException{
    public NegocioException(String mensagem){
        super(mensagem);
    }
}
