// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.wildstang.framework.opmode;

public abstract class WsAutoOpMode extends WsOpMode {

  private boolean initialized;

  public WsAutoOpMode() {
    initialized = false;
  }

  @Override
  public void disabledPeriodic() {
    initialize();
    super.disabledPeriodic();
  }

  @Override
  public void start() {
    initialize();
    super.start();
  }

  @Override
  public void end() {
    super.end();
  }
  
  private void initialize() {
    if (!initialized) {
      initProgram();
      initialized = true;
    }
  }

  protected abstract void initProgram();

  protected abstract void defineSteps();
}
