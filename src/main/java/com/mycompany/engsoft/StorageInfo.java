package com.mycompany.engsoft;

import java.util.List;
import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;

public class StorageInfo {
    private final SystemInfo systemInfo = new SystemInfo();
    private final HardwareAbstractionLayer hardware = systemInfo.getHardware();
    
    public List<HWDiskStore> getDispositivosDeArmazenamento() {
        return List.of(hardware.getDiskStores());
    }
}
