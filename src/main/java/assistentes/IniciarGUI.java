package main.java.assistentes;

import main.java.gui.DashboardGerente;
import main.java.gui.DashboardVendedor;
import main.java.gui.Login;
import main.res.valores.Referencias;

import javax.swing.*;

public abstract class IniciarGUI {

    public static void show(short ID) {
        switch (ID) {
            case Referencias.LOGIN:
                Login login = new Login();
                login.show();
                break;
            case Referencias.DASH_GERENTE:
                DashboardGerente dashboardGerente = new DashboardGerente();
                dashboardGerente.show();

                break;
            case Referencias.DASH_VENDEDOR:
                DashboardVendedor dashVendedor = new DashboardVendedor();
                dashVendedor.show();

                break;
            default:
                break;
        }
    }

    public static void hide(JFrame f) {
        f.setVisible(false);
    }
}
