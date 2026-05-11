package org.wildstang.po27.robot;

import org.wildstang.framework.Core;
import org.wildstang.framework.subsystem.Subsystem;
import org.wildstang.framework.subsystem.SubsystemEnum;
import org.wildstang.po27.subsystem.GamepadTest;
import org.wildstang.po27.subsystem.LoopTimer;

public enum WsSubsystems implements SubsystemEnum {

    LOOP_TIMER("Loop Timer", LoopTimer.class),
    GAMEPAD("Gamepad", GamepadTest.class);

    private String mName;
    private Class<? extends Subsystem> mSubsystemClass;
    private boolean mEnabled;

    WsSubsystems(String pName, Class<? extends Subsystem> pSubsystemClass, boolean pEnabled) {
        mName = pName;
        mSubsystemClass = pSubsystemClass;
        mEnabled = pEnabled;
    }

    WsSubsystems(String pName, Class<? extends Subsystem> pSubsystemClass) {
        this(pName, pSubsystemClass, true);
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public Class<? extends Subsystem> getSubsystemClass() {
        return mSubsystemClass;
    }

    @Override
    public boolean isEnabled() {
        return mEnabled;
    }
    
    public Subsystem get() {
        return Core.getInstance().getSubsystemManager().getSubsystem(this);
    }

}
