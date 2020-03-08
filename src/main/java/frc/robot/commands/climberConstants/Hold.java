package frc.robot.commands.climberConstants;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;

public class Hold extends CommandBase {

  public  Climber climber;

  public Hold(Climber climber) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.climber = climber;
    addRequirements(this.climber);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    climber.spin(0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
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