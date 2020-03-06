/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.stateTransitions.Conditional;


import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import frc.robot.GlobalManager;
import frc.robot.Subsystems;
import frc.robot.GlobalManager.CommandConditionals.*;
import frc.robot.commands.stateTransitions.TriggerMap.ShooterMoveType;
import frc.robot.commands.supersystem.indexer.IndexerDriveForward;
import frc.robot.commands.supersystem.indexer.indexStageOne.IndexerStageOneDriveForward;
import frc.robot.commands.supersystem.shooter.ShooterStop;

import java.util.Map;
import static java.util.Map.entry;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ShouldStopCommand extends SelectCommand {
  /**
   * Creates a new OnShouldIntake.
   */
  public ShouldStopCommand(Subsystems subsystems) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();

    super(
        Map.ofEntries(
            entry(ShooterMoveType.STOPPED, new ShooterStop(subsystems.shooter))
        ),
        GlobalManager.CommandConditionals::shouldStop
    );
  }
}