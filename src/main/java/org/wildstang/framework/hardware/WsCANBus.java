package org.wildstang.framework.hardware;

import org.wpilib.hardware.bus.CANPort;

import com.ctre.phoenix6.CANBus;

/**
 * This is a class containing a series of helper constants which are used to access the 5 CAN ports on SystemCore.
 * This class cannot be constructed.
 */
public abstract class WsCANBus {
    
    public static CANBus S0 = new CANBus(CANPort.CAN_S0);
    public static CANBus S1 = new CANBus(CANPort.CAN_S1);
    public static CANBus S2 = new CANBus(CANPort.CAN_S2);
    public static CANBus S3 = new CANBus(CANPort.CAN_S3);
    public static CANBus S4 = new CANBus(CANPort.CAN_S4);

}
