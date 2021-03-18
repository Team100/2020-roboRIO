/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class StopClimber extends CommandBase {

  public Climber climber;

  public StopClimber(Climber climber) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    System.out.println("kjfdsahgewhfwiujewakjealijeldewa;oi          " + getRequirements().toString());
    this.climber = climber;
    addRequirements(this.climber);
    System.out.println("kjfdsahgewAAAAAAAAAAAAAAjeldewa;oi           " + getRequirements().toString());
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    this.climber.spin(0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    this.climber.spin(0);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  protected void end() {
    this.climber.spin(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
    end();
  }
}
