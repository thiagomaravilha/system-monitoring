package com.mycompany.engsoft;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;

public class MemoryInfo {
    
    private final SystemInfo systemInfo = new SystemInfo();
    private final GlobalMemory memory = systemInfo.getHardware().getMemory();

    public long getTotalMemory() {
        return memory.getTotal();
    }

    public long getAvailableMemory() {
        return memory.getAvailable();
    }

    public long getUsedMemory() {
        return getTotalMemory() - getAvailableMemory();
    }

    public long getTotalSwap() {
        return memory.getSwapTotal();
    }

    public long getUsedSwap() {
        return memory.getSwapUsed();
    }
}
