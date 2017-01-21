package org.usfirst.frc.team1294.robot.subsystems;

import org.usfirst.frc.team1294.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * A subsystem that encompases every motor and sensor related to the fuel shooting and/or collecting game mech.
 */
public class FuelSubsystem extends Subsystem {
	public final CANTalon loadTalon;
	public final CANTalon shootTalon;

    public FuelSubsystem() {
        super("FuelSubsystem");
        loadTalon = new CANTalon(RobotMap.LOAD_TALON);
        shootTalon = new CANTalon(RobotMap.SHOOT_TALON);
    }

    @Override
    protected void initDefaultCommand() {

    }
    public void setLoadMotor(double speed) {
        loadTalon.set(speed);
    }
    public void setShootMotor(double speed) {
        shootTalon.set(speed);
    }
}
