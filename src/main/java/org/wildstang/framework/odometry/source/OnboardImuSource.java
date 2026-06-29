package org.wildstang.framework.odometry.source;

import org.wpilib.hardware.imu.OnboardIMU;
import org.wpilib.math.geometry.Rotation2d;
import org.wpilib.math.geometry.Rotation3d;
import org.wpilib.math.geometry.Transform3d;

public class OnboardImuSource extends GyroSource {

    private OnboardIMU gyro;

    public OnboardImuSource(Transform3d offsetToCenter) {
        Rotation3d mountingAngle = offsetToCenter.getRotation();
        OnboardIMU.MountOrientation orientation = OnboardIMU.MountOrientation.FLAT;
        if (mountingAngle.getX() != 0) {
            orientation = OnboardIMU.MountOrientation.LANDSCAPE;
        }
        else if (mountingAngle.getY() != 0) {
            orientation = OnboardIMU.MountOrientation.PORTRAIT;
        }
        gyro = new OnboardIMU(orientation);
    }

    @Override
    public void zeroize() {
        gyro.resetYaw();
    }

    @Override
    public Rotation2d getAngle() {
        return gyro.getRotation2d();
    }
    
}
