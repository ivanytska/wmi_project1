package entity;

public class DiskProcessEntity {

    private String processName;

    private Long processId;

    private double processWorkingSet;

    private String processExecutablePath;

    public DiskProcessEntity(String processName, Long processId, double processWorkingSet, String processExecutablePath) {
        this.processName = processName;
        this.processId = processId;
        this.processWorkingSet = processWorkingSet;
        this.processExecutablePath = processExecutablePath;
    }

    @Override
    public String toString() {
        return "Name: " + this.processName + "  PID: " + this.processId + "  WS(MB): " + this.processWorkingSet + "  Path: " + this.processExecutablePath;
    }
}
