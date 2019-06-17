package main.java.gui;

import main.java.entidades.Carro;
import main.java.entidades.Empresa;
import main.java.interfaces.FrameInterface;
import main.java.interfaces.PersistirDados;
import main.java.interfaces.UriImagem;
import main.res.valores.Dimensoes;
import main.res.valores.Referencias;
import main.res.valores.Strings;

import javax.imageio.ImageIO;
import javax.swing.*;
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

public class ComprarCarro implements FrameInterface, PersistirDados, UriImagem {

    private JPanel panel1;
    private JTable tabelaCarros;
    private JComboBox<Object> quantidade;
    private JButton cancelarButton;
    private JButton finalizarCompraButton;
    private JLabel img;
    private JLabel saldo;
    private JLabel montante;
    private JLabel numCarros;
    private JLabel valorCarro;
    private JLabel valorTotal;

    private Carro CARRO;
    private double PRECO_CARRO;
    private double VALOR_EM_CAIXA;
    private int ESCOLHA;

    private ArrayList<Carro> carrosAdicionados = new ArrayList<>();

    public ComprarCarro() {
        super();

        assert false;
        img.setIcon(icone);
        quantidade.setEnabled(false);

        ESCOLHA = (int) Referencias.QUANTIDADE_COMPRA[0];
        VALOR_EM_CAIXA = getValorEmCaixa();

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                voltar(Referencias.DASH_GERENTE);
            }
        });

        finalizarCompraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                finalizarCompra(CARRO, ESCOLHA);
            }
        });

        configuraTabelaVeiculos();
        configuraComboBox();
    }

    private void configuraTabelaVeiculos() {
        ArrayList<Carro> carros = getCarros();

        DefaultTableModel model = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }

        };

        for (Object name : Referencias.COLUNAS_COMPRAR_CARRO) {
            model.addColumn(name);
        }

        for (Carro carro : carros) {

            boolean verificaRepetido = false;
            for (Carro c : carrosAdicionados) {
                if (c.getModelo().equals(carro.getModelo())) {
                    verificaRepetido = true;
                }
            }

            if (!verificaRepetido) {
                Object[] objects = {carro.getMarca(), carro.getModelo(), carro.getAno(), carro.getPreco()};
                model.addRow(objects);
                carrosAdicionados.add(carro);
            }
        }

        tabelaCarros.setModel(model);

        tabelaCarros.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tabelaCarros.rowAtPoint(evt.getPoint());
                int col = tabelaCarros.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    CARRO = carros.get(tabelaCarros.getSelectedRow());
                    PRECO_CARRO = CARRO.getPreco();
                    valorCarro.setText(String.valueOf(CARRO.getPreco()));
                    valorTotal.setText(String.valueOf(CARRO.getPreco()));

                    ESCOLHA = (int) Referencias.QUANTIDADE_COMPRA[0];
                    numCarros.setText("x" + ESCOLHA);

                    quantidade.setEnabled(true);
                    quantidade.setSelectedIndex(0);

                    finalizarCompraButton.setEnabled(false);

                    ImageIcon carroIcone = new ImageIcon(new ImageIcon(getURI(CARRO.getImg())).getImage().getScaledInstance(250, 200, Image.SCALE_DEFAULT));
                    img.setIcon(carroIcone);

                    atualizarGUI();
                }
            }
        });
    }

    private void configuraComboBox() {
        DefaultComboBoxModel<Object> comboBoxModel = new DefaultComboBoxModel<Object>(Referencias.QUANTIDADE_COMPRA);

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

        quantidade.setModel(comboBoxModel);

        quantidade.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (Integer.parseInt(Objects.requireNonNull(quantidade.getSelectedItem()).toString()) == (int) Referencias.QUANTIDADE_COMPRA[0]) {
                    ESCOLHA = (int) Referencias.QUANTIDADE_COMPRA[0];
                } else if (Integer.parseInt(Objects.requireNonNull(quantidade.getSelectedItem()).toString()) == (int) Referencias.QUANTIDADE_COMPRA[1]) {
                    ESCOLHA = (int) Referencias.QUANTIDADE_COMPRA[1];
                } else if (Integer.parseInt(Objects.requireNonNull(quantidade.getSelectedItem()).toString()) == (int) Referencias.QUANTIDADE_COMPRA[2]) {
                    ESCOLHA = (int) Referencias.QUANTIDADE_COMPRA[2];
                } else {
                    ESCOLHA = (int) Referencias.QUANTIDADE_COMPRA[3];
                }

                valorTotal.setText(String.valueOf(PRECO_CARRO * (double) ESCOLHA));
                atualizarGUI();
            }
        });

        ESCOLHA = (int) Referencias.QUANTIDADE_COMPRA[0];
        numCarros.setText("x" + ESCOLHA);
    }

    private void atualizarGUI() {
        numCarros.setText("x" + ESCOLHA);
        saldo.setText(String.valueOf(VALOR_EM_CAIXA - PRECO_CARRO * (double) ESCOLHA));
        montante.setText(String.valueOf(VALOR_EM_CAIXA));

        if (VALOR_EM_CAIXA < PRECO_CARRO * (double) ESCOLHA) {
            saldo.setForeground(new Color(0xFF5D5F, false));
            finalizarCompraButton.setEnabled(false);
        } else {
            saldo.setForeground(new Color(0x161616, false));
            finalizarCompraButton.setEnabled(true);
        }
    }

    private void finalizarCompra(Carro carro, int qtd) {
        for (int i = 0; i < qtd; i++) {
            Carro novoCarro = new Carro(carro);
            setDados(Strings.DADOS_CARROS, novoCarro.toMap());
        }

        Empresa empresa = new Empresa(VALOR_EM_CAIXA);
        atualizarCaixa(empresa.toMap());

        JOptionPane.showMessageDialog(frame, Strings.MENSAGEM_COMPRA_REALIZADA);
        voltar(Referencias.DASH_GERENTE);
    }

    @Override
    public void show() {
        frame.setContentPane(new ComprarCarro().panel1);
        frame.pack();
        frame.setTitle(Strings.DAL_COMPLETO);
        frame.setSize(Dimensoes.COMPRA);
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
