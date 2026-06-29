package org.wildstang.framework.odometry.source;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.wildstang.framework.odometry.WsOdometry;
import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;
import org.wildstang.framework.odometry.VisionMeasurement;
import org.wpilib.math.geometry.Pose2d;
import org.wpilib.math.geometry.Transform3d;
import org.wpilib.math.linalg.Matrix;
import org.wpilib.math.numbers.N1;
import org.wpilib.math.numbers.N3;

public class PhotonVisionSource extends AprilTagSource {

    private PhotonCamera camera;
    private PhotonPoseEstimator estimator;

    public PhotonVisionSource(String cameraName, Transform3d offsetToCenter) {
        super(cameraName);

        camera = new PhotonCamera(cameraName);
        estimator = new PhotonPoseEstimator(WsOdometry.TAG_LAYOUT, offsetToCenter);
    }

    @Override
    public List<VisionMeasurement> getMeasurements() {
        List<VisionMeasurement> measurements = new ArrayList<>();
        List<PhotonPipelineResult> results = camera.getAllUnreadResults();
        for (PhotonPipelineResult result : results) {
            List<PhotonTrackedTarget> targets = result.getTargets();
            Pose2d[] tagTargets = new Pose2d[targets.size()];
            TargetAmbiquity[] targetAmbiquity = new TargetAmbiquity[targets.size()];
            for (int i = 0; i < targets.size(); ++i) {
                tagTargets[i] = WsOdometry.TAG_LAYOUT.getTagPose(targets.get(i).getFiducialId()).get().toPose2d();
                targetAmbiquity[i] = new TargetAmbiquity(targets.get(i).getFiducialId(), targets.get(i).getPoseAmbiguity());
            }

            long resultTimestamp = (long) (result.getTimestampSeconds() * 1e6);
            targetPublisher.set(tagTargets, resultTimestamp);

            // TODO: determine which evaluation function to use in place of MULTI_TAG_PNP_ON_COPROCESSOR
            // LOWEST_AMBIGUITY was previously used as the fallback before deprication
            Optional<EstimatedRobotPose> estimate = estimator.estimateLowestAmbiguityPose(result);
            if (estimate.isPresent()) {
                Pose2d pose = estimate.get().estimatedPose.toPose2d();
                double timestamp = estimate.get().timestampSeconds;
                Matrix<N3, N1> stddev = computeStandardDeviation(pose, targetAmbiquity);

                posePublisher.set(pose, (long) (timestamp * 1e6));

                measurements.add(new VisionMeasurement(pose, timestamp, stddev));
            }
            else {
                posePublisher.set(null, resultTimestamp);
            }
        }
        return measurements;
    }
    
}
