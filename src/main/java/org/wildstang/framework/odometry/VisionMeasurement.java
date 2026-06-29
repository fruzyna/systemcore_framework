package org.wildstang.framework.odometry;

import org.wpilib.math.geometry.Pose2d;
import org.wpilib.math.linalg.Matrix;
import org.wpilib.math.numbers.N1;
import org.wpilib.math.numbers.N3;

public class VisionMeasurement {

    private Pose2d mPose;
    private double mTimestamp;
    private Matrix<N3, N1> mStddev;

    public VisionMeasurement(Pose2d pPose, double pTimestamp, Matrix<N3, N1> pStddev) {
        mPose = pPose;
        mTimestamp = pTimestamp;
        mStddev = pStddev;
    }

    public Pose2d getPose() {
        return mPose;
    }

    public double getTimestamp() {
        return mTimestamp;
    }

    public Matrix<N3, N1> getStddev() {
        return mStddev;
    }
}
