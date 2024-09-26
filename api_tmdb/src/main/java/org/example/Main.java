package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class Main {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        String  nomeFilmes = "";


            System.out.println("Qual nome do filme");
            nomeFilmes = read.nextLine();
            nomeFilmes = nomeFilmes.replace(" ","%");


        try {
            URL url = new URL("https://api.themoviedb.org/3/search/movie?query=" + nomeFilmes + "&language=pt-BR");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", "Bearer 'SEU_ID_AQUI'");

            BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String linha;

            StringBuilder sb = new StringBuilder();

            while ((linha = bf.readLine()) != null) {
                sb.append(linha);
            }
            bf.close();

            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(sb.toString());

            System.out.printf(String.valueOf(json));

            JSONArray resultados = (JSONArray) json.get("results");

            for (Object resultado : resultados) {
                JSONObject filme = (JSONObject) resultado;
                System.out.println("-------------------------");
                System.out.println(filme.get("title"));
                System.out.println(filme.get("overview"));
                System.out.println(filme.get("release_date"));
                System.out.println(filme.get("vote_average"));
            }

        }catch (Exception e){
            System.out.println(e);
        }

    }

}