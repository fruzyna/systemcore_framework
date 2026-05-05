// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.wildstang.framework.opmode;

import org.wildstang.framework.Core;
import org.wpilib.opmode.PeriodicOpMode;

public abstract class WsOpMode extends PeriodicOpMode {

  @Override
  public void disabledPeriodic() {
    /* Called periodically (on every DS packet) while the robot is disabled. */
    Core.getInstance().update();
  }

  @Override
  public void start() {
    /* Called once when the robot is enabled. */
  }

  @Override
  public void periodic() {
    /* Called periodically (set time interval) while the robot is enabled. */
    Core.getInstance().update();
  }

  @Override
  public void end() {
    /* Called when the robot is disabled (after previously being enabled). */
  }

  @Override
  public void close() {
    /* Called when the opmode is de-selected / no additional methods will be called. */
  }
}
