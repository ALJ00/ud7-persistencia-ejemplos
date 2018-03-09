package com.jaureguialzo;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class LeerJSON {

    public static void main(String[] args) {

        try {

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream("corredores.json"), StandardCharsets.UTF_8)
            );

            // Java 8+
            String json = br.lines().collect(Collectors.joining());

            Gson gson = new Gson();

            Corredor[] corredores = gson.fromJson(json, Corredor[].class);

            for (Corredor corredor : corredores) {
                System.out.println(corredor);
            }

        } catch (IOException e) {
            System.err.println("ERROR: Error de E/S");
            System.err.println(e.getMessage());
        }

    }
}
