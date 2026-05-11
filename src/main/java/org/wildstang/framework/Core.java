package org.wildstang.framework;

import org.wildstang.framework.subsystem.SubsystemManager;
import org.wildstang.framework.logger.Log;
import org.wildstang.framework.subsystem.SubsystemEnum;

/**
 * Where the magic happens...
 * Centralizes the various calls coming from an implementation of OpModeRobot, to move as much operation into the framework as possible.
 * This class is a singleton meaning there is only one instance that should be created and that instance is accessed using getInstance().
 */
public class Core {

    private static final int LOOP_RATE = 50;

    private static Core instance;

    /**
     * Gets the static instance of the class, or constructs it if it doesn't already exist.
     * @return The only instance of Core
     */
    public static Core getInstance() {
        if (instance == null) {
            instance = new Core();
        }
        return instance;
    }

    /**
     * Returns the configured loop rate as an interval in seconds.
     * @return The interval of the periodic update
     */
    public static double getLoopRate() {
        return 1.0 / LOOP_RATE;
    }

    private SubsystemManager subsystemManager;

    public Core() {
        Log.info("Creating Core");

        subsystemManager = new SubsystemManager();
    }

    /**
     * Called immediately upon construction of the OpModeRobot.
     * Passes robot-specific definition to the appropriate managers.
     * @param subsystems Definition of subsystems from WsSubsystems
     */
    public void initRobot(SubsystemEnum[] subsystems) {
        subsystemManager.createSubsystems(subsystems);
    }

    /**
     * Called on disabledExit() to signal that the robot has been enabled.
     */
    public void onEnabled() {
        Log.danger("Robot enabled");
    }

    /**
     * Called on disabledInit() to signal that the robot has been disabled.
     */
    public void onDisabled() {
        Log.info("Robot disabled");
    }

    /**
     * Called on robotPeriodic() to trigger all managers and their children to update.
     */
    public void update() {
        subsystemManager.update();
    }

    /**
     * Returns the SubsystemManager, primarily used to request access between Subsystems.
     * @return Instance of the SubsystemManager
     */
    public SubsystemManager getSubsystemManager() {
        return subsystemManager;
    }
}
