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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class DashboardGerente implements FrameInterface, PersistirDados {

    public JPanel panel1;
    private JTable tabelaVendas;
    private JTable tabelaEstoque;
    private JTextField buscarCarro;
    private JTextField buscaVendas;
    private JButton cadastrarFuncionarioButton;
    private JButton comprarVeiculoButton;
    private JLabel ola;
    private JLabel lucroTotal;
    private JButton sairButton;
    private JLabel carrosEstoque;
    private JLabel carrosVendidos;
    private JLabel img;
    private JLabel vendasDoDia;


    private static int ESTOQUE;
    private static int VENDIDOS;

    public DashboardGerente() {
        super();

        assert false;
        img.setIcon(icone);
        lucroTotal.setText("");
        carrosVendidos.setText("");
        carrosEstoque.setText("");
        ESTOQUE = 0;
        VENDIDOS = 0;

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
                    IniciarGUI.show(Referencias.COMPRAR_CARRO);
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

        buscaVendas.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                configuraTabelaVendas(true);
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {

            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });

        buscarCarro.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                configuraTabelaEstoque(true);
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {

            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });

        configuraTabelaVendas(false);
        configuraTabelaEstoque(false);
        vendasDia();
    }

    private void configuraTabelaVendas(boolean busca) {
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

        ArrayList<Venda> vendadasFiltro = new ArrayList<>();
        for (Venda venda : vendas) {
            Object[] objects = {
                    venda.getId(),
                    Objects.requireNonNull(getCliente(venda.getClienteID())).getNome(),
                    venda.gettipoPagamento(),
                    Objects.requireNonNull(getCarro(venda.getCarroID())).getModelo(),
                    Objects.requireNonNull(getVendedor(venda.getFuncionarioID())).getNome()};

            if (busca && !buscaVendas.getText().isEmpty()) {
                if (Objects.requireNonNull(getCarro(venda.getCarroID())).getModelo().toUpperCase().contains(buscaVendas.getText().toUpperCase())) {
                    vendadasFiltro.add(venda);
                    model.addRow(objects);
                }
            } else {
                vendadasFiltro.add(venda);
                model.addRow(objects);
            }
        }

        tabelaVendas.setModel(model);

        tabelaVendas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tabelaVendas.rowAtPoint(evt.getPoint());
                int col = tabelaVendas.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    Referencias.CLICKVENDAS = vendadasFiltro.get(tabelaVendas.getSelectedRow());
                    IniciarGUI.show(Referencias.ACESSAR_VENDA);
                }
            }
        });

        lucroTotal.setText(String.valueOf(getValorEmCaixa()));
    }

    private void configuraTabelaEstoque(boolean busca) {
        ESTOQUE = 0;
        VENDIDOS = 0;
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

        ArrayList<Carro> carrosNoEstoque = new ArrayList<>();
        for (Carro carro : carros) {

            if (busca && !buscarCarro.getText().isEmpty()) {
                if (!carro.isVendido()) {
                    if (carro.getModelo().toUpperCase().contains(buscarCarro.getText().toUpperCase())) {
                        Object[] objects = {carro.getId(), carro.getMarca(), carro.getModelo(), carro.getAno(), carro.getPreco()};
                        model.addRow(objects);
                        carrosNoEstoque.add(carro);
                        ESTOQUE++;
                    }
                } else {
                    VENDIDOS++;
                }
            } else {
                if (!carro.isVendido()) {
                    Object[] objects = {carro.getId(), carro.getMarca(), carro.getModelo(), carro.getAno(), carro.getPreco()};
                    model.addRow(objects);
                    carrosNoEstoque.add(carro);
                    ESTOQUE++;
                } else {
                    VENDIDOS++;
                }
            }
        }

        tabelaEstoque.setModel(model);

        tabelaEstoque.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tabelaEstoque.rowAtPoint(evt.getPoint());
                int col = tabelaEstoque.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    Referencias.CARRO = carrosNoEstoque.get(tabelaEstoque.getSelectedRow());
                    IniciarGUI.show(Referencias.ACESSAR_CARRO);
                }
            }
        });

        carrosEstoque.setText(String.valueOf(ESTOQUE));
        carrosVendidos.setText(String.valueOf(VENDIDOS));
    }

    private Cliente getCliente(long id) {

        ArrayList<Cliente> clientes = getClientes();

        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }

        return null;
    }

    private Carro getCarro(long id) {

        ArrayList<Carro> carros = getCarros();

        for (Carro carro : carros) {
            if (carro.getId() == id) {
                return carro;
            }
        }

        return null;
    }

    private Funcionario getVendedor(long id) {

        ArrayList<Funcionario> funcionarios = getFuncionarios();

        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getId() == id) {
                return funcionario;
            }
        }

        return null;
    }

    private void vendasDia() {
        ArrayList<Venda> vendas = getVendas();
        ArrayList<Carro> carro = getCarros();
        double somaVendas = 0.0;
        for (Venda item : vendas) {

            for (Carro itemCarro : carro) {
                if (item.getCarroID() == itemCarro.getId()) {
                    DateFormat df = new SimpleDateFormat("dd:MM:yyyy");
                    Calendar cal = Calendar.getInstance();
                    cal.setTimeInMillis(item.getId());
                    String[] data = df.format(cal.getTime()).split(":");
                    Date now = new Date();
                    Date copiedDate = new Date(now.getTime());
                    String[] dataDia = df.format(copiedDate).split(":");

                    if (data[2].equals(dataDia[2]) && data[1].equals(dataDia[1]) && data[0].equals(dataDia[0])) {
                        somaVendas = somaVendas + itemCarro.getPreco();
                    }
                }
            }
        }
        vendasDoDia.setText(String.valueOf(somaVendas));
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

