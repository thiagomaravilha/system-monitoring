package com.mycompany.engsoft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProcessInfo {
    public static List<String> getProcesses() {
        List<String> processes = new ArrayList<>();
        String os = System.getProperty("os.name").toLowerCase();
        String command;

        if (os.contains("win")) {
            command = "tasklist";
        } else {
            command = "ps -eo pid,comm,%mem --sort=-%mem"; // Linux: PID, Nome, Memória
        }

        try {
            Process process = Runtime.getRuntime().exec(command);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                
                boolean isHeader = true;
                while ((line = reader.readLine()) != null) {
                    if (isHeader) {
                        isHeader = false; // Pula a primeira linha do output
                        continue;
                    }
                    
                    if (os.contains("win")) {
                        String[] parts = line.trim().split("\\s+", 6); // Divide pelo espaço
                        if (parts.length >= 5) {
                            String nome = parts[0];
                            String pid = parts[1];
                            String memoria = parts[4];
                            processes.add(String.format("PID: %s | Nome: %s | Memória: %s", pid, nome, memoria));
                        }
                    } else {
                        String[] parts = line.trim().split("\\s+", 3);
                        if (parts.length == 3) {
                            String pid = parts[0];
                            String nome = parts[1];
                            String memoria = parts[2] + "%";
                            processes.add(String.format("PID: %s | Nome: %s | Memória: %s", pid, nome, memoria));
                        }
                    }
                }
            }
        } catch (IOException e) {
        }

        return processes;
    }
}
