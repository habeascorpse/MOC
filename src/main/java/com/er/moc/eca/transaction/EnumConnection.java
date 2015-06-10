/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.transaction;

public enum EnumConnection {

    MOC("persistence","Conexão com o MOC");
    private String stringConnection;

    private String descricao;

    private EnumConnection(String stringConnection, String descricao) {
        this.stringConnection = stringConnection;
        this.descricao = descricao;
    }

    public String getStringConnection() {
        return stringConnection;
    }

    public void setStringConnection(String stringConnection) {
        this.stringConnection = stringConnection;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
