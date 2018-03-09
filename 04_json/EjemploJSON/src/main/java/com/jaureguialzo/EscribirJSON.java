package com.jaureguialzo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class EscribirJSON {

    public static void main(String[] args) {

        List<Corredor> corredores = Arrays.asList(
                new Corredor(1, "Martín",
                        Arrays.asList(
                                new Marca("2018-03-09", 10000, 3600),
                                new Marca("2018-02-01", 9876.5, 1234.56)
                        )),
                new Corredor(2, "Paula")
        );

        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream("corredores.json");
            Writer w = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8));

            // REF: https://www.mkyong.com/java/how-to-enable-pretty-print-json-output-gson/
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(corredores);

            //String json = new Gson().toJson(corredores);
            w.write(json);

            w.flush();
            w.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
