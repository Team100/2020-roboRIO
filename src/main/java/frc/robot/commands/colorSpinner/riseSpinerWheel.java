/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.colorSpinner;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ControlPanelSpinner;

public class riseSpinerWheel extends CommandBase {

  public ControlPanelSpinner controlPanelSpinner;

  public riseSpinerWheel(ControlPanelSpinner controlPanelSpinner) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.controlPanelSpinner = controlPanelSpinner;
    addRequirements(this.controlPanelSpinner);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    controlPanelSpinner.spin(Constants.ControlPanelSpinnerConstants.ControlPanelSpinnerMotors.RISE_SPEED);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    controlPanelSpinner.spin(Constants.ControlPanelSpinnerConstants.ControlPanelSpinnerMotors.RISE_SPEED);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  protected void end() {
    controlPanelSpinner.spin(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
    end();
  }
}
