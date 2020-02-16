/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.supersystem.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Indexer.ActionState;
import frc.robot.GlobalManager;
import frc.robot.GlobalManager.SupersystemManager;
import frc.robot.GlobalManager.SupersystemManager.SupersystemState;

public class IndexerDriveBackward extends CommandBase {
  /**
   * Creates a new IndexerDriveBackward.
   */
  public Indexer indexer;

  public IndexerDriveBackward(Indexer indexer) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.indexer = indexer;
    addRequirements(this.indexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    GlobalManager.SupersystemManager.supersystemState = SupersystemState.REVERSING;
    indexer.indexerStageOne.drivePercentOutput(-(Constants.IndexerConstants.IndexerMotionParameters.STAGE_ONE_PERCENT_OUTPUT_BACKWARD));
    indexer.indexerStageTwo.drivePercentOutput(-(Constants.IndexerConstants.IndexerMotionParameters.STAGE_TWO_PERCENT_OUTPUT_BACKWARD));
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
