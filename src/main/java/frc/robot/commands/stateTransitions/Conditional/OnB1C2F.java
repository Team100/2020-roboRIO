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
import frc.robot.GlobalManager.CommandConditionals.B1C2FAction;
import frc.robot.commands.stateTransitions.transitionCommandGroups.BypassCommand;
import frc.robot.commands.stateTransitions.transitionCommandGroups.StopIndexer;

/**
 * Add your docs here.
 */
public class OnB1C2F extends SelectCommand {
    public OnB1C2F(Subsystems subsystems){
        super(Map.ofEntries(
            entry(B1C2FAction.STOP_MOTORS_AND_INCREMENT, new SequentialCommandGroup(new PrintCommand("Stopping B1C2F"), new StopIndexer(subsystems), new IncrementIndexerCounter())),
            entry(B1C2FAction.STOP_MOTORS, new SequentialCommandGroup(new PrintCommand("Stopping B1C2F"), new StopIndexer(subsystems))),
            entry(B1C2FAction.NONE, new BypassCommand())
    ), GlobalManager.CommandConditionals::evaluateB1C2F);
    }
}
