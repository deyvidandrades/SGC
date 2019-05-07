package main.java.interfaces;

import main.res.valores.Strings;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public interface PersistirDados {
    File file = new File(Strings.DADOS);

    default JSONArray getDados(String key) {
        String content = "";

        try {
            content = FileUtils.readFileToString(file, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new JSONObject(content).getJSONArray(key);
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
}
