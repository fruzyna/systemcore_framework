package org.wildstang.po27.subsystem;

import org.wildstang.framework.logger.Log;
import org.wildstang.framework.subsystem.Subsystem;
import org.wpilib.system.RobotController;

/**
 * An example subsystem which measures the timing of the periodic update loop.
 */
public class LoopTimer implements Subsystem {

    private int loops;
    private long lastCenturyTime;

    @Override
    public void init() {
        Log.info("LoopTimer.init");

        loops = 0;
        lastCenturyTime = -1;
    }

    @Override
    public void initInputs() {
        Log.info("LoopTimer.initInputs");
    }

    @Override
    public void initOutputs() {
        Log.info("LoopTimer.initOutputs");
    }

    @Override
    public void initSubsystems() {
        Log.info("LoopTimer.initSubsystems");
    }

    @Override
    public void update() {
        long loopStart = RobotController.getMonotonicTime();

        if (loops == 0) {
            lastCenturyTime = loopStart;
        }
        else if (loops % 100 == 0) {
            double deltaSecs = (double) (loopStart - lastCenturyTime) / 1000000;
            Log.info("Last 100 loops: " + deltaSecs + " s (" + (100 / deltaSecs) + " Hz)");
            lastCenturyTime = loopStart;
        }

        ++loops;
    }
    
}
