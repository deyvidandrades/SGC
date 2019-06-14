package main.java.interfaces;

import main.java.assistentes.IniciarGUI;
import main.res.valores.Dimensoes;
import main.res.valores.Strings;

import javax.swing.*;
import java.awt.*;

public interface FrameInterface {

    JFrame frame = new JFrame();
    Dimension tamanhoTela = Dimensoes.TELA;
    ImageIcon icone = new ImageIcon(Strings.ICONE);

    void show();

    void hide();

    default void persistir() {
    }

    default void voltar(short id) {
        IniciarGUI.show(id);
    }
}
