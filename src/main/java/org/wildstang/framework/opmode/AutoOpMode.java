package org.wildstang.framework.opmode;

import java.util.ArrayList;

import org.wildstang.framework.auto.AutoStep;
import org.wildstang.framework.logger.Log;
import org.wpilib.driverstation.RobotState;
import org.wpilib.opmode.PeriodicOpMode;

/**
 * Parent OpMode to all autonomous modes. Serves the role previously provided by AutoProgram.
 */
public abstract class AutoOpMode extends PeriodicOpMode {

    private boolean initialized;
    private int currentStep;
    private ArrayList<AutoStep> autoSteps;

    /**
     * All AutoOpModes are constructed with an empty list of steps which must be populated using defineSteps().
     */
    public AutoOpMode() {
        initialized = false;
        currentStep = -1;
        autoSteps = new ArrayList<>();
    }

    @Override
    public void disabledPeriodic() {
        initialize();
    }

    @Override
    public void start() {
        initialize();
        step();
    }

    @Override
    public void periodic() {
        // NOTE: this supposedly only runs when enabled, but still runs when disabled
        if (currentStep >= 0 && currentStep < autoSteps.size()) {
            AutoStep step = autoSteps.get(currentStep);
            step.update();

            if (step.isFinished()) {
                logInfo(step.getName() + " complete");
                step();
            }
        }
    }

    @Override
    public void end() {
        if (currentStep != autoSteps.size()) {
            currentStep = autoSteps.size();
            logInfo("Exited early");
        }
    }

    /**
     * Calls the AutoOpMode's initProgram and defineSteps functions, then marks the mode as initialized.
     * Called during disabledPeriodic and on enabled for good measure.
     */
    private void initialize() {
        if (!initialized) {
            initProgram();
            defineSteps();
            initialized = true;
            logInfo("Initialized");
        }
    }

    /**
     * Should be called from defineSteps to add new AutoSteps in order.
     * @param step Newly constructed AutoStep.
     */
    public void addStep(AutoStep step) {
        autoSteps.add(step);
    }

    /**
     * Advances the AutoStep and calls the step's onStart function.
     */
    private void step() {
        if (++currentStep < autoSteps.size()) {
            AutoStep step = autoSteps.get(currentStep);
            step.onStart();
            logInfo(step.getName() + " started");
        }
        else {
            logInfo("AutoOpMode complete");
        }
    }

    /**
     * Makes a custom Log.info call prepending the AutoOpMode name.
     * @param message Message to log
     */
    private void logInfo(String message) {
        // this assumes that the current OpMode is the child AutoOpMode, which should always be the case
        Log.info("[" + RobotState.getOpMode() + "] " + message);
    }

    /**
     * Used to initialize the AutoOpMode before the robot has been enabled.
     * Called on the first disabled loop after construction.
     */
    protected abstract void initProgram();

    /**
     * Used to define AutoSteps in order using the addStep function.
     */
    protected abstract void defineSteps();
}
