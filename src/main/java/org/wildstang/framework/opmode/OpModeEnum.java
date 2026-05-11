package org.wildstang.framework.opmode;

import org.wpilib.hardware.hal.RobotMode;
import org.wpilib.opmode.OpMode;

/**
 * Implemented by a robot's WsOpModes to define the OpModes available to the robot.
 */
public interface OpModeEnum {

    /**
     * Returns the name used to identify the OpMode in logs.
     * @return Unique name
     */
    public String getName();

    /**
     * Returns the class representing the OpMode, used to construct new instances.
     * @return OpMode class
     */
    public Class<? extends OpMode> getOpModeClass();

    /**
     * Returns the RobotMode which the OpMode is available (AUTONOMOUS, TELEOPERATED, UTILITY)
     * @return Associated RobotMode
     */
    public RobotMode getRobotMode();

    /**
     * Whether the OpMode should be available when connected to an FMS.
     * @return True to show when connected
     */
    public boolean inCompetition();

    /**
     * Whether the OpMode should be available when running in simulation.
     * @return True to show when in sim
     */
    public boolean inSimulation();

    /**
     * Whether the OpMode should be available at all.
     * @return True to enable
     */
    public boolean isEnabled();
}
