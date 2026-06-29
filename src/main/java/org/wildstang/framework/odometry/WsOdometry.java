package org.wildstang.framework.odometry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.wildstang.framework.Core;
import org.wildstang.framework.drive.swerve.SwerveDrive;
import org.wildstang.framework.logger.Log;
import org.wildstang.framework.odometry.source.AprilTagSource;
import org.wildstang.framework.odometry.source.GyroSource;
import org.wildstang.framework.odometry.source.LimeLightSource;
import org.wildstang.framework.odometry.source.OnboardImuSource;
import org.wildstang.framework.odometry.source.PhotonVisionSource;
import org.wildstang.framework.odometry.source.PigeonSource;
import org.wildstang.framework.odometry.source.Source;
import org.wpilib.math.estimator.SwerveDrivePoseEstimator;
import org.wpilib.math.geometry.Pose2d;
import org.wpilib.math.geometry.Rectangle2d;
import org.wpilib.math.geometry.Rotation2d;
import org.wpilib.math.geometry.Transform2d;
import org.wpilib.math.geometry.Transform3d;
import org.wpilib.vision.apriltag.AprilTagFieldLayout;
import org.wpilib.vision.apriltag.AprilTagFields;

/**
 * Combines many odometry sources to estimate a current pose (position) of the robot.
 * Uses that position to determine which zones the robot occupies.
 */
public class WsOdometry {

    public static final AprilTagFieldLayout TAG_LAYOUT = AprilTagFieldLayout.loadField(AprilTagFields.kDefaultField);

    private List<Source> sources;
    private Map<Rectangle2d, ZoneEnum> zones;

    private Pose2d combinedPose;
    private List<ZoneEnum> currentZones;
    private SwerveDrive drive;
    private SwerveDrivePoseEstimator estimator;

    /**
     * Constructor; class is not operational until initOdometry has been called.
     */
    public WsOdometry() {
        sources = new ArrayList<>();
        zones = new HashMap<>();

        combinedPose = Pose2d.kZero;
        currentZones = new ArrayList<>();
        estimator = null;
    }

    /**
     * Initializes odometry. Creates the pose estimator, odometry sources, and zones.
     * @param definedSources Odometry sources defined in WsSources
     * @param definedZones Zones defined in WsZones
     */
    public void initOdometry(SourceEnum[] definedSources, ZoneEnum[] definedZones) {
        // get the drivebase subsystem, then create a pose estimator from it
        drive = Core.getInstance().getDrive();
        if (drive == null) {
            Log.error("Cannot initOdometry without SwerveDrive");
            return;
        }

        estimator = new SwerveDrivePoseEstimator(
            drive.getKinematics(),
            Rotation2d.kZero,
            drive.getModulePositions(),
            combinedPose
        );

        // create the corresponding Source for each definied source
        boolean foundGyro = false;
        for (SourceEnum source : definedSources) {
            String port = source.getPort();
            Transform3d offest = source.getOffset();

            switch (source.getSource()) {
                case LIMELIGHT:
                    sources.add(new LimeLightSource(port, offest));
                    break;
                case PHOTON_VISION:
                    sources.add(new PhotonVisionSource(port, offest));
                    break;
                case PIGEON:
                    try {
                        sources.add(new PigeonSource(Integer.parseInt(port)));
                        foundGyro = true;
                    }
                    catch (NumberFormatException e) {
                        Log.error("Pigeon assigned non-integer port \"" + port + "\"");
                    }
                    break;
                case ONBOARD_IMU:
                    sources.add(new OnboardImuSource(offest));
                    foundGyro = true;
            }
        }

        if (!foundGyro) {
            Log.warn("No gyro provided to WsOdometry!");
        }

        // build a map of zone rectangles to zone definitions based on the given definitions
        // rectangles are the key because there may be multiple rectangles per zone definition
        for (ZoneEnum zone : definedZones) {
            Rectangle2d bounds = zone.getBounds();
            zones.put(bounds, zone);

            // create a copy of the zone mirrored across both axes
            if (zone.isSymmetrical()) {
                zones.put(bounds.rotateBy(Rotation2d.k180deg), zone);
            }
            // create a copy of the zone mirrored across the Y axis
            else if (zone.isMirrored()) {
                zones.put(bounds.transformBy(new Transform2d(-2 * bounds.getCenter().getX(), bounds.getCenter().getY(), Rotation2d.kZero)), zone);
            }
        }
    }

    /**
     * Run each update loop by Core. Reads all odometry sources and computes a new pose estimate.
     * Then update the list of occupied zones.
     */
    public void updatePose() {
        if (estimator != null) {
            Rotation2d rotation = Rotation2d.kZero;
            for (int i = 0; i < sources.size(); ++i) {
                Source source = sources.get(i);

                // only the first value read from a gyro this update loop is used
                // gyros are prioritized in order of definition (highest priority first)
                if (source instanceof GyroSource && rotation != Rotation2d.kZero) {
                    rotation = ((GyroSource) source).getAngle();
                }

                // adds each April Tag measurement from each camera to the pose estimator
                if (source instanceof AprilTagSource) {
                    List<VisionMeasurement> measurements = ((AprilTagSource) source).getMeasurements();
                    for (VisionMeasurement measurement : measurements) {
                        estimator.addVisionMeasurement(measurement.getPose(), measurement.getTimestamp(), measurement.getStddev());
                    }
                }
            }

            estimator.update(rotation, drive.getModulePositions());
            combinedPose = estimator.getEstimatedPosition();

            checkZones();
        }
    }

    /**
     * Updates the list of currently occupied zones based on the current pose.
     */
    public void checkZones() {
        currentZones.clear();
        for (Map.Entry<Rectangle2d, ZoneEnum> zone : zones.entrySet()) {
            if (isInZone(zone.getKey())) {
                currentZones.add(zone.getValue());
            }
        }
    }

    /**
     * Determines if the robot is inside a specified zone.
     * @param zone Rectangle describing the absolute position of the field
     * @return True is the robot is inside the zone
     */
    public boolean isInZone(Rectangle2d zone) {
        return zone.intersects(combinedPose.getTranslation());
    }

    /**
     * Provides the list of curretnly occupied zones.
     * @return Enumerations of the occupied zones
     */
    public List<ZoneEnum> getZones() {
        return currentZones;
    }

}
