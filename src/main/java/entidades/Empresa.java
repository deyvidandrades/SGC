package main.java.entidades;

import java.util.HashMap;
import java.util.Map;

public class Empresa {
    private double caixa;

    public Empresa() {
    }

    public Empresa(double caixa) {
        this.caixa = caixa;
    }

    public double getCaixa() {
        return caixa;
    }

    public Map toMap() {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("caixa", getCaixa());

        return map;
    }
}
