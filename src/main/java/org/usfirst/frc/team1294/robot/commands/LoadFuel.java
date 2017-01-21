package org.usfirst.frc.team1294.robot.commands;

import org.usfirst.frc.team1294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LoadFuel extends Command {

  public LoadFuel() {
    super("Drive motor");
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    Robot.fuelSubsystem.setLoadMotor(1);
  }
  @Override
  protected void end() {
    Robot.fuelSubsystem.setLoadMotor(0);
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