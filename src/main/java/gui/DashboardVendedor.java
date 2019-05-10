package main.java.gui;

import main.java.interfaces.FrameInterface;
import main.res.valores.Dimensoes;
import main.res.valores.Referencias;
import main.res.valores.Strings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DashboardVendedor implements FrameInterface {


    private JPanel panel1;
    private JButton button1;
    private JLabel label1;


    public DashboardVendedor() {
        button1.setText(Referencias.FUNCIONARIO.getNome());

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
