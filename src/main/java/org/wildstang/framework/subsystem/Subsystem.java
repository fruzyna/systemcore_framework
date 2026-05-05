package org.wildstang.framework.subsystem;

public interface Subsystem {

    void init();
    
    void initInputs();

    void initOutputs();

    void initSubsystems();

    void update();
    
}
