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
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class CadastroFuncionarios implements FrameInterface, PersistirDados {
    private JTable tabelaFuncionarios;
    private JButton cadastrarFuncionarioButton;
    private JFormattedTextField nomeText;
    private JCheckBox privilegioCheckBox;
    private JPasswordField senhaText;
    private JComboBox<Object> comboCargo;
    private JPanel panel1;
    private JButton voltarButton;
    private JFormattedTextField loginText;
    private JTextField busca;

    private Funcionario FUNCIONARIO;
    private boolean check = false;

    public CadastroFuncionarios() {

        configuraTabela(false);
        configuraComboBox();

        privilegioCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                check = e.getStateChange() == ItemEvent.SELECTED;
            }
        });

        privilegioCheckBox.setSelected(false);

        comboCargo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (Objects.requireNonNull(comboCargo.getSelectedItem()).toString().equals(Referencias.COLUNAS_CARGOS[1])) {
                    privilegioCheckBox.setSelected(true);
                    privilegioCheckBox.setEnabled(false);

                } else if (Objects.requireNonNull(comboCargo.getSelectedItem()).toString().equals(Referencias.COLUNAS_CARGOS[2])) {
                    privilegioCheckBox.setSelected(false);
                    privilegioCheckBox.setEnabled(false);

                } else {
                    privilegioCheckBox.setEnabled(true);
                    privilegioCheckBox.setSelected(false);
                }
            }
        });

        cadastrarFuncionarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if (!nomeText.getText().equals("") && !String.valueOf(senhaText.getPassword()).equals("") && !loginText.getText().equals("")) {
                    Funcionario funcionario = new Funcionario(
                            nomeText.getText(),
                            String.valueOf(senhaText.getPassword()),
                            Objects.requireNonNull(comboCargo.getSelectedItem()).toString(),
                            loginText.getText(),
                            check);

                    setDados(Strings.DADOS_FUNCIONARIOS, funcionario.toMap());
                    JOptionPane.showMessageDialog(frame, Strings.MENSAGEM_NOVO_FUNCIONARIO);

                    configuraTabela(false);
                    IniciarGUI.show(Referencias.CADASTRAR_FUNCIONARIO);
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

        busca.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                configuraTabela(true);
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {

            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

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

    private void configuraTabela(boolean buscar) {
        ArrayList<Funcionario> funcionarios = getFuncionarios();

        DefaultTableModel model = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }

        };
        for (Object name : Referencias.COLUNAS_FUNCIONARIOS) {
            model.addColumn(name);
        }

        ArrayList<Funcionario> arrayListFuncionarios = new ArrayList<>();

        for (Funcionario funcionario : funcionarios) {
            if (!funcionario.getLogin().equals(Strings.ADMIN)) {
                if (buscar && !busca.getText().isEmpty()) {
                    if (funcionario.getNome().toUpperCase().contains(busca.getText().toUpperCase())) {
                        arrayListFuncionarios.add(funcionario);

                        String acesso;
                        if (funcionario.getAcesso()) {
                            acesso = "Sim";
                        } else {
                            acesso = "Não";
                        }

                        Object[] objects = {funcionario.getNome(), funcionario.getCargo(), acesso};
                        model.addRow(objects);
                    }
                } else {
                    arrayListFuncionarios.add(funcionario);

                    String acesso;
                    if (funcionario.getAcesso()) {
                        acesso = "Sim";
                    } else {
                        acesso = "Não";
                    }

                    Object[] objects = {funcionario.getNome(), funcionario.getCargo(), acesso};
                    model.addRow(objects);
                }
            }
        }

        tabelaFuncionarios.setModel(model);

        tabelaFuncionarios.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tabelaFuncionarios.rowAtPoint(evt.getPoint());
                int col = tabelaFuncionarios.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    Referencias.FUNCIONARIO_TABELA = arrayListFuncionarios.get(tabelaFuncionarios.getSelectedRow());
                    IniciarGUI.show(Referencias.DADOS_FUNCIONARIOS);
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
