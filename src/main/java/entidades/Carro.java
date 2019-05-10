package main.java.entidades;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Carro {
    private String cor;
    private String marca;
    private String modelo;
    private String cambio;
    private String opcionais;
    private String combustivel;

    private long id;
    private int ano;
    private short numPortas;

    private double preco;
    private double torque;
    private double quilometragem;

    private float litro;
    private boolean vendido;

    public Carro() {
    }

    public Carro(String cor, String marca, String modelo, String cambio, String opcionais, String combustivel, int ano, short numPortas, double preco, double torque, double quilometragem, float litro) {
        this.cor = cor;
        this.marca = marca;
        this.modelo = modelo;
        this.cambio = cambio;
        this.opcionais = opcionais;
        this.combustivel = combustivel;
        this.ano = ano;
        this.numPortas = numPortas;
        this.preco = preco;
        this.torque = torque;
        this.quilometragem = quilometragem;
        this.litro = litro;

        Date date = new Date();
        this.id = date.getTime();
        this.vendido = false;
    }

    public String getCor() {
        return cor;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getCambio() {
        return cambio;
    }

    public String getOpcionais() {
        return opcionais;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public long getId() {
        return id;
    }

    public int getAno() {
        return ano;
    }

    public short getNumPortas() {
        return numPortas;
    }

    public double getPreco() {
        return preco;
    }

    public double getTorque() {
        return torque;
    }

    public double getQuilometragem() {
        return quilometragem;
    }

    public boolean isVendido() {
        return vendido;
    }

    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }

    public Map toMap() {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("cor", cor);
        map.put("marca", marca);
        map.put("modelo", modelo);
        map.put("cambio", cambio);
        map.put("opcionais", opcionais);
        map.put("combustivel", combustivel);

        map.put("id", id);
        map.put("ano", ano);
        map.put("numPortas", numPortas);

        map.put("preco", preco);
        map.put("torque", torque);
        map.put("quilometragem", quilometragem);

        map.put("litro", litro);
        map.put("vendido", vendido);

        return map;
    }
}
