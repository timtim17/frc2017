package org.usfirst.frc.team1294.robot.subsystems;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1294.robot.RobotMap;
import org.usfirst.frc.team1294.robot.commands.MecanumDriveCommand;

/**
 * @author Austin Jenchi (timtim17)
 */
public class DriveSubsystem extends Subsystem {

  public final CANTalon leftFrontTalon;
  public final CANTalon leftRearTalon;
  public final CANTalon rightFrontTalon;
  public final CANTalon rightRearTalon;
  private final RobotDrive robotDrive;
  private static AHRS navX;
//  private final Ultrasonic leftUltrasonic;
//  private final Ultrasonic rightUltrasonic;

  public DriveSubsystem() {
    super("DriveSubsystem");

    leftFrontTalon = new CANTalon(RobotMap.DRIVEBASE_LEFT_FRONT_TALON);
    leftRearTalon = new CANTalon(RobotMap.DRIVEBASE_LEFT_REAR_TALON);
    rightFrontTalon = new CANTalon(RobotMap.DRIVEBASE_RIGHT_FRONT_TALON);
    rightRearTalon = new CANTalon(RobotMap.DRIVEBASE_RIGHT_REAR_TALON);
    robotDrive = new RobotDrive(leftFrontTalon, leftRearTalon, rightFrontTalon, rightRearTalon);
    robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
    navX = new AHRS(SerialPort.Port.kMXP);

//    leftUltrasonic = new Ultrasonic(RobotMap.DRIVEBASE_ULTRASONIC_PING_LEFT, RobotMap.DRIVEBASE_ULTRASONIC_ECHO_LEFT, Ultrasonic.Unit.kInches);
//    rightUltrasonic = new Ultrasonic(RobotMap.DRIVEBASE_ULTRASONIC_PING_RIGHT, RobotMap.DRIVEBASE_ULTRASONIC_ECHO_RIGHT, Ultrasonic.Unit.kInches);
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new MecanumDriveCommand());
  }

  public void mecanumDrive(double x, double y, double rotate, double gyro) {
    robotDrive.mecanumDrive_Cartesian(x, y, rotate, gyro);
    //robotDrive.mecanumDrive_Cartesian();
  }

  public double getAngle() {
    double angle = navX.getAngle();
    System.out.println(angle);
    return angle;
  }


  public void resetGyro() {
    navX.reset();
  }

  public double getDistanceToWall() {
//    return (leftUltrasonic.getRangeMM() + rightUltrasonic.getRangeMM()) / 2;
    return -1;
  }

  public double getAngleToWall() {
    return 0;
  }

  public void toggleSafety() {
    robotDrive.setSafetyEnabled(!robotDrive.isSafetyEnabled());
  }
}
