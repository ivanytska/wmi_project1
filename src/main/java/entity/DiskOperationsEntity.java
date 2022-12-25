package entity;

public class DiskOperationsEntity extends AbstractMonitorEntity {

    private String osName;

    private String idleTimePercent;

    private String readTimePercent;

    private String writeTimePercent;

    public DiskOperationsEntity(String osName, Long idleTimePercent, Long readTimePercent, Long writeTimePercent)

    {
        this.osName = osName;
        this.idleTimePercent = String.format("%s", idleTimePercent);
        this.readTimePercent = String.format("%s", readTimePercent);
        this.writeTimePercent = String.format("%s", writeTimePercent);
    }

    @Override
    public String toString()

    {
        return "\nDISK: " + this.osName + "\\ "
                + "IDLE (%): " + this.idleTimePercent + "%  "
                + "READ (%): " + this.readTimePercent + "%  "
                + "WRITE (%): " + this.writeTimePercent + "% \n";
    }
}
