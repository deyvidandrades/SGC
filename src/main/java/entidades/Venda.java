package main.java.entidades;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Venda {

    private String tipoPagamento;
    private Carro carro;
    private Cliente cliente;
    private Funcionario funcionario;
    private long id;

    public Venda(String pagamento, Carro carro, Cliente cliente, Funcionario funcionario) {
        this.tipoPagamento = pagamento;
        this.carro = carro;
        this.cliente = cliente;
        this.funcionario = funcionario;

        Date date = new Date();
        this.id = date.getTime();
    }

    public String getPagamento() {
        return tipoPagamento;
    }

    public Carro getCarro() {
        return carro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }


    public Map toMap() {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("id", id);
        map.put("pagamento", tipoPagamento);
        map.put("carro", carro.toMap());
        map.put("cliente", cliente.toMap());
        map.put("funcionario", funcionario.toMap());

        return map;
    }
}
