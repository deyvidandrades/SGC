package main.res.valores;

import main.java.entidades.Funcionario;

public abstract class Referencias {

    public static final short LOGIN = 0;
    public static final short DASH_GERENTE = 1;
    public static final short DASH_VENDEDOR = 2;
    public static Funcionario FUNCIONARIO;
    public static String[] COLUNAS_VENDAS = {"ID", "Comprador", "Veículo", "Pagamento"};
    public static String[] COLUNAS_ESTOQUE = {"ID", "Marca", "Modelo", "Quantidade"};

}
