package com.mycompany.engsoft;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.time.Instant;

public class NetworkInfo {
    private static final String TEST_URL = "https://ash-speed.hetzner.com/100MB.bin";
    
    public double medirVelocidadeInternet() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(TEST_URL);
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
}