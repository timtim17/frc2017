package org.usfirst.frc.team1294.robot.commands;

import org.usfirst.frc.team1294.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Austin Jenchi (timtim17)
 */
public class MecanumDriveCommand extends Command {

  public static final double DEADZONE = 0.05;
  private double abxYL;

  public MecanumDriveCommand() {
    requires(Robot.driveSubsystem);
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    XboxController joystick = Robot.oi.getJoystick();
    // if the magnitude of the left analog stick > right analog stick, use field oriented
    System.out.printf("lx: %.2f rx: %.2f // ly: %.2f ry: %.2f // ", joystick.getX(GenericHID.Hand.kLeft), joystick.getX(GenericHID.Hand.kRight), joystick.getY(GenericHID.Hand.kLeft), joystick.getY(GenericHID.Hand.kRight));
    double absXL = Math.abs(joystick.getX(GenericHID.Hand.kLeft));
    absXL = absXL < DEADZONE ? 0 : absXL;
    double absXR = Math.abs(joystick.getX(GenericHID.Hand.kRight));
    absXR = absXR < DEADZONE ? 0 : absXR;
    double abxYL = Math.abs(joystick.getY(GenericHID.Hand.kLeft));
    abxYL = abxYL < DEADZONE ? 0 : abxYL;
    double absYR = Math.abs(joystick.getY(GenericHID.Hand.kRight));
    absYR = absYR < DEADZONE ? 0 : absYR;
    if (absXL > absXR
            || abxYL > absYR) {	
      Robot.driveSubsystem.mecanumDrive(joystick.getX(GenericHID.Hand.kLeft),
              joystick.getY(GenericHID.Hand.kLeft), 
              0,
              Robot.driveSubsystem.getAngle());
      System.out.println("FIELD ORIENTED");
    } else if(absXL < absXR
            || abxYL < absYR){
      // otherwise use the right analog stick for robot oriented
    	 Robot.driveSubsystem.mecanumDrive(joystick.getTriggerAxis(GenericHID.Hand.kRight) - joystick.getTriggerAxis(GenericHID.Hand.kLeft),
                 joystick.getY(GenericHID.Hand.kRight),
                 joystick.getX(GenericHID.Hand.kRight),0);
      System.out.println("ROBOT ORIENTED");
    } else{
    	Robot.driveSubsystem.mecanumDrive(joystick.getTriggerAxis(GenericHID.Hand.kRight) - joystick.getTriggerAxis(GenericHID.Hand.kLeft),0,0,0);
	    }
	  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    // does nothing - this command will never end!
  }

  @Override
  protected void interrupted() {
    end();
  }
}
