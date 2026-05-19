package org.wildstang.po27.robot;

import org.wildstang.framework.opmode.WsOpMode;
import org.wildstang.framework.opmode.OpModeEnum;
import org.wildstang.framework.opmode.Sleeper;
import org.wpilib.hardware.hal.RobotMode;
import org.wpilib.opmode.OpMode;

public enum WsOpModes implements OpModeEnum {

    MATCH("Match", WsOpMode.class, RobotMode.TELEOPERATED, true, true, true),
    SLEEPER("Sleeper", Sleeper.class),
    SLEEPER_1("No Sim", Sleeper.class, RobotMode.AUTONOMOUS, true, false, true),
    SLEEPER_2("Disabled", Sleeper.class, RobotMode.AUTONOMOUS, true, true, false),
    SLEEPER_3("No FMS", Sleeper.class, RobotMode.AUTONOMOUS, false, true, true),
    CHANGED_FNS("Changed Functions", WsOpMode.class, RobotMode.UTILITY, false, true, true),
    INPUT_FNS("Input Functions", WsOpMode.class, RobotMode.UTILITY, false, true, true);

    private String mName;
    private Class<? extends OpMode> mOpModeClass;
    private RobotMode mRobotMode;
    private boolean mCompetition;
    private boolean mSimulation;
    private boolean mEnabled;

    WsOpModes(String pName, Class<? extends OpMode> pOpModeClass, RobotMode pRobotMode, boolean pCompetition, boolean pSimulation, boolean pEnabled) {
        mName = pName;
        mOpModeClass = pOpModeClass;
        mRobotMode = pRobotMode;
        mCompetition = pCompetition;
        mSimulation = pSimulation;
        mEnabled = pEnabled;
    }

    WsOpModes(String pName, Class<? extends OpMode> pOpModeClass) {
        this(pName, pOpModeClass, RobotMode.AUTONOMOUS, true, true, true);
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public Class<? extends OpMode> getOpModeClass() {
        return mOpModeClass;
    }

    @Override
    public RobotMode getRobotMode() {
        return mRobotMode;
    }

    @Override
    public boolean inCompetition() {
        return mCompetition;
    }

    @Override
    public boolean inSimulation() {
        return mSimulation;
    }

    @Override
    public boolean isEnabled() {
        return mEnabled;
    }

}
