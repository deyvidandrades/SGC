package main.java.gui;

import main.java.assistentes.IniciarGUI;
import main.java.entidades.Funcionario;
import main.java.interfaces.FrameInterface;
import main.java.interfaces.PersistirDados;
import main.res.valores.Dimensoes;
import main.res.valores.Referencias;
import main.res.valores.Strings;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class CadastroFuncionarios implements FrameInterface, PersistirDados {
    private JTable table1;
    private JButton cadastrarFuncionarioButton;
    private JButton removerUsuarioButton;
    private JPasswordField passwordField1;
    private JFormattedTextField nomeText;
    private JCheckBox privilegioCheckBox;
    private JPasswordField senhaText;
    private JComboBox<Object> comboCargo;
    private JPanel panel1;
    private JButton voltarButton;
    private JFormattedTextField loginText;

    private boolean check = false;

    public CadastroFuncionarios() {

        configuraTabela(getDados(Strings.DADOS_FUNCIONARIOS));
        configuraComboBox();

        privilegioCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                check = e.getStateChange() == ItemEvent.SELECTED;
            }
        });


        cadastrarFuncionarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if (!nomeText.getText().equals("") && !senhaText.getText().equals("") && !loginText.getText().equals("")) {
                    Funcionario funcionario = new Funcionario(
                            nomeText.getText(),
                            senhaText.getText(),
                            Objects.requireNonNull(comboCargo.getSelectedItem()).toString(),
                            loginText.getText(),
                            check);

                    setDados(Strings.DADOS_FUNCIONARIOS, funcionario.toMap());
                    JOptionPane.showMessageDialog(frame, Strings.MENSAGEM_NOVO_FUNCIONARIO);

                    configuraTabela(getDados(Strings.DADOS_FUNCIONARIOS));

                } else {
                    JOptionPane.showMessageDialog(frame, Strings.MENSAGEM_LOGIN_INVALIDO);
                }
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                IniciarGUI.show(Referencias.DASH_GERENTE);
            }
        });

    }

    private void configuraComboBox() {

        DefaultComboBoxModel<Object> comboBoxModel = new DefaultComboBoxModel<>(Referencias.COLUNAS_CARGOS);

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

        comboCargo.setModel(comboBoxModel);
    }

    private void configuraTabela(JSONArray jsonArray) {

        DefaultTableModel model = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }

        };
        for (Object name : Referencias.COLUNAS_FUNCIONARIOS) {
            model.addColumn(name);
        }

        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            Object[] objects = {jsonObject.get("nome"), jsonObject.get("cargo"), jsonObject.get("acesso")};
            model.addRow(objects);
        }

        table1.setModel(model);

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
