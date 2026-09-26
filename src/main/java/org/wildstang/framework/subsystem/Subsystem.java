package org.wildstang.framework.subsystem;

import org.wildstang.framework.input.WsGamepad;
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
     * Update function called during TELEOPERATED and UTILITY OpModes before applyChanges().
     * @param opMode Selected OpMode.
     * @param driver The driver's gamepad.
     * @param operator The operator's gamepad.
     */
    protected abstract void inputUpdate(OpModeEnum opMode, WsGamepad driver, WsGamepad operator);

    /**
     * Called periodically to update the subsystem state, regardless of RobotMode.
     */
    protected abstract void applyChanges();
    
}
