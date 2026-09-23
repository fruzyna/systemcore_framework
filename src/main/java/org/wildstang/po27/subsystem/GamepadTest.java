package org.wildstang.po27.subsystem;

import org.wildstang.framework.input.WsGamepad;
import org.wildstang.framework.logger.Log;
import org.wildstang.framework.opmode.OpModeEnum;
import org.wildstang.framework.subsystem.Subsystem;
import org.wildstang.po27.robot.WsOpModes;

/**
 * An example subsystem which queries a few inputs from the new generic Gamepad class.
 */
public class GamepadTest extends Subsystem {

    private WsGamepad driver;
    private WsGamepad operator;

    private boolean bPressed;
    private boolean triggerPressed;
    private double rightPosition;

    private boolean bLast;
    private boolean triggerLast;
    private double rightLast;

    @Override
    protected void resetState() {
        driver = WsGamepad.getDriver();
        operator = WsGamepad.getOperator();

        bLast = false;
        triggerLast = false;
        rightLast = 0;
    }

    @Override
    protected void initSubsystems() {
    }

    @Override
    protected void autoUpdate(OpModeEnum autoMode) {
    }

    @Override
    protected void teleUpdate(OpModeEnum teleMode) {
        if (driver.isConnected()) {
            bPressed = driver.getBButton();
            triggerPressed = driver.getLeftTriggerButton();
            rightPosition = driver.getRightX();
        }
    }

    @Override
    protected void utilUpdate(OpModeEnum utilMode) {
        if (driver.isConnected()) {
            if (utilMode == WsOpModes.CHANGED_FNS) {
                if (driver.getBButton()) {
                    bPressed = true;
                }
                else {
                    bPressed = false;
                }
            }
            else if (utilMode == WsOpModes.INPUT_FNS) {
                bPressed = driver.getBButton();
                triggerPressed = Math.abs(driver.getLeftTrigger()) > 0.1;
                rightPosition = driver.getRightX();
            }
        }
    }

    @Override
    public void applyChanges() {
        if (bPressed != bLast) {
            bLast = bPressed;
            Log.info("B button changed to " + bPressed);
        }

        if (triggerPressed != triggerLast) {
            triggerLast = triggerPressed;
            Log.info("Left trigger changed to " + triggerPressed);
        }

        if (rightPosition != rightLast) {
            rightLast = rightPosition;
            Log.info("Right joystick X changed to " + rightPosition);
        }

        telemetry.log("B Button", bLast);
        telemetry.log("Left Trigger", triggerLast);
        telemetry.log("Right X", rightLast);
    }
    
}
