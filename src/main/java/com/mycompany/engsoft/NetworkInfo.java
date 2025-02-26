package com.mycompany.engsoft;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;

public class NetworkInfo {
    private static final String TEST_DOWNLOAD_URL = "https://ash-speed.hetzner.com/100MB.bin"; // Arquivo de 100MB
    private static final String TEST_UPLOAD_URL = "https://postman-echo.com/post"; // Serviço de teste de upload

    // Método de Download
    public double medirVelocidadeDownload() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(TEST_DOWNLOAD_URL);
            Instant start = Instant.now();
            
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                HttpEntity entity = response.getEntity();
                long contentLength = entity.getContentLength();
                Instant end = Instant.now();
                long timeTakenMillis = java.time.Duration.between(start, end).toMillis();
                
                return (contentLength / (double) timeTakenMillis) * 1000.0 / (1024 * 1024);
            }
        } catch (IOException e) {
            return -1;
        }
    }

    // Método de Upload
    public double medirVelocidadeUpload() {
        try {
            byte[] testData = new byte[5 * 1024 * 1024]; // 5MB de dados fictícios para upload
            URL url = new URL(TEST_UPLOAD_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            Instant start = Instant.now();
            try (OutputStream os = connection.getOutputStream()) {
                os.write(testData);
            }
            Instant end = Instant.now();
            long timeTakenMillis = java.time.Duration.between(start, end).toMillis();
            
            return (testData.length / (double) timeTakenMillis) * 1000.0 / (1024 * 1024);
        } catch (IOException e) {
            return -1;
        }
    }
}
