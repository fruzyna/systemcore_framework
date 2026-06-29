package org.wildstang.framework.odometry.source;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.wildstang.framework.odometry.WsOdometry;
import org.wildstang.framework.odometry.VisionMeasurement;
import org.wpilib.math.geometry.Pose2d;
import org.wpilib.math.geometry.Pose3d;
import org.wpilib.math.linalg.Matrix;
import org.wpilib.math.linalg.VecBuilder;
import org.wpilib.math.numbers.N1;
import org.wpilib.math.numbers.N3;
import org.wpilib.networktables.NetworkTableInstance;
import org.wpilib.networktables.StructArrayPublisher;
import org.wpilib.networktables.StructPublisher;

public abstract class AprilTagSource extends Source {

    protected class TargetAmbiquity {
        public int tagId;
        public double ambiguity;

        public TargetAmbiquity(int pTagId, double pAmbiquity) {
            tagId = pTagId;
            ambiguity = pAmbiquity;
        }
    }

    // TODO: these will probably vary per model / camera
    protected static final List<Integer> IGNORED_TAGS = Arrays.asList();
    protected static final double AMBIGUITY_THRESHOLD = 0.2;
    protected static final Matrix<N3, N1> SINGLE_TAG_STDDEV = VecBuilder.fill(1.3, 1.3, Double.MAX_VALUE);  // TODO: tune these values
    protected static final Matrix<N3, N1> MAX_STDDEV = VecBuilder.fill(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);

    protected StructPublisher<Pose2d> posePublisher;
    protected StructArrayPublisher<Pose2d> targetPublisher;

    public AprilTagSource(String cameraName) {
        posePublisher = NetworkTableInstance.getDefault().getStructTopic(cameraName + " Pose Estimator", Pose2d.struct).publish();
        targetPublisher = NetworkTableInstance.getDefault().getStructArrayTopic(cameraName + " Vision Targets", Pose2d.struct).publish();
    }

    protected Matrix<N3, N1> computeStandardDeviation(Pose2d pose, TargetAmbiquity[] targets) {
        int tagCount = 0;
        double totalDistance = 0;
        for (TargetAmbiquity target : targets) {
            if (IGNORED_TAGS.contains(target.tagId) || target.ambiguity > AMBIGUITY_THRESHOLD) {
                return MAX_STDDEV;
            }

            Optional<Pose3d> tagPose = WsOdometry.TAG_LAYOUT.getTagPose(target.tagId);
            if (tagPose.isEmpty()) {
                continue;
            }

            ++tagCount;
            totalDistance += tagPose.get().toPose2d().getTranslation().getDistance(pose.getTranslation());
        }

        if (tagCount > 0) {
            double averageDistance = totalDistance / tagCount;

            if (tagCount > 1 || averageDistance < 4) {
                return SINGLE_TAG_STDDEV.div(tagCount).times(1 + (Math.pow(averageDistance, 2) / 30));
            }
            else {
                return MAX_STDDEV;
            }
        }
        else {
            return SINGLE_TAG_STDDEV;
        }
    }

    public abstract List<VisionMeasurement> getMeasurements();

}
