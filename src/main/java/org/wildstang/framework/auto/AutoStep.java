package org.wildstang.framework.auto;

/**
 * Owned by AutoOpModes, AutoSteps define a single action that can be performed by the robot during autonomous.
 * Steps are intrinsically serial, meaning they run one at a time.
 */
public abstract class AutoStep {
    
    private boolean finished;

    public AutoStep() {
        finished = false;
    }

    /**
     * Called immediately after the previous step ends.
     * This should perform any initialization that must be done while the robot is enabled.
     */
    public abstract void onStart();

    /**
     * Called periodically while the step is selected.
     * This should perform all actions that are comprised by the step.
     */
    public abstract void update();

    /**
     * Exclusively used for logging purposes, defines a name of the step.
     * @return A unique name incorporating any parameters to the function.
     */
    public abstract String getName();

    /**
     * Used to determine if the step is complete and the next step can be started.
     * @return Whether the step has determined that its task is complete or not.
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * Must be implemented in update() to mark the step as complete.
     */
    public void setFinished() {
        finished = true;
    }
}
