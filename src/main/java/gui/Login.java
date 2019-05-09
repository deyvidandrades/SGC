package main.java.gui;

import main.java.assistentes.IniciarGUI;
import main.java.assistentes.VerificaFuncionario;
import main.java.entidades.Funcionario;
import main.java.interfaces.FrameInterface;
import main.res.valores.Dimensoes;
import main.res.valores.Referencias;
import main.res.valores.Strings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Login extends VerificaFuncionario implements FrameInterface {

    private JPanel panel1;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JLabel img;
    private JButton acessarButton;
    private JLabel footer;

    public Login() {
        super();

        img.setIcon(icone);
        footer.setText(Strings.FOOTER + "  v" + Strings.VERSAO);

        acessarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Funcionario funcionario = new Funcionario("", passwordField1.getText(), "", textField1.getText(), false);

                if (logar(funcionario)) {
                    IniciarGUI.show(Referencias.DASH_GERENTE);

                } else
                    JOptionPane.showMessageDialog(null, Strings.MENSAGEM_LOGIN_INVALIDO);
            }
        });

    }

    @Override
    public void show() {
        frame.setContentPane(new Login().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setTitle(String.format("%s %s", Strings.LOGIN, Strings.DAL));
        frame.setSize(Dimensoes.LOGIN);
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
        frame.hide();
    }
}
