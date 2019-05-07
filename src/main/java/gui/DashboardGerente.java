package main.java.gui;

import main.java.interfaces.FrameInterface;
import main.java.interfaces.PersistirDados;
import main.res.valores.Dimensoes;
import main.res.valores.Referencias;
import main.res.valores.Strings;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class DashboardGerente implements FrameInterface, PersistirDados {

    public JPanel panel1;
    private JTable table1;
    private JTable table2;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel img;
    private JButton cadastrarFuncionarioButton;
    private JButton comprarVeiculoButton;
    private JLabel ola;

    public DashboardGerente() {
        super();

        assert false;
        img.setIcon(icone);

        ola.setText(String.format("Olá %s!", Referencias.FUNCIONARIO.getNome()));

        comprarVeiculoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                JOptionPane.showMessageDialog(frame, Strings.MENSAGEM_NAO_IMPLEMENTADO);
            }
        });

        cadastrarFuncionarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                JOptionPane.showMessageDialog(frame, Strings.MENSAGEM_NAO_IMPLEMENTADO);
            }
        });

        configuraTabela(getDados(Strings.DADOS_CLIENTES));
    }

    private void configuraTabela(JSONArray jsonArray) {

        DefaultTableModel model = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }

        };

        for (String name : Referencias.COLUNAS_VENDAS) {
            model.addColumn(name);
        }

        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            Object[] objects = {jsonObject.get("id"), jsonObject.get("nome"), jsonObject.get("documento"), jsonObject.get("idade")};
            model.addRow(objects);
        }

        table1.setModel(model);
        table2.setModel(model);

        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table1.rowAtPoint(evt.getPoint());
                int col = table1.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {

                    JOptionPane.showMessageDialog(frame, Strings.MENSAGEM_NAO_IMPLEMENTADO);
                }
            }
        });
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

