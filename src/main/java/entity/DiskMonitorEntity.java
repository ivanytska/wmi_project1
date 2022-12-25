package entity;

public class DiskMonitorEntity extends AbstractMonitorEntity {

    private String deviceId;

    private Long driveType;

    private double diskSize;

    private double diskFreeSpace;

    public DiskMonitorEntity(String deviceId, Long driveType, double diskSize, double diskFreeSpace) {
        this.deviceId = deviceId;
        this.driveType = driveType;
        this.diskSize = diskSize;
        this.diskFreeSpace = diskFreeSpace;
    }

    @Override
    public String toString()

    {
        return "ID: " + this.deviceId + "\\" +
                "  TYPE: " + this.driveType +
                "  Size(GB): " + this.diskSize +
                "  Free Space(GB): " + this.diskFreeSpace;
    }
}
