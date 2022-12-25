package enums;

public enum DiskIdentifier {

    C("C:"),
    E("E:"),
    X("X:"),
    F("F:"),
    D("D:");

    private final String diskName;

    DiskIdentifier(String diskValue) {
        this.diskName = diskValue;
    }

    public String getDiskName() {
        return diskName;
    }
}
