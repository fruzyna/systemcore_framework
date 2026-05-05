package org.wildstang.framework;

import org.wildstang.framework.subsystem.SubsystemManager;
import org.wildstang.framework.subsystem.SubsystemEnum;

public class Core {
    
    private static Core instance;

    public static Core getInstance() {
        if (instance == null) {
            instance = new Core();
        }
        return instance;
    }
    
    private SubsystemManager subsystemManager;

    public Core() {
        subsystemManager = new SubsystemManager();
    }

    public void initRobot(SubsystemEnum[] subsystems) {
        subsystemManager.createSubsystems(subsystems);
    }

    public void update() {
        subsystemManager.update();
    }
}
