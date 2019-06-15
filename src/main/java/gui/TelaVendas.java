package main.java.gui;

import main.java.entidades.Carro;
import main.java.entidades.Cliente;
import main.java.entidades.Funcionario;
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

public class TelaVendas implements PersistirDados, FrameInterface {

    private JLabel compradorDoc;
    private JLabel marcaCar;
    private JLabel modCar;
    private JLabel anoCar;
    private JLabel corCar;
    private JLabel varValor;
    private JLabel logo;
    private JLabel compradorNome;
    private JPanel panel1;
    private JLabel imgCar;
    private JLabel idVenda;
    private JButton sair;
    private JLabel vendedorNome;
    private JLabel vendedorID;
    private JButton info;

    public TelaVendas() {
        Venda vendaSolicitada = Referencias.CLICKVENDAS;
        setClient(vendaSolicitada.getClienteID());
        setCarro(vendaSolicitada.getCarroID());
        setVendedor(vendaSolicitada.getFuncionarioID());
        idVenda.setText(String.valueOf(vendaSolicitada.getId()));

        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                voltar(Referencias.DASH_GERENTE);
            }
        });
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                voltar(Referencias.ACESSAR_INFO);
            }
        });
    }

    private void setClient(long ID) {
        ArrayList<Cliente> clienteSolicitado = getClientes();
        for (Cliente C : clienteSolicitado) {
            if (C.getId() == ID) {
                compradorNome.setText(C.getNome());
                compradorDoc.setText(C.getDocumento());
            }
        }

    }

    private void setCarro(long ID) {
        ArrayList<Carro> carroSolicitado = getCarros();
        for (Carro carro : carroSolicitado) {
            if (carro.getId() == ID) {
                marcaCar.setText(carro.getMarca() + " /");
                modCar.setText(carro.getModelo() + " /");
                anoCar.setText(carro.getAno() + " /");
                corCar.setText(carro.getCor());
                logo.setIcon(icone);
                varValor.setText("R$:" + carro.getPreco());
            }
        }

    }

    private void setVendedor(long ID) {
        ArrayList<Funcionario> funcionarioSolicitado = getFuncionarios();
        for (Funcionario F : funcionarioSolicitado) {
            if (F.getId() == ID) {
                vendedorNome.setText(F.getNome());
                vendedorID.setText(String.valueOf(F.getId()));
            }
        }
    }

    @Override
    public void show() {
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setTitle(Strings.DAL_COMPLETO);
        frame.setSize(Dimensoes.ACESSARVENDAS);
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