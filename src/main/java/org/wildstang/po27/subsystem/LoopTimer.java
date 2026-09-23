package org.wildstang.po27.subsystem;

import org.wildstang.framework.logger.Log;
import org.wildstang.framework.opmode.OpModeEnum;
import org.wildstang.framework.subsystem.Subsystem;
import org.wpilib.system.RobotController;

/**
 * An example subsystem which measures the timing of the periodic update loop.
 */
public class LoopTimer extends Subsystem {

    private int loops;
    private long lastCenturyTime;

    @Override
    protected void resetState() {
        Log.info("LoopTimer.init");

        loops = 0;
        lastCenturyTime = -1;
    }

    @Override
    protected void initSubsystems() {
        Log.info("LoopTimer.initSubsystems");
    }

    @Override
    protected void autoUpdate(OpModeEnum autoMode) {
    }

    @Override
    protected void teleUpdate(OpModeEnum teleMode) {
    }

    @Override
    protected void utilUpdate(OpModeEnum utilMode) {
    }

    @Override
    protected void applyChanges() {
        long loopStart = RobotController.getMonotonicTime();

        if (loops == 0) {
            lastCenturyTime = loopStart;
        }
        else {
            if (loops % 100 == 0) {
                double deltaSecs = (double) (loopStart - lastCenturyTime) / 1000000;
                Log.info("Last 100 loops: " + deltaSecs + " s (" + (100 / deltaSecs) + " Hz)");
                telemetry.log("Loop Time", deltaSecs / 100);
                telemetry.log("Loop Rate", 100 / deltaSecs);
                lastCenturyTime = loopStart;
            }
        }

        ++loops;
    }
    
}
