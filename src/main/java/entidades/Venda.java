package main.java.entidades;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Venda {

    private String tipoPagamento;
    private long carroID;
    private long clienteID;
    private long funcionarioID;
    private long id;

    public Venda(String pagamento, long carro, long cliente, long funcionario) {
        this.tipoPagamento = pagamento;
        this.carroID = carro;
        this.clienteID = cliente;
        this.funcionarioID = funcionario;

        Date date = new Date();
        this.id = date.getTime();
    }

    public String getPagamento() {
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


    public Map toMap() {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("id", id);
        map.put("pagamento", tipoPagamento);
        map.put("carro", carroID);
        map.put("cliente", clienteID);
        map.put("funcionario", funcionarioID);

        return map;
    }
}
