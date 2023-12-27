package com.culysoft.gestaovenda.modelo.dominio;

public class Categoria {
    private long id;
    private String nome;
    private String descrição;

    public Categoria(Long o, String bebida_java, String insercao_no_intellij){

            }
    public Categoria(long id, String nome, String descrição) {
        this.id = id;
        this.nome = nome;
        this.descrição = descrição;
    }
    /*os metodos get and set sao o que tornam visiveis os metodos fora*/

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

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }
}
