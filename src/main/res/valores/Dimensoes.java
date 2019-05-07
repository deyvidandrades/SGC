package main.res.valores;

import java.awt.*;

public abstract class Dimensoes {

    //INTERFACES GRAFICAS//
    public static Dimension TELA = Toolkit.getDefaultToolkit().getScreenSize();
    public static Dimension DASH = new Dimension(800, 600);
    public static Dimension LOGIN = new Dimension(350, 620);

    public static Point getCentroTela(int x, int y) {
        return new Point((TELA.width - x) / 2, (TELA.height - y) / 2);
    }
}
