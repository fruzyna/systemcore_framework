package org.wildstang.framework.input;

import org.wpilib.driverstation.Gamepad;

/**
 * Custom enumeration for wrapping WPILib Gamepad.Button face buttons to their Xbox names.
 */
public enum XboxButton {

    A(Gamepad.Button.SOUTH_FACE),
    B(Gamepad.Button.EAST_FACE),
    X(Gamepad.Button.WEST_FACE),
    Y(Gamepad.Button.NORTH_FACE);

    private Gamepad.Button mButton;

    private XboxButton(Gamepad.Button pButton) {
        mButton = pButton;
    }

    /**
     * Returns the corresponding Gamepad.Button.
     * @return WPILib gamepad button equivalent
     */
    public Gamepad.Button getGamepadButton() {
        return mButton;
    }
}
