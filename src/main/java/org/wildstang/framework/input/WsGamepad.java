package org.wildstang.framework.input;

import org.wpilib.driverstation.Gamepad;

/**
 * Adds extra features on top of those provided by WPILib's Gamepad.
 */
public class WsGamepad extends Gamepad {

    private static final int DRIVER = 0;
    private static final int OPERATOR = 1;

    private static WsGamepad driver;
    private static WsGamepad operator;

    /**
     * Creates and returns a singleton instance of the driver gamepad (0).
     * @return Gamepad at index 0
     */
    public static WsGamepad getDriver() {
        if (driver == null) {
            driver = new WsGamepad(DRIVER);
        }
        return driver;
    }

    /**
     * Creates and returns a singleton instance of the operator gamepad (1).
     * @return Gamepad at index 1
     */
    public static WsGamepad getOperator() {
        if (operator == null) {
            operator = new WsGamepad(OPERATOR);
        }
        return operator;
    }

    /**
     * Constructs a WsGamepad and passes the port index to the parent class.
     * @param port Port index of the gamepad in the driver station
     */
    public WsGamepad(int port) {
        super(port);
    }

    /**
     * Allows interacting with gamepad axes such as triggers as if they were buttons.
     * Uses an absolute threshold of 10%.
     * @param axis Axis to treat as a button
     * @return Whether the axis has exceeded the threshold
     */
    public boolean getButton(Gamepad.Axis axis) {
        return Math.abs(getAxis(axis)) > 0.1;
    }

    /**
     * Allows interacting with gamepad buttons using the XboxButton wrapper, so A, B, X, and Y can be used.
     * @param button Requested Xbox button
     * @return Whether the button is pressed
     */
    public boolean getButton(XboxButton button) {
        return getButton(button.getGamepadButton());
    }
}
