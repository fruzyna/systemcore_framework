package org.wildstang.po27.subsystem;

import org.wildstang.framework.drive.swerve.SwerveDrive;
import org.wildstang.framework.opmode.OpModeEnum;
import org.wpilib.math.geometry.Translation2d;

public class Drive extends SwerveDrive {

    @Override
    public void init() {
    }

    @Override
    public void initSubsystems() {
    }

    @Override
    public void autoUpdate(OpModeEnum autoMode) {
    }

    @Override
    public void teleUpdate(OpModeEnum teleMode) {
    }

    @Override
    public void utilUpdate(OpModeEnum utilMode) {
    }

    @Override
    public void applyChanges() {
    }

    @Override
    public Translation2d[] getModuleTranslations() {
        return new Translation2d[]{
            new Translation2d(10, 10),
            new Translation2d(-10, 10),
            new Translation2d(10, -10),
            new Translation2d(-10, -10),
        };
    }
}
