package service;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.EnumVariant;
import com.jacob.com.Variant;
import entity.AbstractMonitorEntity;
import entity.DiskMonitorEntity;
import entity.DiskOperationsEntity;
import entity.DiskProcessEntity;
import enums.DiskActions;
import enums.DiskIdentifier;

import java.util.ArrayList;
import java.util.List;

public class DiskMonitor extends AbstractCimMonitor implements Monitorable {

    @Override
    public <T extends AbstractMonitorEntity> List<T> doBasicMonitor() {

        List<T> basicDisksList = new ArrayList<>();

        activeXComponent = new ActiveXComponent(cimQuery);

        Variant vCollection = activeXComponent
                .invoke("ExecQuery", new Variant(DiskActions.BASIC_DISKS_INFO.getDiskQuery()));
        EnumVariant enumVariant = new EnumVariant(vCollection.toDispatch());

        Dispatch item = item = enumVariant.nextElement().toDispatch();

        String deviceId = Dispatch.call(item, "DeviceID").toString();
        Long driveType =
                Long.parseLong(Dispatch.call(item, "DriveType").toString());

        double diskSize =
                Double.parseDouble(Dispatch.call(item, "Size").toString());

        double diskFreeSize =
                Double.parseDouble(Dispatch.call(item, "FreeSpace").toString());

        diskSize = Math.round(diskSize * (9.31 * (Math.pow(10, -10))));
        diskFreeSize = Math.round(diskFreeSize * (9.31 * (Math.pow(10, -10))));

        DiskMonitorEntity diskEntity = new DiskMonitorEntity(
                deviceId,
                driveType,
                diskSize,
                diskFreeSize
        );

        basicDisksList.add((T) diskEntity);

        return basicDisksList;

    }

    public <T extends DiskOperationsEntity> T doUsageTraceMonitor(DiskIdentifier identifier) {

        List<T> diskRecoursesList = new ArrayList<>();

        activeXComponent = new ActiveXComponent(cimQuery);

        String parsedQuery =
                String.format(DiskActions.REALTIME_DISK_USAGE.getDiskQuery(), identifier.getDiskName());

        Variant vCollection = activeXComponent
                .invoke("ExecQuery", new Variant(parsedQuery));
        EnumVariant enumVariant = new EnumVariant(vCollection.toDispatch());

        Dispatch item = enumVariant.nextElement().toDispatch();

        String osName = Dispatch.call(item, "Name").toString();
        Long idleUsage =
                Long.valueOf(Dispatch.call(item, "PercentIdleTime").toString());

        Long readUsage =
                Long.valueOf(Dispatch.call(item, "PercentDiskReadTime").toString());

        Long writeUsage = Long.valueOf(Dispatch.call(item, "PercentDiskWriteTime").toString());

        DiskOperationsEntity operationsEntity = new DiskOperationsEntity(
                osName,
                idleUsage,
                readUsage,
                writeUsage
        );

        return (T) operationsEntity;
    }

    public <T extends DiskProcessEntity> List<T> doDiskProcessesMonitor() {

        List<T> processesList = new ArrayList<>();

        activeXComponent = new ActiveXComponent(cimQuery);

        Variant vCollection = activeXComponent
                .invoke("ExecQuery", new Variant(DiskActions.TASKS_MONITOR_INFO.getDiskQuery()));
        EnumVariant enumVariant = new EnumVariant(vCollection.toDispatch());

        Dispatch item;

        while (enumVariant.hasMoreElements()) {
            item = enumVariant.nextElement().toDispatch();

            String processName = Dispatch.call(item, "Name").toString();

            Long processId = Long.parseLong(Dispatch.call(item, "ProcessID").toString());

            double processWorkingSet = Long.parseLong(Dispatch.call(item, "WorkingSetSize").toString());
            processWorkingSet = Math.round(processWorkingSet * (9.53 * Math.pow(10, -7)));

            String processExecutablePath = Dispatch.call(item, "ExecutablePath").toString();

            if (processExecutablePath.equals("null")) {
                processExecutablePath = "DEFAULT";
            }

            DiskProcessEntity processEntity = new DiskProcessEntity(
                    processName,
                    processId,
                    processWorkingSet,
                    processExecutablePath
            );

            processesList.add((T) processEntity);
        }

        return processesList;
    }
}
