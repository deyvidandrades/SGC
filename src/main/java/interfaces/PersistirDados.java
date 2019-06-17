package main.java.interfaces;

import main.java.entidades.*;
import main.res.valores.Strings;
import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public interface PersistirDados {
    File file = new File(Strings.DADOS);

    private JSONArray getDados(String key) {
        String content = "";

        try {
            content = FileUtils.readFileToString(file, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }


        return new JSONObject(content).getJSONArray(key);
    }

    default ArrayList<Carro> getCarros() {
        ArrayList<Carro> carros = new ArrayList<>();

        for (Object object : getDados(Strings.DADOS_CARROS)) {
            ObjectMapper m = new ObjectMapper();

            Carro c = null;
            try {
                c = m.readValue(object.toString(), Carro.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            carros.add(c);
        }

        return carros;
    }

    default ArrayList<Cliente> getClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();

        for (Object object : getDados(Strings.DADOS_CLIENTES)) {
            ObjectMapper m = new ObjectMapper();

            Cliente c = null;
            try {
                c = m.readValue(object.toString(), Cliente.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            clientes.add(c);
        }

        return clientes;
    }

    default ArrayList<Funcionario> getFuncionarios() {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        for (Object object : getDados(Strings.DADOS_FUNCIONARIOS)) {
            ObjectMapper m = new ObjectMapper();

            Funcionario f = null;
            try {
                f = m.readValue(object.toString(), Funcionario.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            funcionarios.add(f);
        }

        return funcionarios;
    }

    default ArrayList<Venda> getVendas() {
        ArrayList<Venda> vendas = new ArrayList<>();

        for (Object object : getDados(Strings.DADOS_VENDAS)) {
            ObjectMapper m = new ObjectMapper();

            Venda v = null;
            try {
                v = m.readValue(object.toString(), Venda.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            vendas.add(v);
        }

        return vendas;
    }

    default double getValorEmCaixa() {
        ObjectMapper m = new ObjectMapper();
        try {
            Empresa empresa = m.readValue(getDados(Strings.DADOS_EMPRESA).get(0).toString(), Empresa.class);
            return empresa.getCaixa();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0.0;
    }

    default void setDados(String key, Map objeto) {

        try {
            String content = FileUtils.readFileToString(file, "utf-8");
            JSONObject jsonObject = new JSONObject(content);

            JSONArray jsonArray = (JSONArray) jsonObject.get(key);
            jsonArray.put(objeto);

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    default void atualizarCarro(long carroId, Map map) {
        try {
            String content = FileUtils.readFileToString(file, "utf-8");
            JSONObject jsonObject = new JSONObject(content);

            JSONArray jsonArray = (JSONArray) jsonObject.get(Strings.DADOS_CARROS);

            int i = 0, indice = 0;
            for (Object object : jsonArray) {
                ObjectMapper m = new ObjectMapper();

                Carro carro = null;
                try {
                    carro = m.readValue(object.toString(), Carro.class);

                    if (carro.getId() == carroId) {
                        indice = i;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                i++;
            }

            jsonArray.remove(indice);
            jsonArray.put(map);

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    default void atualizarFuncionario(long funcionarioId, Map map) {
        try {
            String content = FileUtils.readFileToString(file, "utf-8");
            JSONObject jsonObject = new JSONObject(content);

            JSONArray jsonArray = (JSONArray) jsonObject.get(Strings.DADOS_FUNCIONARIOS);

            int i = 0, indice = 0;
            for (Object object : jsonArray) {
                ObjectMapper m = new ObjectMapper();

                Funcionario funcionario = null;
                try {
                    funcionario = m.readValue(object.toString(), Funcionario.class);

                    if (funcionario.getId() == funcionarioId) {
                        indice = i;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                i++;
            }

            jsonArray.remove(indice);
            jsonArray.put(map);

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    default void atualizarCaixa(Map map) {
        try {
            String content = FileUtils.readFileToString(file, "utf-8");
            JSONObject jsonObject = new JSONObject(content);

            JSONArray jsonArray = (JSONArray) jsonObject.get(Strings.DADOS_EMPRESA);

            int i = 0, indice = 0;
            for (Object object : jsonArray) {
                ObjectMapper m = new ObjectMapper();

                Empresa empresa = null;
                try {
                    empresa = m.readValue(object.toString(), Empresa.class);
                    indice = i;
                } catch (IOException e) {
                    e.printStackTrace();
                }

                i++;
            }

            jsonArray.remove(indice);
            jsonArray.put(map);

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    default void deletarFuncionario(long funcionarioId) {
        try {
            String content = FileUtils.readFileToString(file, "utf-8");
            JSONObject jsonObject = new JSONObject(content);

            JSONArray jsonArray = (JSONArray) jsonObject.get(Strings.DADOS_FUNCIONARIOS);

            int i = 0, indice = 0;
            for (Object object : jsonArray) {
                ObjectMapper m = new ObjectMapper();

                Funcionario funcionario = null;
                try {
                    funcionario = m.readValue(object.toString(), Funcionario.class);

                    if (funcionario.getId() == funcionarioId) {
                        indice = i;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                i++;
            }

            jsonArray.remove(indice);

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
