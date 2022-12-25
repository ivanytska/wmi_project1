package service;

public final class CimExecutorFactory {

    public CimExecutorFactory() {
    }

    public <T extends Monitorable> T newInstance(Class<T> clazz) {
        return (clazz.equals(DiskMonitor.class)) ? (T) new DiskMonitor() : null;
    }


}
