package main.java.assistentes;

import main.java.gui.*;
import main.res.valores.Referencias;

import javax.swing.*;

public abstract class IniciarGUI {

    public static void show(short ID) {
        switch (ID) {
            case Referencias.LOGIN:
                Login login = new Login();
                login.show();
                break;
            case Referencias.DASH_GERENTE:
                DashboardGerente dashboardGerente = new DashboardGerente();
                dashboardGerente.show();
                break;
            case Referencias.DASH_VENDEDOR:
                DashboardVendedor dashVendedor = new DashboardVendedor();
                dashVendedor.show();
                break;
            case Referencias.CADASTRAR_FUNCIONARIO:
                CadastroFuncionarios cadastroFuncionarios = new CadastroFuncionarios();
                cadastroFuncionarios.show();
                break;
            case Referencias.COMPRAR_CARRO:
                ComprarCarro comprarCarro = new ComprarCarro();
                comprarCarro.show();
                break;
            case Referencias.ACESSAR_VENDA:
                TelaVendas telavendas = new TelaVendas();
                telavendas.show();
                break;
            case Referencias.ACESSAR_INFO:
                TelaCarros telacarros = new TelaCarros();
                telacarros.show();
                break;
            case Referencias.ACESSAR_CARRO:
                TelaCarros telacarro = new TelaCarros(Referencias.CARRO);
                telacarro.show();
                break;
            case Referencias.DADOS_FUNCIONARIOS:
                DadosFuncionarios dadosFuncionarios = new DadosFuncionarios(Referencias.FUNCIONARIO_TABELA);
                dadosFuncionarios.show();
                break;
            case Referencias.ACESSAR_CARRO_VENDEDOR:
                TelaCarros carroVendedor = new TelaCarros(Referencias.CARRO, 1);
                carroVendedor.show();
                break;

            default:
                break;
        }
    }

    public static void hide(JFrame f) {
        f.setVisible(false);
    }
}
