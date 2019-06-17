package main.java.gui;

import main.java.assistentes.IniciarGUI;
import main.java.entidades.Carro;
import main.java.entidades.Cliente;
import main.java.entidades.Empresa;
import main.java.entidades.Venda;
import main.java.interfaces.FrameInterface;
import main.java.interfaces.PersistirDados;
import main.res.valores.Dimensoes;
import main.res.valores.Referencias;
import main.res.valores.Strings;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class DashboardVendedor implements FrameInterface, PersistirDados {


    private JLabel ola;
    private JLabel img;
    private JPanel panel1;
    private JLabel estoque;
    private JButton sairButton;
    private JButton finalizarVendaButton;
    private JTextField textNome;
    private JTextField textDocumento;
    private JSlider slider1;
    private JTable tabelaEstoque;
    private JTable tabelaInfo;
    private JComboBox<Object> comboBox1;
    private JLabel idade;

    private long carroID = 0;
    private long funcionarioID = Referencias.FUNCIONARIO.getId();
    private String pagamento = "";
    private double VALOR_EM_CAIXA;

    public DashboardVendedor() {
        VALOR_EM_CAIXA = getValorEmCaixa();

        assert false;
        img.setIcon(icone);

        ola.setText("Vendedor: " + Referencias.FUNCIONARIO.getNome());


        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                if (slider1.getValue() < 60) {
                    idade.setText(slider1.getValue() + " anos");
                } else {
                    idade.setText("+60 anos");
                }
            }
        });

        finalizarVendaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                confirmarCompra();
            }
        });

        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Referencias.FUNCIONARIO = null;
                IniciarGUI.show(Referencias.LOGIN);
            }
        });

        tabelaInfo.setEnabled(false);
        configuraTabelaEstoque();
        configuraComboBox();
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

        int i = 0;
        ArrayList<Carro> carrosNaoVendidos = new ArrayList<>();

        for (Carro carro : carros) {
            if (!carro.isVendido()) {
                Object[] objects = {carro.getId(), carro.getMarca(), carro.getModelo(), carro.getAno(), carro.getPreco()};
                model.addRow(objects);
                carrosNaoVendidos.add(carro);
                i++;
            }
        }

        estoque.setText(String.valueOf(i));
        tabelaEstoque.setModel(model);

        tabelaEstoque.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tabelaEstoque.rowAtPoint(evt.getPoint());
                int col = tabelaEstoque.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {

                    configuraTabelainfo(carrosNaoVendidos, tabelaEstoque.getSelectedRow());
                    tabelaInfo.setEnabled(true);
                }
            }
        });
    }

    private void configuraTabelainfo(ArrayList<Carro> carros, int posicao) {
        DefaultTableModel model = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }

        };

        for (Object name : Referencias.COLUNAS_INFO) {
            model.addColumn(name);
        }

        Carro carro = carros.get(posicao);
        carroID = carros.get(posicao).getId();

        Object[] objects = {
                carro.getModelo(),
                carro.getMarca(),
                carro.getAno(),
                carro.getPreco(),
                carro.getCor(),
                carro.getNumPortas(),
                carro.getCambio(),
                carro.getQuilometragem(),
                carro.getTorque(),
                carro.getCombustivel(),
                carro.getOpcionais()
        };

        int i = 0;
        for (Object o : objects) {
            Object[] obb = {Referencias.LINHAS_CARRO[i], o};
            model.addRow(obb);
            i++;
        }

        tabelaInfo.setModel(model);

        tabelaInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tabelaInfo.rowAtPoint(evt.getPoint());
                int col = tabelaInfo.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {

                    Referencias.CARRO = carros.get(tabelaEstoque.getSelectedRow());
                    IniciarGUI.show(Referencias.ACESSAR_CARRO_VENDEDOR);
                }
            }
        });
    }

    private void configuraComboBox() {

        pagamento = Referencias.COLUNAS_FORMAS_PAGAMENTO[0].toString();
        DefaultComboBoxModel<Object> comboBoxModel = new DefaultComboBoxModel<>(Referencias.COLUNAS_FORMAS_PAGAMENTO);

        comboBoxModel.addListDataListener(new ListDataListener() {
            @Override
            public void intervalAdded(ListDataEvent listDataEvent) {

            }

            @Override
            public void intervalRemoved(ListDataEvent listDataEvent) {

            }

            @Override
            public void contentsChanged(ListDataEvent listDataEvent) {


            }
        });

        comboBox1.setModel(comboBoxModel);

        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (Objects.requireNonNull(comboBox1.getSelectedItem()).toString().equals(Referencias.COLUNAS_FORMAS_PAGAMENTO[0])) {
                    pagamento = Referencias.COLUNAS_FORMAS_PAGAMENTO[0].toString();

                } else if (Objects.requireNonNull(comboBox1.getSelectedItem()).toString().equals(Referencias.COLUNAS_CARGOS[1])) {
                    pagamento = Referencias.COLUNAS_FORMAS_PAGAMENTO[1].toString();

                } else {
                    pagamento = Referencias.COLUNAS_FORMAS_PAGAMENTO[2].toString();
                }
            }
        });

    }

    private void confirmarCompra() {
        if (carroID != 0 && funcionarioID != 0 && !textNome.getText().equals("") && !textDocumento.getText().equals("")) {

            Cliente cliente = new Cliente(textNome.getText(), textDocumento.getText(), Integer.parseInt(idade.getText().replace(" anos", "").replace("+", "")));
            Venda venda = new Venda(pagamento, carroID, cliente.getId(), funcionarioID);

            setDados(Strings.DADOS_CLIENTES, cliente.toMap());
            setDados(Strings.DADOS_VENDAS, venda.toMap());

            ArrayList<Carro> carros = getCarros();

            int i = 0;
            for (Carro carro : carros) {
                if (carro.getId() == carroID) {

                    carro.setVendido(true);
                    carro.setId(carro.getId());
                    atualizarCarro(carros.get(i).getId(), carro.toMap());

                    Empresa empresa = new Empresa(getValorEmCaixa() + carro.getPreco());
                    atualizarCaixa(empresa.toMap());
                }

                i++;
            }

            textNome.setText("");
            textDocumento.setText("");

            carroID = 0;
            slider1.setValue(18);
            tabelaInfo.setEnabled(false);

            configuraTabelaEstoque();
            VALOR_EM_CAIXA = getValorEmCaixa();

            JOptionPane.showMessageDialog(frame, Strings.MENSAGEM_COMPRA_REALIZADA);

        } else if (carroID == 0) {
            JOptionPane.showMessageDialog(frame, Strings.MENSAGEM_CARRO_NAO_SELECIONADO);
        } else if (textNome.getText().equals("")) {
            JOptionPane.showMessageDialog(frame, Strings.MENSAGEM_NOME_VAZIO);
        } else if (textDocumento.getText().equals("")) {
            JOptionPane.showMessageDialog(frame, Strings.MENSAGEM_DOCUMENTO_VAZIO);
        }
    }

    @Override
    public void show() {
        frame.setContentPane(new DashboardVendedor().panel1);
        frame.pack();
        frame.setTitle(Strings.DAL_COMPLETO);
        frame.setSize(Dimensoes.DASH);
        frame.setLocation(new Point((tamanhoTela.width - frame.getWidth()) / 2, (tamanhoTela.height - frame.getHeight()) / 2));

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
