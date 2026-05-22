package org.wildstang.po27.opmode;

import org.wildstang.framework.auto.SleepStep;
import org.wildstang.framework.opmode.AutoOpMode;
import org.wildstang.po27.auto.LogStep;

/**
 * Sample auto program which counts out seconds from 1 to 20.
 */
public class Counter extends AutoOpMode {

    @Override
    protected void initProgram() {
    }

    @Override
    protected void defineSteps() {
        for (int i = 1; i <= 20; ++i) {
            addStep(new SleepStep(1));
            addStep(new LogStep(i + " seconds"));
        }
    }
    
}
