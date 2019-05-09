package main.res.valores;

import main.java.entidades.Funcionario;

public abstract class Referencias {

    public static final short LOGIN = 0;
    public static final short DASH_GERENTE = 1;
    public static final short DASH_VENDEDOR = 2;
    public static final short CADASTRAR_FUNCIONARIO = 3;

    public static Funcionario FUNCIONARIO;

    public static Object[] COLUNAS_VENDAS = {"ID", "Comprador", "Veículo", "Pagamento", "Vendedor"};
    public static Object[] COLUNAS_ESTOQUE = {"ID", "Marca", "Modelo", "Ano", "Preço"};

    public static Object[] COLUNAS_FUNCIONARIOS = {"Nome", "Cargo", "Privilegio"};
    public static Object[] COLUNAS_CARGOS = {"Assistente", "Gerente", "Vendedor"};

}
