package org.wildstang.po27.robot;

import org.wildstang.framework.odometry.ZoneEnum;
import org.wpilib.math.geometry.Rectangle2d;
import org.wpilib.math.geometry.Rotation2d;
import org.wpilib.math.geometry.Pose2d;

public enum WsZones implements ZoneEnum {

    ALLIANCE_ZONE(new Rectangle2d(new Pose2d(243, 0, Rotation2d.kZero), 158.6, 317.7), false, true),
    NEUTRAL_ZONE(new Rectangle2d(Pose2d.kZero, 283, 317.7));

    private Rectangle2d mBounds;
    private boolean mSymmetrical;
    private boolean mMirrored;

    WsZones(Rectangle2d pBounds, boolean pSymmetrical, boolean pMirrored) {
        mBounds = pBounds;
        mSymmetrical = pSymmetrical;
        mMirrored = pMirrored;
    }

    WsZones(Rectangle2d pBounds) {
        this(pBounds, false, false);
    }

    @Override
    public Rectangle2d getBounds() {
        return mBounds;
    }

    @Override
    public boolean isSymmetrical() {
        return mSymmetrical;
    }

    @Override
    public boolean isMirrored() {
        return mMirrored;
    }

}
