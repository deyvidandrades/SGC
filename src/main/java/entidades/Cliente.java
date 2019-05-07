package main.java.entidades;

import java.util.HashMap;
import java.util.Map;

public class Cliente extends Pessoa {
    private String documento;
    private int idade;

    public Cliente(String nome, String documento, int idade) {
        super(nome);

        this.documento = documento;
        this.idade = idade;
    }

    public String getDocumento() {
        return documento;
    }

    public int getIdade() {
        return idade;
    }

    public Map toMap() {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("nome", getNome());
        map.put("documento", getDocumento());
        map.put("idade", getIdade());
        map.put("id", getId());

        return map;
    }

}
