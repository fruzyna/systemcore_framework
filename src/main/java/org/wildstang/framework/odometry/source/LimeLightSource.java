package org.wildstang.framework.odometry.source;

import java.util.ArrayList;
import java.util.List;

import org.wildstang.framework.odometry.WsOdometry;
import org.wildstang.framework.odometry.VisionMeasurement;
import org.wpilib.math.geometry.Pose2d;
import org.wpilib.math.geometry.Rotation3d;
import org.wpilib.math.geometry.Transform3d;
import org.wpilib.math.linalg.Matrix;
import org.wpilib.math.numbers.N1;
import org.wpilib.math.numbers.N3;

import io.limelightvision.LimelightHelpers;
import io.limelightvision.LimelightHelpers.PoseEstimate;
import io.limelightvision.LimelightHelpers.RawFiducial;

public class LimeLightSource extends AprilTagSource {

    private String mCameraName;

    public LimeLightSource(String pCameraName, Transform3d offsetToCenter) {
        super(pCameraName);

        if (pCameraName == null || pCameraName.isEmpty()) {
            mCameraName = "limelight";
        }
        else {
            mCameraName = pCameraName;
        }

        Rotation3d rotation = offsetToCenter.getRotation();
        LimelightHelpers.setCameraPose_RobotSpace(mCameraName, offsetToCenter.getX(), offsetToCenter.getY(), offsetToCenter.getZ(), rotation.getX(), rotation.getY(), rotation.getZ());
    }

    @Override
    public List<VisionMeasurement> getMeasurements() {
        List<VisionMeasurement> measurements = new ArrayList<>();

        PoseEstimate estimate = LimelightHelpers.getBotPoseEstimate(mCameraName, "TODO", false);

        RawFiducial[] targets = estimate.rawFiducials;
        Pose2d[] tagTargets = new Pose2d[targets.length];
        TargetAmbiquity[] targetAmbiquity = new TargetAmbiquity[targets.length];
        for (int i = 0; i < targets.length; ++i) {
            tagTargets[i] = WsOdometry.TAG_LAYOUT.getTagPose(targets[i].id).get().toPose2d();
            targetAmbiquity[i] = new TargetAmbiquity(targets[i].id, targets[i].ambiguity);
        }

        long resultTimestamp = (long) (estimate.timestampSeconds * 1e6);
        targetPublisher.set(tagTargets, resultTimestamp);

        if (estimate.timestampSeconds > 0) {
            Pose2d pose = estimate.pose;
            double timestamp = estimate.timestampSeconds;
            Matrix<N3, N1> stddev = computeStandardDeviation(pose, targetAmbiquity);

            posePublisher.set(pose, (long) (timestamp * 1e6));

            measurements.add(new VisionMeasurement(pose, timestamp, stddev));
        }
        else {
            posePublisher.set(null, resultTimestamp);
        }
        return measurements;
    }
    
}
