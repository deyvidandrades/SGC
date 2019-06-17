package main.java.entidades;

import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Funcionario extends Pessoa {

    private boolean acesso;
    private String senha;
    private String cargo;
    private String login;


    public Funcionario() {
        super();
    }

    public Funcionario(JSONObject funcionario) {
        super(funcionario.getString("nome"), funcionario.getLong("id"));

        this.acesso = funcionario.getBoolean("acesso");
        this.senha = funcionario.getString("senha");
        this.cargo = funcionario.getString("cargo");
        this.login = funcionario.getString("login");
    }

    public Funcionario(String nome, String senha, String cargo, String login, boolean acesso, long id) {
        super(nome, id);

        this.acesso = acesso;
        this.senha = senha;
        this.cargo = cargo;
        this.login = login;
    }

    public Funcionario(String nome, String senha, String cargo, String login, boolean acesso) {
        super(nome, new Date().getTime());

        this.acesso = acesso;
        this.senha = senha;
        this.cargo = cargo;
        this.login = login;
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

    public String getLogin() {
        return login;
    }

    public Map toMap() {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("nome", getNome());
        map.put("acesso", acesso);
        map.put("senha", senha);
        map.put("cargo", cargo);
        map.put("login", login);
        map.put("id", getId());

        return map;
    }
}
