package main.java.gui;

import main.java.entidades.Carro;
import main.java.entidades.Venda;
import main.java.interfaces.FrameInterface;
import main.java.interfaces.PersistirDados;
import main.res.valores.Dimensoes;
import main.res.valores.Referencias;
import main.res.valores.Strings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TelaCarros implements PersistirDados, FrameInterface {
    private JPanel panel1;
    private JList carList;
    private JLabel carImg;
    private JButton sair;

    public TelaCarros() {
        Venda vendax = Referencias.CLICKVENDAS;
        ArrayList<Carro> carro = getCarros();
        DefaultListModel modelo = new DefaultListModel();
        for (Carro C : carro) {
            if (C.getId() == vendax.getCarroID()) {

                modelo.addElement("Marca: " + C.getMarca());
                modelo.addElement("Modelo: " + C.getModelo());
                modelo.addElement("Ano: " + C.getAno());
                modelo.addElement("Cor: " + C.getCor());
                modelo.addElement("Câmbio: " + C.getCambio());
                modelo.addElement("Combustível: " + C.getCombustivel());
                modelo.addElement("Num Portas: " + C.getNumPortas());
                modelo.addElement("Km: " + C.getQuilometragem());
                modelo.addElement("Torque: " + C.getTorque());
                modelo.addElement("Valor: " + C.getPreco());
                modelo.addElement("ID: " + C.getId());

            }
        }
        carList.setModel(modelo);
        carImg.setIcon(icone);
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                voltar(Referencias.ACESSAR_VENDA);
            }
        });
    }

    @Override
    public void show() {
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setTitle(Strings.DAL_COMPLETO);
        frame.setSize(Dimensoes.INFOCARROS);
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


