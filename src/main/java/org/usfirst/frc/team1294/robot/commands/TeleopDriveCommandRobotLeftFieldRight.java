package org.usfirst.frc.team1294.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1294.robot.Robot;

/**
 * The default command for the drive system. Left stick is robot oriented with y axis joystick controlling robot
 * oriented y axis, x axis controlling robot turn rate, and triggers controlling robot oriented strafe. If right stick
 * is used past a defined dead zone, its y axis controls field oriented forward/back, x axis controls field oriented
 * right/left, and triggers control robot rotation.
 */
public class TeleopDriveCommandRobotLeftFieldRight extends Command {

  public static final double DEADZONE = 0.05;
  private XboxController joystick;

  public TeleopDriveCommandRobotLeftFieldRight() {
    requires(Robot.driveSubsystem);
    joystick = Robot.oi.getJoystick();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void execute() {
    double x;
    double y;
    double turn;
    double gyro; // set to reading from navx for field oriented, or 0 for robot oriented

    if (rightJoystickBeingUsed()) {
      // field oriented with triggers rotation
      y = joystick.getY(GenericHID.Hand.kRight);
      x = joystick.getX(GenericHID.Hand.kRight);
      turn = joystick.getTriggerAxis(GenericHID.Hand.kRight) - joystick.getTriggerAxis(GenericHID.Hand.kLeft);
      gyro = Robot.driveSubsystem.getAngle();
    } else {
      // robot oriented with triggers strafing
      x = joystick.getTriggerAxis(GenericHID.Hand.kRight) - joystick.getTriggerAxis(GenericHID.Hand.kLeft);
      y = joystick.getY(GenericHID.Hand.kLeft);
      turn = joystick.getX(GenericHID.Hand.kLeft);
      gyro = 0;
    }

    Robot.driveSubsystem.mecanumDrive(x, y, turn, gyro);
  }

  private boolean rightJoystickBeingUsed() {
    return Math.abs(joystick.getX(GenericHID.Hand.kRight)) > DEADZONE || Math.abs(joystick.getX(GenericHID.Hand.kRight)) > DEADZONE;
  }
}
