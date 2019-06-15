package main.java.gui;

import main.java.entidades.Carro;
import main.java.interfaces.FrameInterface;
import main.java.interfaces.PersistirDados;
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

public class ComprarCarro implements FrameInterface, PersistirDados {
    private static double TOTAL;
    private static int ESTOQUE;
    private static int VENDIDOS;
    private JPanel panel1;
    private JTable tabelaCarros;
    private JComboBox<Object> quantidade;
    private JButton cancelarButton;
    private JButton finalizarCompraButton;
    private JLabel img;
    private JLabel numCarros;
    private String ESCOLHA = "";

    public ComprarCarro() {
        super();

        assert false;
        img.setIcon(icone);

        configuraTabelaEstoque();
        configuraComboBox();

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                voltar(Referencias.DASH_GERENTE);
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

        for (Object name : Referencias.COLUNAS_COMPRAR_CARRO) {
            model.addColumn(name);
        }

        for (Carro carro : carros) {
            if (!carro.isVendido()) {
                Object[] objects = {carro.getMarca(), carro.getModelo(), carro.getAno(), carro.getPreco() * 1000};
                model.addRow(objects);

                ESTOQUE++;
            } else {
                VENDIDOS++;
            }
        }

        tabelaCarros.setModel(model);

        tabelaCarros.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tabelaCarros.rowAtPoint(evt.getPoint());
                int col = tabelaCarros.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {

                    JOptionPane.showMessageDialog(frame, Objects.requireNonNull(getCarro(carros.get(tabelaCarros.getSelectedRow()).getId())).toMap().toString()
                            .replace(",", "\n")
                            .replace("{", "")
                            .replace("}", "")
                            .replace("=", ": ")
                            .toUpperCase());
                }
            }
        });
    }


    private void configuraComboBox() {

        ESCOLHA = Referencias.COLUNAS_FORMAS_PAGAMENTO[0].toString();

        DefaultComboBoxModel<Object> comboBoxModel = new DefaultComboBoxModel<>(Referencias.QUANTIDADE_COMPRA);

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

                if (Objects.requireNonNull(quantidade.getSelectedItem()).toString().equals(Referencias.QUANTIDADE_COMPRA[0])) {
                    ESCOLHA = Referencias.QUANTIDADE_COMPRA[0].toString();

                } else if (Objects.requireNonNull(quantidade.getSelectedItem()).toString().equals(Referencias.QUANTIDADE_COMPRA[1])) {
                    ESCOLHA = Referencias.QUANTIDADE_COMPRA[1].toString();

                } else if (Objects.requireNonNull(quantidade.getSelectedItem()).toString().equals(Referencias.QUANTIDADE_COMPRA[1])) {
                    ESCOLHA = Referencias.QUANTIDADE_COMPRA[2].toString();

                } else {
                    ESCOLHA = Referencias.QUANTIDADE_COMPRA[3].toString();
                }


                numCarros.setText("x" + ESCOLHA);
            }
        });
    }

    private Carro getCarro(long id) {

        ArrayList<Carro> carros = getCarros();

        for (Carro carro : carros) {
            if (carro.getId() == id) {
                return carro;//.getModelo() + " (" + carro.getMarca() + ")";
            }
        }

        return null;
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
