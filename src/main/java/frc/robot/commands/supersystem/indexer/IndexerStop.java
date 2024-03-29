/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.supersystem.indexer;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.supersystem.indexer.indexStageOne.IndexerStageOneStop;
import frc.robot.commands.supersystem.indexer.indexStageTwo.IndexerStageTwoStop;
import frc.robot.subsystems.IndexerStageOne;
import frc.robot.subsystems.IndexerStageTwo;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class IndexerStop extends ParallelCommandGroup {
  /**
   * Creates a new IndexerStop.
   */
  public IndexerStop(IndexerStageOne stageOne, IndexerStageTwo stageTwo) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
    super(new IndexerStageOneStop(stageOne), new IndexerStageTwoStop(stageTwo));
  }
}
