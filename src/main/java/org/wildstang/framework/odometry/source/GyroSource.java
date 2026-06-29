package org.wildstang.framework.odometry.source;

import org.wpilib.math.geometry.Rotation2d;

public abstract class GyroSource extends Source {

    public abstract void zeroize();

    public abstract Rotation2d getAngle();
}
