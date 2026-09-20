package org.wildstang.framework.input;

import org.wpilib.driverstation.Gamepad;

/**
 * Adds extra features on top of those provided by WPILib's Gamepad.
 */
public class WsGamepad extends Gamepad {

    private static final double TRIGGER_THRESHOLD = 0.1;

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
     * Helper function that returns the state of the left face button or X on XBOX.
     * @return The state of the button
     */
    public boolean getXButton() {
        return getFaceLeftButton();
    }

    /**
     * Helper function that returns the state of the up face button or Y on XBOX.
     * @return The state of the button
     */
    public boolean getYButton() {
        return getFaceUpButton();
    }

    /**
     * Helper function that returns the state of the right face button or B on XBOX.
     * @return The state of the button
     */
    public boolean getBButton() {
        return getFaceRightButton();
    }

    /**
     * Helper function that returns the state of the down face button or A on XBOX.
     * @return The state of the button
     */
    public boolean getAButton() {
        return getFaceDownButton();
    }

    /**
     * Helper function that treats a given axis as a button, returning a boolean state.
     * @param axis Axis to treat as a button
     * @param threshold Threshold at which the virtual button is activated
     * @return The virtual state of the button
     */
    public boolean getAxisAsButton(Gamepad.Axis axis, double threshold) {
        if (threshold == 0) {
            threshold = TRIGGER_THRESHOLD;
        }
        return Math.abs(getAxis(axis)) > threshold;
    }

    /**
     * Helper function that treats the left trigger as a button, returning a boolean state.
     * @param threshold Threshold at which the virtual button is activated
     * @return The virtual state of the button
     */
    public boolean getLeftTriggerButton(double threshold) {
        return getAxisAsButton(Gamepad.Axis.LEFT_TRIGGER, threshold);
    }

    /**
     * Helper function that treats the right trigger as a button, returning a boolean state.
     * @param threshold Threshold at which the virtual button is activated
     * @return The virtual state of the button
     */
    public boolean getRightTriggerButton(double threshold) {
        return getAxisAsButton(Gamepad.Axis.RIGHT_TRIGGER, threshold);
    }

    /**
     * Helper function that treats the left trigger as a button, returning a boolean state.
     * @return The virtual state of the button
     */
    public boolean getLeftTriggerButton() {
        return getLeftTriggerButton(TRIGGER_THRESHOLD);
    }

    /**
     * Helper function that treats the right trigger as a button, returning a boolean state.
     * @return The virtual state of the button
     */
    public boolean getRightTriggerButton() {
        return getRightTriggerButton(TRIGGER_THRESHOLD);
    }
}
