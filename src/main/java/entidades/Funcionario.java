package main.java.entidades;

import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Funcionario {

    private String nome;
    private boolean acesso;
    private String senha;
    private String cargo;
    private String id;

    public Funcionario(JSONObject funcionario) {
        this.nome = funcionario.getString("nome");
        this.acesso = funcionario.getBoolean("acesso");
        this.senha = funcionario.getString("senha");
        this.cargo = funcionario.getString("cargo");
        this.id = funcionario.getString("id");
    }

    public Funcionario(String nome, boolean acesso, String senha, String cargo) {

        Date date = new Date();
        long time = date.getTime();

        this.nome = nome;
        this.acesso = acesso;
        this.senha = senha;
        this.cargo = cargo;
        this.id = String.valueOf(time);
    }

    public String getNome() {
        return nome;
    }

    public Boolean getAcesso() {
        return acesso;
    }

    public String getSenha() {
        return senha;
    }

    public String getCargo() {
        return cargo;
    }

    public String getId() {
        return id;
    }

    public Map toMap() {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("nome", nome);
        map.put("acesso", acesso);
        map.put("senha", senha);
        map.put("cargo", cargo);
        map.put("id", id);

        return map;
    }
}
