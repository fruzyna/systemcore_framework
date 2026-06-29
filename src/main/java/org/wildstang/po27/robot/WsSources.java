package org.wildstang.po27.robot;

import org.wildstang.framework.odometry.SourceEnum;
import org.wpilib.math.geometry.Transform3d;

public enum WsSources implements SourceEnum {

    IMU(OdometrySource.ONBOARD_IMU, "", Transform3d.kZero),
    BACKCAM(OdometrySource.PHOTON_VISION, "BackCam", Transform3d.kZero),
    FRONTCAM(OdometrySource.PHOTON_VISION, "FontCam", Transform3d.kZero);

    private OdometrySource mSource;
    private String mPort;
    private Transform3d mOffset;
    private boolean mEnabled;

    WsSources(OdometrySource pSource, String pPort, Transform3d pOffset, boolean pEnabled) {
        mSource = pSource;
        mPort = pPort;
        mOffset = pOffset;
        mEnabled = pEnabled;
    }

    WsSources(OdometrySource pSource, String pPort, Transform3d pOffset) {
        this(pSource, pPort, pOffset, true);
    }

    @Override
    public OdometrySource getSource() {
        return mSource;
    }

    @Override
    public String getPort() {
        return mPort;
    }

    @Override
    public Transform3d getOffset() {
        return mOffset;
    }

    @Override
    public boolean isEnabled() {
        return mEnabled;
    }

}
