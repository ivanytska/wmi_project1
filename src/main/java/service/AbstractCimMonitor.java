package service;

import com.jacob.activeX.ActiveXComponent;

public abstract class AbstractCimMonitor {

    protected final String cimQuery = "winmgmts:\\\\localhost\\root\\CIMV2";

    protected ActiveXComponent activeXComponent;

}
