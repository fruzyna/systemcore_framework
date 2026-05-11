package org.wildstang.framework.subsystem;

/**
 * Represents the functions required by all subsystems.
 */
public interface Subsystem {

    /**
     * Called immediately following construction, used to initialize the subsystem.
     */
    void init();
    
    /**
     * Called immediately following init(), used to initialize inputs used by the subsystem.
     */
    void initInputs();

    /**
     * Called immediately following initInputs(), used to initialize outputs used by the subsystem.
     */
    void initOutputs();

    /**
     * Called after all Subsystems are constructed and initialized, used to initialize connections to other subsystems.
     */
    void initSubsystems();

    /**
     * Called periodically to update the subsystem state.
     */
    void update();
    
}
