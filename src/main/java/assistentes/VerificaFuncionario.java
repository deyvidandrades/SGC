package main.java.assistentes;

import main.java.entidades.Funcionario;
import main.java.interfaces.PersistirDados;
import main.res.valores.Strings;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class VerificaFuncionario extends PersistirDados {

    public boolean logar(Funcionario funcionario) {

        JSONArray funcionarios = getDados(Strings.DADOS_FUNCIONARIOS);
        boolean logado = false;

        for (Object o : funcionarios) {
            JSONObject jsonObject = (JSONObject) o;

            Funcionario f = new Funcionario(jsonObject);
            if (funcionario.getNome().equals(f.getNome()) && funcionario.getSenha().equals(f.getSenha()))
                logado = true;
        }

        return logado;
    }
}
