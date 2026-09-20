package org.wildstang.framework.subsystem;

import org.wildstang.framework.logger.WsTelemetry;
import org.wildstang.framework.opmode.OpModeEnum;

/**
 * Represents the functions required by all subsystems.
 */
public abstract class Subsystem extends WsTelemetry {

    /**
     * Called immediately following construction, used to initialize the subsystem.
     */
    protected abstract void resetState();

    /**
     * Called after all Subsystems are constructed and initialized, used to initialize connections to other subsystems.
     */
    protected abstract void initSubsystems();

    /**
     * Update function called during AUTONOMOUS OpModes before applyChanges().
     * @param autoMode Selected OpMode.
     */
    protected abstract void autoUpdate(OpModeEnum autoMode);

    /**
     * Update function called during TELEOPERATED OpModes before applyChanges().
     * @param teleMode Selected OpMode.
     */
    protected abstract void teleUpdate(OpModeEnum teleMode);

    /**
     * Update function called during UTILITY OpModes before applyChanges().
     * @param utilMode Selected OpMode.
     */
    protected abstract void utilUpdate(OpModeEnum utilMode);

    /**
     * Called periodically to update the subsystem state, regardless of RobotMode.
     */
    protected abstract void applyChanges();
    
}
