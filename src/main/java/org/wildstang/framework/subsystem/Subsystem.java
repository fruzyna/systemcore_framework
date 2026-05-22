package org.wildstang.framework.subsystem;

import org.wildstang.framework.opmode.OpModeEnum;

/**
 * Represents the functions required by all subsystems.
 */
public interface Subsystem {

    /**
     * Called immediately following construction, used to initialize the subsystem.
     */
    void init();

    /**
     * Called after all Subsystems are constructed and initialized, used to initialize connections to other subsystems.
     */
    void initSubsystems();

    /**
     * Update function called during AUTONOMOUS OpModes before applyChanges().
     * @param autoMode Selected OpMode.
     */
    void autoUpdate(OpModeEnum autoMode);

    /**
     * Update function called during TELEOPERATED OpModes before applyChanges().
     * @param teleMode Selected OpMode.
     */
    void teleUpdate(OpModeEnum teleMode);

    /**
     * Update function called during UTILITY OpModes before applyChanges().
     * @param utilMode Selected OpMode.
     */
    void utilUpdate(OpModeEnum utilMode);

    /**
     * Called periodically to update the subsystem state, regardless of RobotMode.
     */
    void applyChanges();
    
}
