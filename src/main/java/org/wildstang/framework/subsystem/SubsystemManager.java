package org.wildstang.framework.subsystem;

import java.util.HashMap;

import org.wildstang.framework.CoreUtils;
import org.wildstang.framework.logger.Log;
import org.wildstang.framework.opmode.OpModeEnum;

/**
 * Manages Subsystem creation and cycling on behalf of Core.
 */
public class SubsystemManager {
    
    private HashMap<SubsystemEnum, Subsystem> subsystems;

    /**
     * Constructs an empty SubsystemManager.
     * The Map of subsystems should be populated using createSubsystems().
     */
    public SubsystemManager() {
        Log.info("Creating SubsystemManager");

        subsystems = new HashMap<>();
    }

    /**
     * Constructs and initializes the specified set of Subsystems, if not already called.
     * @param definedSubsystems Subsystems defined in WsSubsystems
     */
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
                    subsystems.put(ssEnum, subsystem);
                    Log.info("Created subsystem " + ssEnum.getName());
                }
                catch (Exception e) {
                    Log.error("Failed to create subsystem " + ssEnum.getName());
                }
            }
        }

        for (Subsystem subsystem : subsystems.values()) {
            subsystem.initSubsystems();
        }
    }

    /**
     * Periodically triggers each available subsystem to update.
     * @param opMode Enum representation of the current OpMode, if null no only applyChanges() is called
     */
    public void update(OpModeEnum opMode) {
        for (Subsystem subsystem : subsystems.values()) {
            if (opMode != null) {
                switch (opMode.getRobotMode()) {
                    case AUTONOMOUS:
                        subsystem.autoUpdate(opMode);
                        break;
                    case TELEOPERATED:
                        subsystem.teleUpdate(opMode);
                        break;
                    case UTILITY:
                        subsystem.utilUpdate(opMode);
                        break;
                    default:
                        break;
                }
            }

            subsystem.applyChanges();
        }
    }

    /**
     * Gets a constructed subsystem using the subsystem definition.
     * @param definedSubsystem Enumeration defining the desired subsystem
     * @return The requested Subsystem instance
     */
    public Subsystem getSubsystem(SubsystemEnum definedSubsystem) {
        return subsystems.get(definedSubsystem);
    }
}
