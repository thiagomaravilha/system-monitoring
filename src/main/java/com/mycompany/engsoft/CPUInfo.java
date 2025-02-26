package com.mycompany.engsoft;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

public class CPUInfo {
    private final SystemInfo systemInfo = new SystemInfo();
    private final CentralProcessor processor = systemInfo.getHardware().getProcessor();
    
    public double[] getUsoCPU() {
        return processor.getProcessorCpuLoadBetweenTicks();
    }
}
