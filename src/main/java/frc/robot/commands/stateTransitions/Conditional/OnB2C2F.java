/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.stateTransitions.Conditional;

import java.util.Map;
import static java.util.Map.entry;

import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.GlobalManager;
import frc.robot.Subsystems;
import frc.robot.GlobalManager.CommandConditionals.B2C2FAction;
import frc.robot.commands.stateTransitions.transitionCommandGroups.BypassCommand;
import frc.robot.commands.stateTransitions.transitionCommandGroups.StopIndexer;

/**
 * Add your docs here.
 */
public class OnB2C2F extends SelectCommand {
    public OnB2C2F(Subsystems subsystems){
        super(Map.ofEntries(
            entry(B2C2FAction.STOP_MOTORS, new SequentialCommandGroup(new PrintCommand("STOPPING IN B2C2F"), new StopIndexer(subsystems))),
            //entry(B2C2TAction.SET_UNCERTAIN,new SetIndexerLocationState(IndexerLocationState.UNCERTAIN)),
            entry(B2C2FAction.NONE, new BypassCommand())
    
    ), GlobalManager.CommandConditionals::evaluateB2C2F);
    }
   
}
