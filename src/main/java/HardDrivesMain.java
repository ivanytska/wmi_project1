import entity.DiskMonitorEntity;
import entity.DiskOperationsEntity;
import entity.DiskProcessEntity;
import enums.DiskIdentifier;
import service.CimExecutorFactory;
import service.DiskMonitor;

import java.util.List;

public class HardDrivesMain {
    public static void main(String[] args) {
        CimExecutorFactory factory = new CimExecutorFactory();

        DiskMonitor diskMonitor = factory.newInstance(DiskMonitor.class);

        //TODO: Вывод данных о доступных в системе дисках
        List<DiskMonitorEntity> diskMonitorEntities = diskMonitor.doBasicMonitor();
        diskMonitorEntities.forEach(System.out::println);

        ////TODO: Отрисовка производительности указанного диска (C) в данный момент
        DiskOperationsEntity operationsEntity = diskMonitor.doUsageTraceMonitor(DiskIdentifier.C);
        System.out.println(operationsEntity);

        ////TODO: Отрисовка запущенных процессов в системе
        List<DiskProcessEntity> processEntities = diskMonitor.doDiskProcessesMonitor();
        processEntities.forEach(System.out::println);

    }
}
