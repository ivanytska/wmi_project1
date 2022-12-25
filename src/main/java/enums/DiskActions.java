package enums;

public enum DiskActions {

    BASIC_DISKS_INFO("SELECT DeviceID, DriveType, Size, FreeSpace FROM Win32_LogicalDisk"),
    REALTIME_DISK_USAGE(
            "SELECT Name, PercentIdleTime, PercentDiskReadTime, PercentDiskWriteTime FROM Win32_PerfFormattedData_PerfDisk_LogicalDisk WHERE Name = '%s'"
    ),
    TASKS_MONITOR_INFO("SELECT Name, ProcessID, WorkingSetSize, ExecutablePath FROM Win32_Process");

    private final String diskQuery;

    DiskActions(String diskQuery) {
        this.diskQuery = diskQuery;
    }

    public String getDiskQuery() {
        return diskQuery;
    }

}
