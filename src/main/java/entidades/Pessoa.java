package main.java.entidades;

import main.java.interfaces.PersistirDados;

import java.util.Date;

public abstract class Pessoa implements PersistirDados {

    private String nome;
    private long id;

    public Pessoa() {
    }

    public Pessoa(String nome) {
        Date date = new Date();
        this.nome = nome;
        this.id = date.getTime();
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}