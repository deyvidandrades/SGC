package main.java.gui;

import main.java.entidades.Carro;
import main.java.entidades.Venda;
import main.java.interfaces.FrameInterface;
import main.java.interfaces.PersistirDados;
import main.java.interfaces.UriImagem;
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
import java.util.ArrayList;

public class TelaCarros implements PersistirDados, FrameInterface, UriImagem {

    private JPanel panel1;
    private JList<Object> carList;
    private JLabel carImg;
    private JButton sair;
    private int SOLO;

    public TelaCarros(Carro carro) {
        SOLO = 0;
        configuraGUI();
        configuraLista(carro);
    }

    public TelaCarros(Carro carro, int solo) {
        SOLO = solo;
        configuraGUI();
        configuraLista(carro);
    }

    public TelaCarros() {
        SOLO = 2;
        Venda vendax = Referencias.CLICKVENDAS;
        configuraGUI();

        ArrayList<Carro> carro = getCarros();
        for (Carro C : carro) {
            if (vendax.getCarroID() == C.getId()) {
                configuraLista(C);
            }
        }
    }

    private void configuraGUI() {
        carImg.setIcon(icone);

        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                switch (SOLO) {
                    case 0:
                        voltar(Referencias.DASH_GERENTE);
                        break;
                    case 1:
                        voltar(Referencias.DASH_VENDEDOR);
                        break;
                    case 2:
                        voltar(Referencias.ACESSAR_VENDA);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void configuraLista(Carro C) {
        DefaultListModel<Object> modelo = new DefaultListModel<>();
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

        ImageIcon carroIcone = new ImageIcon(new ImageIcon(getURI(C.getImg())).getImage().getScaledInstance(250, 200, Image.SCALE_DEFAULT));
        carImg.setIcon(carroIcone);

        carList.setModel(modelo);
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


