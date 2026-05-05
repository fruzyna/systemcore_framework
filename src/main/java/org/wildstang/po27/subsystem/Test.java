package org.wildstang.po27.subsystem;

import org.wildstang.framework.logger.Log;
import org.wildstang.framework.subsystem.Subsystem;
import org.wpilib.system.RobotController;

public class Test implements Subsystem {

    @Override
    public void init() {
        Log.info("Test.init");
    }

    @Override
    public void initInputs() {
        Log.info("Test.initInputs");
    }

    @Override
    public void initOutputs() {
        Log.info("Test.initOutputs");
    }

    @Override
    public void initSubsystems() {
        Log.info("Test.initSubsystems");
    }

    @Override
    public void update() {
        Log.info("Test.update @ " + RobotController.getMonotonicTime());
    }
    
}
