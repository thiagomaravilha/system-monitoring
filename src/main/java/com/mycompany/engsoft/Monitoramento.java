package com.mycompany.engsoft;

import oshi.hardware.HWDiskStore;
import oshi.software.os.OSProcess;

public class Monitoramento {
    public static void main(String[] args) throws InterruptedException {
        CPUInfo cpuInfo = new CPUInfo();
        MemoryInfo memoryInfo = new MemoryInfo();
        ProcessInfo processInfo = new ProcessInfo();
        StorageInfo storageInfo = new StorageInfo();
        NetworkInfo networkInfo = new NetworkInfo();
        
        while (true) {
            // CPU
            System.out.println("Uso da CPU por nucleo:");
            double[] usoCPU = cpuInfo.getUsoCPU();
            for (int i = 0; i < usoCPU.length; i++) {
                System.out.printf("Nucleo %d: %.2f%%\n", i, usoCPU[i] * 100);
            }
            
            // Memória
            System.out.println("\nUso de Memoria:");
            System.out.printf("Total: %.2f GB\n", memoryInfo.getTotalMemory() / 1e9);
            System.out.printf("Usada: %.2f GB\n", memoryInfo.getUsedMemory() / 1e9);
            System.out.printf("Disponivel: %.2f GB\n", memoryInfo.getAvailableMemory() / 1e9);
            System.out.println("\nUso de Swap:");
            System.out.printf("Total: %.2f GB\n", memoryInfo.getTotalSwap() / 1e9);
            System.out.printf("Usada: %.2f GB\n", memoryInfo.getUsedSwap() / 1e9);
            
            // Processos
            System.out.println("\nProcessos em execucao:");
            for (OSProcess processo : processInfo.getTopProcessos(0)) {
                System.out.printf("PID: %d | Nome: %s\n", processo.getProcessID(), processo.getName());
            }
            
            // Armazenamento
            System.out.println("\nDispositivos de Armazenamento:");
            for (HWDiskStore dispositivo : storageInfo.getDispositivosDeArmazenamento()) {
                System.out.printf("Nome: %s | Modelo: %s | Capacidade Total: %.2f GB\n", 
                                  dispositivo.getName(), dispositivo.getModel(), dispositivo.getSize() / 1e9);
            }
            
            // Rede
            double velocidadeInternet = networkInfo.medirVelocidadeInternet();
            if (velocidadeInternet != -1) {
                System.out.printf("\nVelocidade de Download: %.2f Mbps\n", velocidadeInternet);
            } else {
                System.out.println("\nErro ao medir a velocidade da internet.");
            }
            
            System.out.println("-----------------------------------\n");
            Thread.sleep(1000);
        }
    }
}
