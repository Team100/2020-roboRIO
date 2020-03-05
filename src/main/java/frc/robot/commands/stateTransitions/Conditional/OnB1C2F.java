/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.stateTransitions.Conditional;

import java.util.Map;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import frc.robot.GlobalManager;
import frc.robot.Subsystems;
import frc.robot.GlobalManager.CommandConditionals.B1C2FAction;

/**
 * Add your docs here.
 */
public class OnB1C2F extends ConditionalCommand {
    public OnB1C2F(Subsystems subsystems){
        super( Map.ofEntries(
            entry(B1C2FAction.STOP_MOTORS, tcg.stopIndexer()),
            entry(B1C2FAction.NONE, tcg.bypassCommand())
    ), GlobalManager.CommandConditionals::evaluateB1C2F);
    }
}
