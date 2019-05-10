package main.java.gui;

import main.java.assistentes.IniciarGUI;
import main.java.entidades.Carro;
import main.java.entidades.Cliente;
import main.java.entidades.Funcionario;
import main.java.entidades.Venda;
import main.java.interfaces.FrameInterface;
import main.java.interfaces.PersistirDados;
import main.res.valores.Dimensoes;
import main.res.valores.Referencias;
import main.res.valores.Strings;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DashboardGerente implements FrameInterface, PersistirDados {

    public JPanel panel1;
    private JTable tabelaVendas;
    private JTable tabelaEstoque;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel img;
    private JButton cadastrarFuncionarioButton;
    private JButton comprarVeiculoButton;
    private JLabel ola;
    private JButton sairButton;

    public DashboardGerente() {
        super();

        assert false;
        img.setIcon(icone);

        ola.setText(String.format("Olá %s!", Referencias.FUNCIONARIO.getNome()));

        if (!Referencias.FUNCIONARIO.getAcesso()) {
            cadastrarFuncionarioButton.setEnabled(false);
            comprarVeiculoButton.setEnabled(false);
        } else {
            cadastrarFuncionarioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    IniciarGUI.show(Referencias.CADASTRAR_FUNCIONARIO);
                }
            });

            comprarVeiculoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    JOptionPane.showMessageDialog(frame, Strings.MENSAGEM_NAO_IMPLEMENTADO);
                }
            });
        }

        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Referencias.FUNCIONARIO = null;
                IniciarGUI.show(Referencias.LOGIN);
            }
        });

        configuraTabelaVendas();
        configuraTabelaEstoque();
    }

    private void configuraTabelaVendas() {
        ArrayList<Venda> vendas = getVendas();

        DefaultTableModel model = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }

        };

        for (Object name : Referencias.COLUNAS_VENDAS) {
            model.addColumn(name);
        }

        for (Venda venda : vendas) {
            Object[] objects = {
                    venda.getId(),
                    getNomeCliente(venda.getClienteID()),
                    venda.gettipoPagamento(),
                    getModeloCarro(venda.getCarroID()),
                    getVendedor(venda.getFuncionarioID())};

            model.addRow(objects);
        }

        tabelaVendas.setModel(model);

        tabelaVendas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tabelaVendas.rowAtPoint(evt.getPoint());
                int col = tabelaVendas.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {

                    JOptionPane.showMessageDialog(frame, Strings.MENSAGEM_NAO_IMPLEMENTADO);
                }
            }
        });
    }

    private void configuraTabelaEstoque() {
        ArrayList<Carro> carros = getCarros();

        DefaultTableModel model = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }

        };

        for (Object name : Referencias.COLUNAS_ESTOQUE) {
            model.addColumn(name);
        }

        for (Carro carro : carros) {
            Object[] objects = {carro.getId(), carro.getMarca(), carro.getModelo(), carro.getAno(), carro.getPreco() * 1000};
            model.addRow(objects);
        }

        tabelaEstoque.setModel(model);

        tabelaEstoque.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tabelaEstoque.rowAtPoint(evt.getPoint());
                int col = tabelaEstoque.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {

                    JOptionPane.showMessageDialog(frame, Strings.MENSAGEM_NAO_IMPLEMENTADO);
                }
            }
        });
    }

    private String getNomeCliente(long id) {

        ArrayList<Cliente> clientes = getClientes();

        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente.getNome();
            }
        }

        return null;
    }

    private String getModeloCarro(long id) {

        ArrayList<Carro> carros = getCarros();

        for (Carro carro : carros) {
            if (carro.getId() == id) {
                return carro.getModelo() + " (" + carro.getMarca() + ")";
            }
        }

        return null;
    }

    private String getVendedor(long id) {

        ArrayList<Funcionario> funcionarios = getFuncionarios();

        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getId() == id) {
                return funcionario.getNome();
            }
        }

        return null;
    }

    @Override
    public void show() {
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setTitle(Strings.DAL_COMPLETO);
        frame.setSize(Dimensoes.DASH);
        frame.setLocation(Dimensoes.getCentroTela(frame.getWidth(), frame.getHeight()));

        try {
            frame.setIconImage(ImageIO.read(new File(Strings.ICONE)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        frame.setVisible(true);
    }

    @Override
    public void hide() {
        frame.setVisible(false);
    }
}

