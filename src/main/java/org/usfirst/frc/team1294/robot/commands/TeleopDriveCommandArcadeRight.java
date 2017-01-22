package org.usfirst.frc.team1294.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1294.robot.Robot;

/**
 * Created by jxlewis on 1/21/17.
 */
public class TeleopDriveCommandArcadeRight extends Command {
  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void execute() {
    XboxController joystick = Robot.oi.getJoystick();
    double commandedX = joystick.getTriggerAxis(GenericHID.Hand.kRight) - joystick.getTriggerAxis(GenericHID.Hand.kLeft);
    double commandedY = joystick.getY(GenericHID.Hand.kRight);
    double commandedTurn = joystick.getX(GenericHID.Hand.kRight);
    Robot.driveSubsystem.mecanumDrive(commandedX, commandedY, commandedTurn, 0);
  }
}
