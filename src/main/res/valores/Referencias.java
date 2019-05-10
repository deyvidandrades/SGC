package main.res.valores;

import main.java.entidades.Funcionario;

public abstract class Referencias {

    //INICIA GUI'S//
    public static final short LOGIN = 0;
    public static final short DASH_GERENTE = 1;
    public static final short DASH_VENDEDOR = 2;
    public static final short CADASTRAR_FUNCIONARIO = 3;

    //FUNCIONARIO LOGADO//
    public static Funcionario FUNCIONARIO;


    //CABECALHOS DE TABELAS//
    public static Object[] COLUNAS_INFO = {"Propriedade", "Descrição"};
    public static Object[] COLUNAS_FUNCIONARIOS = {"Nome", "Cargo", "Privilegio"};
    public static Object[] COLUNAS_ESTOQUE = {"ID", "Marca", "Modelo", "Ano", "Preço"};
    public static Object[] COLUNAS_VENDAS = {"ID", "Comprador", "Pagamento", "Veículo", "Vendedor"};
    public static Object[] LINHAS_CARRO = {"Modelo", "Marca", "Ano", "Preço", "Cor", "Portas", "Câmbio", "Quilometragem", "Torque", "Combustível", "Opicionáis"};

    //COMBOBOX//
    public static Object[] FORNECEDORES = {"Fiat", "Volkswagen"};
    public static Object[] COLUNAS_CARGOS = {"Assistente", "Gerente", "Vendedor"};
    public static Object[] COLUNAS_FORMAS_PAGAMENTO = {"Credito", "Debito", "Financiamento"};
    public static Object[] MODELOS = {"Palio", "Punto", "Toro", "Cronos", "Mobi", "Polo", "Fox", "Saveiro", "Gol", "Amarok"};

}
