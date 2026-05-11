package org.wildstang.framework.subsystem;

/**
 * Implemented by a robot's WsSubsystems to define subsystems available to the robot.
 */
public interface SubsystemEnum {

    /**
     * Returns the name used to identify the Subsystem in logs.
     * @return Unique name
     */
    public String getName();

    /**
     * Returns the class representing the Subsystem, used to construct new instances.
     * @return Subsystem class
     */
    public Class<? extends Subsystem> getSubsystemClass();

    /**
     * Whether the Subsystem should be created.
     * @return True to enable
     */
    public boolean isEnabled();
}
