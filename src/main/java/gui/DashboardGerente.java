package main.java.gui;

import main.java.interfaces.FrameInterface;
import main.res.valores.Dimensoes;
import main.res.valores.Referencias;
import main.res.valores.Strings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class DashboardGerente implements FrameInterface {

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

        img.setIcon(icone);
        ola.setText(String.format("Olá %s!", Referencias.FUNCIONARIO.getNome()));

        comprarVeiculoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                JOptionPane.showMessageDialog(frame, "Não implementado");
            }
        });

        cadastrarFuncionarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                JOptionPane.showMessageDialog(frame, "Não implementado");
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

