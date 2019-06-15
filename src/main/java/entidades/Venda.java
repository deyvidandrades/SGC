package main.java.entidades;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Venda {

    private long carroID;
    private long clienteID;
    private long funcionarioID;
    private long id;
    private String tipoPagamento;

    public Venda() {
    }

    public Venda(String tipoPagamento, long carro, long cliente, long funcionario) {
        this.tipoPagamento = tipoPagamento;
        this.carroID = carro;
        this.clienteID = cliente;
        this.funcionarioID = funcionario;

        Date date = new Date();
        this.id = date.getTime();
    }

    public String gettipoPagamento() {
        return tipoPagamento;
    }

    public long getCarroID() {
        return carroID;
    }

    public long getClienteID() {
        return clienteID;
    }

    public long getFuncionarioID() {
        return funcionarioID;
    }

    public long getId() {
        return id;
    }

    public Map toMap() {
        Map<String, Object> map = new HashMap<>();

        map.put("id", id);
        map.put("tipoPagamento", tipoPagamento);
        map.put("carroID", carroID);
        map.put("clienteID", clienteID);
        map.put("funcionarioID", funcionarioID);

        return map;
    }
}
