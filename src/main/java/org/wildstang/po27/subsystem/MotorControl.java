package org.wildstang.po27.subsystem;

import org.wildstang.framework.input.WsGamepad;
import org.wildstang.framework.opmode.OpModeEnum;
import org.wildstang.framework.subsystem.Subsystem;
import org.wpilib.hardware.bus.CANPort;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.hardware.TalonFX;

/**
 * An example subsystem which controls a single motor with a trigger.
 * The B button is the "boost" button and activates full power.
 * The Y button reverses the motor.
 */
public class MotorControl extends Subsystem {

    private TalonFX motor;
    private WsGamepad driver;

    private double driverInput;
    private boolean slowMode;
    private boolean reverse;

    public MotorControl() {
        motor = new TalonFX(11, new CANBus(CANPort.CAN_S0));
        driver = WsGamepad.getDriver();
    }

    @Override
    protected void resetState() {
        driverInput = 0;
        slowMode = true;
        reverse = false;
    }

    @Override
    protected void initSubsystems() {}

    @Override
    protected void autoUpdate(OpModeEnum autoMode) {}

    @Override
    protected void teleUpdate(OpModeEnum teleMode) {
        driverInput = driver.getRightTrigger();
        slowMode = !driver.getBButton();
        reverse = driver.getYButton();
    }

    @Override
    protected void utilUpdate(OpModeEnum utilMode) {}

    @Override
    protected void applyChanges() {
        double outputPower = driverInput;
        if (slowMode) {
            outputPower /= 2;
        }
        if (reverse) {
            outputPower *= -1;
        }
        motor.setThrottle(outputPower);

        telemetry.log("Output Power", outputPower);
    }
    
}
