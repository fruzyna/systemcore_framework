package org.wildstang.framework.hardware;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;

/**
 * This is a class containing helper functions for TalonFX control.
 * This class cannot be implemented on its own.
 */
public abstract class WsTalon {

    /**
     * Configure a given motor to follow a second motor.
     * 
     * @param followerMotor Motor which should do the following.
     * @param leadMotor Motor which will be sent control commands.
     * @param invert True if the follower should run in the opposite direction of its leader.
     */
    public static void follow(TalonFX followerMotor, TalonFX leadMotor, boolean invert) {
        MotorAlignmentValue aligned = invert ? MotorAlignmentValue.Opposed : MotorAlignmentValue.Aligned;
        followerMotor.setControl(new Follower(leadMotor.getDeviceID(), aligned));
    }

}
