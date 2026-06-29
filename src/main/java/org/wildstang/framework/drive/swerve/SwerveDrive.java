package org.wildstang.framework.drive.swerve;

import org.wildstang.framework.subsystem.Subsystem;
import org.wpilib.math.geometry.Translation2d;
import org.wpilib.math.kinematics.SwerveDriveKinematics;
import org.wpilib.math.kinematics.SwerveModulePosition;

public abstract class SwerveDrive implements Subsystem {

    private SwerveDriveKinematics kinematics;
    private SwerveModulePosition[] positions;

    public SwerveDrive() {
        Translation2d[] moduleTranslations = getModuleTranslations();
        kinematics = new SwerveDriveKinematics(moduleTranslations);
        positions = new SwerveModulePosition[moduleTranslations.length];
        for (int i = 0; i < moduleTranslations.length; ++i) {
            positions[i] = new SwerveModulePosition();
        }
    }

    public SwerveDriveKinematics getKinematics() {
        return kinematics;
    }

    public SwerveModulePosition[] getModulePositions() {
        return positions;
    }

    public abstract Translation2d[] getModuleTranslations();
}
