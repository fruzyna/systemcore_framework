package org.wildstang.framework.odometry.source;

import org.wpilib.math.geometry.Rotation2d;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.hardware.Pigeon2;

public class PigeonSource extends GyroSource {

    private Pigeon2 gyro;

    public PigeonSource(int canId) {
        gyro = new Pigeon2(canId, CANBus.systemcore(0));
    }

    @Override
    public void zeroize() {
        gyro.setYaw(0.0);
    }

    @Override
    public Rotation2d getAngle() {
        return gyro.getRotation2d();
    }
    
}
