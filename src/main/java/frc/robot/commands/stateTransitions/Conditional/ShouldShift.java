/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.stateTransitions.Conditional;

import java.util.Map;
import static java.util.Map.entry;

import edu.wpi.first.wpilibj2.command.SelectCommand;
import frc.robot.GlobalManager;
import frc.robot.Subsystems;
import frc.robot.commands.stateTransitions.transitionCommandGroups.BypassCommand;
import frc.robot.commands.supersystem.indexer.IndexerDriveForward;

/**
 * Add your docs here.
 */
public class ShouldShift extends SelectCommand{
    public ShouldShift(Subsystems subsystems){
        super(
            Map.ofEntries(
                    entry(true, new IndexerDriveForward(subsystems.stageOne, subsystems.stageTwo)), 
                    entry(false, new BypassCommand())
            ),
            GlobalManager.CommandConditionals::needsToShift
        );
    }
}
