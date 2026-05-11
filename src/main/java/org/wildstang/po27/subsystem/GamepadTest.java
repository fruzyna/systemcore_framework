package org.wildstang.po27.subsystem;

import org.wildstang.framework.logger.Log;
import org.wildstang.framework.subsystem.Subsystem;
import org.wpilib.driverstation.Gamepad;

/**
 * An example subsystem which queries a few inputs from the new generic Gamepad class.
 */
public class GamepadTest implements Subsystem {

    private Gamepad gamepad;

    private boolean state;
    private int position;

    @Override
    public void init() {
        state = false;
        position = 0;
    }

    @Override
    public void initInputs() {
        gamepad = new Gamepad(0);
    }

    @Override
    public void initOutputs() {
    }

    @Override
    public void initSubsystems() {
    }

    @Override
    public void update() {
        if (gamepad.isConnected()) {
            boolean pressed = gamepad.getButton(Gamepad.Button.EAST_FACE);
            if (pressed != state) {
                state = pressed;
                Log.info("Button changed to " + state);
            }

            int axis = (int) gamepad.getAxis(Gamepad.Axis.LEFT_X);
            if (axis != position) {
                position = axis;
                Log.info("Joystick changed to " + axis);
            }
        }
    }
    
}
