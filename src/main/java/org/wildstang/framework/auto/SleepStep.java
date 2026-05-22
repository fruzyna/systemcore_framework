package org.wildstang.framework.auto;

import org.wpilib.system.Timer;

/**
 * Autonomous step which creates a timer for a given number of seconds, then waits that amount of time before finishing.
 */
public class SleepStep extends AutoStep {

    private double mDelaySeconds;
    private Timer timer;

    /**
     * Initializes the step and its timer. Stores the length of time to sleep.
     * @param pDelaySeconds Number of seconds before the step is finished
     */
    public SleepStep(double pDelaySeconds) {
        super();

        mDelaySeconds = pDelaySeconds;
        timer = new Timer();
    }

    @Override
    public void onStart() {
        timer.start();
    }

    @Override
    public void update() {
        if (timer.hasElapsed(mDelaySeconds)) {
            setFinished();
        }
    }

    @Override
    public String getName() {
        return "Sleep " + mDelaySeconds + " seconds";
    }

}
