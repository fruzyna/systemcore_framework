package org.wildstang.framework.odometry;

import org.wpilib.math.geometry.Rectangle2d;

public interface ZoneEnum {
    
    public Rectangle2d getBounds();

    public boolean isSymmetrical();

    public boolean isMirrored();
}
