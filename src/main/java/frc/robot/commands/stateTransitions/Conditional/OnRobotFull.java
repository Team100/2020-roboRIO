/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.stateTransitions.Conditional;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.GlobalManager;
import frc.robot.Subsystems;
import frc.robot.GlobalManager.IndexerManager;
import frc.robot.commands.supersystem.intake.intakePivot.IntakeMoveUp;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class OnRobotFull extends ParallelCommandGroup {
  public OnRobotFull(Subsystems s) {
    // Use addRequirements() here to declare subsystem dependencies.
    super(new IntakeMoveUp(s.intakePivot, false));
  }
  
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

}