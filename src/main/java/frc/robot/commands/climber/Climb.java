/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;

public class Climb extends CommandBase {

  public Climber climber;

  boolean climbed = false;

  public Climb(Climber climber) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.climber = climber;
    addRequirements(this.climber);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    climber.spin(-Constants.ClimberConstants.ClimberMotors.SPIN_SPEED);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    while(!climber.extended()){
      climber.spin(-Constants.ClimberConstants.ClimberMotors.SPIN_SPEED);
    }
    while(!climber.retracted()){
      climber.spin(Constants.ClimberConstants.ClimberMotors.SPIN_SPEED);
    }
    climbed=true;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    if(climbed){
      return true;
    }
    return false;
  }

  // Called once after isFinished returns true
  protected void end() {
    climber.spin(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
    end();
  }
}
