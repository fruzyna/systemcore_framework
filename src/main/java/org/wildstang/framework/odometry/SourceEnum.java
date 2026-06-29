package org.wildstang.framework.odometry;

import org.wpilib.math.geometry.Transform3d;

public interface SourceEnum {
    
    public enum OdometrySource {
        LIMELIGHT,
        PHOTON_VISION,
        PIGEON,
        ONBOARD_IMU
    }

    public OdometrySource getSource();
    
    public String getPort();

    public Transform3d getOffset();

    public boolean isEnabled();
}
