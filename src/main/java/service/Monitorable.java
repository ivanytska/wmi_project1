package service;

import entity.AbstractMonitorEntity;

import java.util.List;

public interface Monitorable {

    <T extends AbstractMonitorEntity> List<T> doBasicMonitor();

}
