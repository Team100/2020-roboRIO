package frc.robot.commands.climberConstants;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.ControlPanelSpinner;

public class Extend extends CommandBase {

  public  Climber climber;

  public Extend(Climber climber) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.climber = climber;
    addRequirements(this.climber);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    climber.spin(Constants.ClimberConstants.RetractSpeed.EXTEND_SPEED);
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