package org.wildstang.framework.logger;

import org.wpilib.telemetry.Telemetry;
import org.wpilib.telemetry.TelemetryTable;

/**
 * A base class implemented by all classes which should own a TelemetryTable.
 */
public abstract class WsTelemetry {
    protected TelemetryTable telemetry;

    /**
     * Called, ideally right after construction of the child class, to populate telemetry.
     * @param name Name of the TelemetryTable belonging to the child class.
     */
    public void setupTelemetry(String name) {
        telemetry = Telemetry.getTable(name);
    }
}
