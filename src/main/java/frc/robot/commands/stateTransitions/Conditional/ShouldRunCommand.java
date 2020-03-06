/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.stateTransitions.Conditional;


import edu.wpi.first.wpilibj2.command.SelectCommand;
import frc.robot.GlobalManager;
import frc.robot.Subsystems;
import frc.robot.GlobalManager.CommandConditionals.*;
import frc.robot.commands.stateTransitions.TriggerMap.ShooterMoveType;
import frc.robot.commands.stateTransitions.transitionCommandGroups.BypassCommand;
import frc.robot.commands.supersystem.shooter.ShooterRecover;

import java.util.Map;
import static java.util.Map.entry;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ShouldRunCommand extends SelectCommand {
  public ShouldRunCommand(Subsystems subsystems) {
    super(Map.ofEntries(
            entry(ShooterMoveType.SPINNINGUP, new ShooterRecover(subsystems.shooter)),
            entry(ShooterMoveType.NONE, new BypassCommand())
        ), GlobalManager.CommandConditionals::shouldStop
    );
  }
}