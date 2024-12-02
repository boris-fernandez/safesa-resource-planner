/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.safesa.app.model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 *
 * @author BORIS
 */
public class ConsultaApi {
    private String apikey;

    public ConsultaApi() {
        this.apikey = "apikey";
    }
    
    public VerificarEmailDTO verificarEmail(String email) {
        URI direccion = URI.create("https://api.trueguard.io/verification");
        HttpClient client = HttpClient.newHttpClient();
        String jsonBody = "{" +
                "\"email\": \"" + email + "\"," +
                "\"type\": \"full\"," +
                "\"ip\": \"1.2.3.4\"" +
                "}";
        HttpRequest request = HttpRequest.newBuilder()
        .uri(direccion)
        .header("X-API-KEY", this.apikey)
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
        .build();
         try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), VerificarEmailDTO.class);
        } catch (JsonSyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException("Problemas con la API", e);
        }
    }
    
}
