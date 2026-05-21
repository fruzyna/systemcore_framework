package org.wildstang.framework.auto;

import java.util.ArrayList;

/**
 * Autonomous step which executes multiple given steps at once.
 */
public class ParallelStepGroup extends AutoStep {

    private boolean initialized;
    private ArrayList<AutoStep> mSteps;

    public ParallelStepGroup() {
        this(new ArrayList<>());
    }

    public ParallelStepGroup(ArrayList<AutoStep> pSteps) {
        super();

        initialized = false;
        mSteps = pSteps;
    }

    /**
     * Optionally add additional AutoSteps after construction and before it starts.
     * Throws an AssertionError if the step group has already started.
     * @param step Additional AutoStep to run
     */
    public void addStep(AutoStep step) {
        if (initialized) {
            throw new AssertionError("AutoSteps cannot be added after ParallelStepGroup has started.");
        }

        mSteps.add(step);
    }

    @Override
    public void onStart() {
        initialized = true;
    }

    @Override
    public void update() {
        ArrayList<AutoStep> finished = new ArrayList<>();
        for (AutoStep step : mSteps) {
            step.update();

            if (step.isFinished()) {
                finished.add(step);
            }
        }

        mSteps.removeAll(finished);

        if (mSteps.isEmpty()) {
            setFinished();
        }
    }

    @Override
    public String getName() {
        return "ParallelStepGroup";
    }

}
