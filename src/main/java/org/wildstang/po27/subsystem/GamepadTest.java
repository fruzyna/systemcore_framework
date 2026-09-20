package org.wildstang.po27.subsystem;

import org.wildstang.framework.input.WsGamepad;
import org.wildstang.framework.logger.Log;
import org.wildstang.framework.opmode.OpModeEnum;
import org.wildstang.framework.subsystem.Subsystem;
import org.wildstang.po27.robot.WsOpModes;
import org.wpilib.driverstation.Gamepad;

/**
 * An example subsystem which queries a few inputs from the new generic Gamepad class.
 */
public class GamepadTest implements Subsystem {

    private WsGamepad driver;
    private WsGamepad operator;

    private boolean bPressed;
    private boolean triggerPressed;
    private double rightPosition;

    private boolean bLast;
    private boolean triggerLast;
    private double rightLast;

    @Override
    public void resetState() {
        driver = WsGamepad.getDriver();
        operator = WsGamepad.getOperator();

        bLast = false;
        triggerLast = false;
        rightLast = 0;
    }

    @Override
    public void initSubsystems() {
    }

    @Override
    public void autoUpdate(OpModeEnum autoMode) {
    }

    @Override
    public void teleUpdate(OpModeEnum teleMode) {
        if (driver.isConnected()) {
            bPressed = driver.getBButton();
            // These axes appear to be backwards (others are wrong too)
            //triggerPressed = WsGamepad.getDriver().getButton(Gamepad.Axis.RIGHT_X);
            //rightPosition = WsGamepad.getDriver().getAxis(Gamepad.Axis.LEFT_TRIGGER);
            triggerPressed = driver.getRightTriggerButton();
            rightPosition = driver.getRightX();
        }
    }

    @Override
    public void utilUpdate(OpModeEnum utilMode) {
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
                // These axes appear to be backwards (others are wrong too)
                //triggerPressed = Math.abs(WsGamepad.getDriver().getRightX()) > 0.1;
                //rightPosition = WsGamepad.getDriver().getLeftTriggerAxis();
                triggerPressed = Math.abs(driver.getLeftTriggerAxis()) > 0.1;
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
    }
    
}
