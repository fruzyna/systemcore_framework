package org.wildstang.po27.robot;

import org.wildstang.framework.subsystem.SubsystemEnum;
import org.wildstang.po27.subsystem.Test;

public enum WsSubsystems implements SubsystemEnum {

    TEST("Test", Test.class);

    private String name;
    private Class<?> subsystemClass;
    private boolean enabled;

    WsSubsystems(String name, Class<?> subsystemClass, boolean enabled) {
        this.name = name;
        this.subsystemClass = subsystemClass;
        this.enabled = enabled;
    }

    WsSubsystems(String name, Class<?> subsystemClass) {
        this(name, subsystemClass, true);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Class<?> getSubsystemClass() {
        return subsystemClass;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
    
}
