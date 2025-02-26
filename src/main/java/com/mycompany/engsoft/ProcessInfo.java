package com.mycompany.engsoft;

import java.util.List;
import oshi.SystemInfo;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

public class ProcessInfo {
    private final SystemInfo systemInfo = new SystemInfo();
    private final OperatingSystem os = systemInfo.getOperatingSystem();

    public List<OSProcess> getTopProcessos(int limit) {
        return List.of(os.getProcesses(limit, OperatingSystem.ProcessSort.CPU));
    }
}
