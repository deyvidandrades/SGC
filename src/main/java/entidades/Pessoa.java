package main.java.entidades;

import main.java.interfaces.PersistirDados;

import java.util.Date;

public class Pessoa implements PersistirDados {

    private String nome;
    private long id;

    Pessoa() {
        id = new Date().getTime();
    }

    Pessoa(String nome, long id) {
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}