package org.usfirst.frc.team1294.robot.commands;

import org.usfirst.frc.team1294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShootFuel extends Command {

  public ShootFuel() {
    super("Drive motor");
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    Robot.fuelSubsystem.setShootMotor(1);
  }
  @Override
  protected void end() {
    Robot.fuelSubsystem.setShootMotor(0);
  }

  @Override
  protected void interrupted() {
    end();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}