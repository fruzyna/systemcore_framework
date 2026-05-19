package org.wildstang.po27.subsystem;

import org.wildstang.framework.input.WsGamepad;
import org.wildstang.framework.input.XboxButton;
import org.wildstang.framework.logger.Log;
import org.wildstang.framework.opmode.OpModeEnum;
import org.wildstang.framework.subsystem.Subsystem;
import org.wildstang.po27.robot.WsOpModes;
import org.wpilib.driverstation.Gamepad;

/**
 * An example subsystem which queries a few inputs from the new generic Gamepad class.
 */
public class GamepadTest implements Subsystem {

    private boolean bPressed;
    private boolean triggerPressed;
    private double rightPosition;

    private boolean bLast;
    private boolean triggerLast;
    private double rightLast;

    @Override
    public void init() {
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
        if (WsGamepad.getDriver().isConnected()) {
            bPressed = WsGamepad.getDriver().getButton(XboxButton.B);
            // These axes appear to be backwards (others are wrong too)
            triggerPressed = WsGamepad.getDriver().getButton(Gamepad.Axis.RIGHT_X);
            rightPosition = WsGamepad.getDriver().getAxis(Gamepad.Axis.LEFT_TRIGGER);
        }
    }

    @Override
    public void utilUpdate(OpModeEnum utilMode) {
        if (WsGamepad.getDriver().isConnected()) {
            if (utilMode == WsOpModes.CHANGED_FNS) {
                if (WsGamepad.getDriver().getEastFaceButtonPressed()) {
                    bPressed = true;
                }
                else if (WsGamepad.getDriver().getEastFaceButtonReleased()) {
                    bPressed = false;
                }
            }
            else if (utilMode == WsOpModes.INPUT_FNS) {
                bPressed = WsGamepad.getDriver().getEastFaceButton();
                // These axes appear to be backwards (others are wrong too)
                triggerPressed = Math.abs(WsGamepad.getDriver().getRightX()) > 0.1;
                rightPosition = WsGamepad.getDriver().getLeftTriggerAxis();
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
