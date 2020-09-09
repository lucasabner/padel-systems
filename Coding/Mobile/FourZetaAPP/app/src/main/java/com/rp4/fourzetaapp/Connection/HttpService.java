package com.rp4.fourzetaapp.Connection;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rp4.fourzetaapp.model.Circuito;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HttpService extends AsyncTask<Void, Void, List<Circuito>> {

//    private final String circuito;

//    public HttpService(String circuito) {
//        this.circuito = circuito;
////    }
    public HttpService() {}

    @Override
    protected List<Circuito> doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();

//        if (this.circuito != null) {
            try {
                URL url = new URL("http://10.0.2.2:8080/circuito");

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);
                connection.setConnectTimeout(5000);
                connection.connect();

                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    resposta.append(scanner.next());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
//        }

        List<Circuito> circuitos = null;
        if(resposta != null) {
            circuitos = new Gson().fromJson(resposta.toString(), new TypeToken<List<Circuito>>() {
            }.getType());
        }

        return circuitos;
    }
}
