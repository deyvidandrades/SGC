package main.java.gui;

import main.java.assistentes.IniciarGUI;
import main.java.entidades.Funcionario;
import main.java.interfaces.FrameInterface;
import main.java.interfaces.PersistirDados;
import main.res.valores.Dimensoes;
import main.res.valores.Referencias;
import main.res.valores.Strings;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class DadosFuncionarios implements FrameInterface, PersistirDados {
    private JPanel panel1;
    private JButton atualizarButton;
    private JButton deletarButton;
    private JButton voltarButton;
    private JComboBox<Object> comboBoxCargo;
    private JCheckBox nivelDeAcessoCheckBox;
    private JTextField textNome;
    private JLabel nomeFunc;
    private JLabel idUser;
    private JPasswordField senha;
    private JTextField login;


    public DadosFuncionarios(Funcionario funcionario) {
        setGUI(funcionario);
        configuraComboBox(funcionario);

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                IniciarGUI.show(Referencias.CADASTRAR_FUNCIONARIO);
            }
        });

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!textNome.getText().equals("") && !String.valueOf(senha.getPassword()).equals("") && !login.getText().equals("")) {

                    Funcionario f = new Funcionario(
                            textNome.getText(),
                            senha.getText(),
                            Objects.requireNonNull(comboBoxCargo.getSelectedItem()).toString(),
                            login.getText(),
                            nivelDeAcessoCheckBox.isSelected(),
                            funcionario.getId());

                    atualizarFuncionario(funcionario.getId(), f.toMap());
                    JOptionPane.showMessageDialog(frame, Strings.MENSAGEM_FUNCIONARIO_ATUALIZADO);

                    IniciarGUI.show(Referencias.CADASTRAR_FUNCIONARIO);
                } else {
                    JOptionPane.showMessageDialog(frame, Strings.MENSAGEM_LOGIN_INVALIDO);
                }
            }
        });

        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                deletarFuncionario(funcionario.getId());
                JOptionPane.showMessageDialog(frame, Strings.MENSAGEM_FUNCIONARIO_DELETADO);
                IniciarGUI.show(Referencias.CADASTRAR_FUNCIONARIO);
            }
        });
    }

    private void setGUI(Funcionario funcionario) {
        textNome.setText(funcionario.getNome());
        nomeFunc.setText(funcionario.getNome());
        nivelDeAcessoCheckBox.setSelected(funcionario.getAcesso());
        idUser.setText(String.format("ID: %d", funcionario.getId()));
        senha.setText(String.valueOf(funcionario.getSenha()));
        login.setText(funcionario.getLogin());
    }

    private void configuraComboBox(Funcionario funcionario) {

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

        comboBoxCargo.setModel(comboBoxModel);
        comboBoxCargo.setSelectedItem(funcionario.getCargo());
    }

    @Override
    public void show() {
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setTitle(Strings.DAL_COMPLETO);
        frame.setSize(Dimensoes.DADOS_FUNCIONARIOS);
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
