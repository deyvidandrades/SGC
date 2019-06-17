package main.java.assistentes;

import main.java.entidades.Funcionario;
import main.java.interfaces.PersistirDados;
import main.res.valores.Referencias;

import java.util.ArrayList;

public abstract class VerificaFuncionario implements PersistirDados {

    public Funcionario logar(Funcionario funcionario) {

        ArrayList<Funcionario> funcionarios = getFuncionarios();

        for (Funcionario f : funcionarios) {
            if (funcionario.getLogin().equals(f.getLogin()) && funcionario.getSenha().equals(f.getSenha())) {
                Referencias.FUNCIONARIO = f;
                return f;
            }
        }
        return null;
    }
}
