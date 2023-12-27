package com.culysoft.gestaovenda.modelo.dominio;

public class Cliente {
    private long id;
    private String nome;
    private String telefone;
    private String moradia;

    public Cliente(){

    }

    public Cliente(long id, String nome, String telefone, String moradia) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.moradia = moradia;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getMoradia() {
        return moradia;
    }

    public void setMoradia(String moradia) {
        this.moradia = moradia;
    }
}
