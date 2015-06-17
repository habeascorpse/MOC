/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.services;


/**
 *
 * @author alan
 */
public enum EReturn {
    
    SUCESS(0,"SUCESSO",false),
    ERROR(1,"ERRO DESCONHECIDO",true),
    LOGIN_JA_EXISTE(2,"LOGIN JA EXISTE",true);
    
    
    private final int number;
    private final String description;
    private final boolean error;

    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public boolean isError() {
        return error;
    }

    private EReturn(int number, String description, boolean error) {
        this.number = number;
        this.description = description;
        this.error = error;
    }

    
    
    
    
}
