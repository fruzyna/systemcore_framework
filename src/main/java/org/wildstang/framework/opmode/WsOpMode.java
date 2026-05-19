package org.wildstang.framework.opmode;

import org.wpilib.opmode.PeriodicOpMode;

/**
 * This is intended to be the primary op mode used in teleop and util; it does nothing on its own.
 * All robot operation is performed through Core, which is ticked by the robot's implementation of OpModeRobot.
 */
public class WsOpMode extends PeriodicOpMode {}
