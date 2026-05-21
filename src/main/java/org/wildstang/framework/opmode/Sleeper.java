package org.wildstang.framework.opmode;

import org.wildstang.framework.auto.SleepStep;

/**
 * A demonstration AutoOpMode which sleeps for 10 seconds before finishing.
 */
public class Sleeper extends AutoOpMode {

    @Override
    protected void initProgram() {}

    @Override
    protected void defineSteps() {
        addStep(new SleepStep(10));
    }

}
