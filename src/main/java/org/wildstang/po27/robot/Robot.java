package org.wildstang.po27.robot;

import org.wildstang.framework.Core;
import org.wildstang.framework.logger.Log;
import org.wildstang.framework.opmode.OpModeEnum;
import org.wpilib.framework.OpModeRobot;

/**
 * The base class of the robot, create in Main.
 * This is effectively framework code, most functions interface directly to Core.
 */
public class Robot extends OpModeRobot {

    public Robot() {
        super(Core.getLoopRate());

        Core.getInstance().initRobot(WsSubsystems.values(), WsOpModes.values());

        populateOpModes();
    }

    public void populateOpModes() {
        clearOpModes();
        
        for (OpModeEnum opMode : Core.getInstance().updateAvailableOpModes()) {
            addOpMode(opMode.getOpModeClass(), opMode.getRobotMode(), opMode.getName());
            Log.info("Added " + opMode.getRobotMode().name() + " op mode " + opMode.getName());
        }

        publishOpModes();
    }

    @Override
    public void disabledExit() {
        Core.getInstance().onEnabled();
    }

    @Override
    public void disabledInit() {
        Core.getInstance().onDisabled();
    }

    @Override
    public void robotPeriodic() {
        Core.getInstance().update();
    }

    @Override
    public void driverStationConnected() {
        Log.info("Driver station connected");

        // refresh op modes to check if FMS is attached again
        populateOpModes();
    }

    @Override
    public void simulationInit() {
        Log.info("Simulation connected");
    }
}
