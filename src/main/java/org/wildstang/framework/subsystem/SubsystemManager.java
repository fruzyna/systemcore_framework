package org.wildstang.framework.subsystem;

import java.util.ArrayList;

import org.wildstang.framework.CoreUtils;
import org.wildstang.framework.logger.Log;

public class SubsystemManager {
    
    private ArrayList<Subsystem> subsystems;

    public SubsystemManager() {
        subsystems = new ArrayList<>();
    }

    public void createSubsystems(SubsystemEnum[] definedSubsystems) {
        if (!subsystems.isEmpty()) {
            Log.warn("Subsystems already exist, skipping createSubsystems");
            return;
        }

        for (SubsystemEnum ssEnum : definedSubsystems) {
            if (ssEnum.isEnabled()) {
                try {
                    Subsystem subsystem = (Subsystem) CoreUtils.createObject(ssEnum.getSubsystemClass());
                    subsystem.init();
                    subsystem.initInputs();
                    subsystem.initOutputs();
                    subsystems.add(subsystem);
                    Log.info("Created subsystem " + ssEnum.getName());
                }
                catch (Exception e) {
                    Log.error("Failed to create subsystem " + ssEnum.getName());
                }
            }
        }

        for (Subsystem subsystem : subsystems) {
            subsystem.initSubsystems();
        }
    }

    public void update() {
        for (Subsystem subsystem : subsystems) {
            subsystem.update();
        }
    }
}
