package main.java.entidades;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Carro {
    private String modelo;
    private float preco;
    private String cor;
    private short numPortas;
    private String consumo;
    private int potencia;
    private int torque;
    private boolean arCondicionado;
    private boolean vidrosEletricos;
    private boolean travasEletricas;
    private boolean alarme;
    private float litro;
    private long id;

    public Carro(String modelo, float preco, String cor, short numPortas, String consumo, int potencia, int torque, boolean arCondicionado, boolean vidrosEletricos, boolean travasEletricas, boolean alarme, float litro) {
        this.modelo = modelo;
        this.preco = preco;
        this.cor = cor;
        this.numPortas = numPortas;
        this.consumo = consumo;
        this.potencia = potencia;
        this.torque = torque;
        this.arCondicionado = arCondicionado;
        this.vidrosEletricos = vidrosEletricos;
        this.travasEletricas = travasEletricas;
        this.alarme = alarme;
        this.litro = litro;

        Date date = new Date();
        this.id = date.getTime();
    }

    public String getModelo() {
        return modelo;
    }

    public float getPreco() {
        return preco;
    }

    public String getCor() {
        return cor;
    }

    public short getNumPortas() {
        return numPortas;
    }

    public String getConsumo() {
        return consumo;
    }

    public int getPotencia() {
        return potencia;
    }

    public int getTorque() {
        return torque;
    }

    public boolean isArCondicionado() {
        return arCondicionado;
    }

    public boolean isVidrosEletricos() {
        return vidrosEletricos;
    }

    public boolean isTravasEletricas() {
        return travasEletricas;
    }

    public boolean isAlarme() {
        return alarme;
    }

    public float getLitro() {
        return litro;
    }

    public Map toMap() {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("modelo", modelo);
        map.put("preco", preco);
        map.put("cor", cor);
        map.put("numPortas", numPortas);
        map.put("consumo", consumo);
        map.put("potencia", potencia);
        map.put("torque", torque);
        map.put("arCondicionado", arCondicionado);
        map.put("vidrosEletricos", vidrosEletricos);
        map.put("travasEletricas", travasEletricas);
        map.put("alarme", alarme);
        map.put("litro", litro);
        map.put("id", id);

        return map;
    }
}
