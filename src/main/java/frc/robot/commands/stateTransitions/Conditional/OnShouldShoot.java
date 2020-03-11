/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.stateTransitions.Conditional;

import edu.wpi.first.wpilibj2.command.SelectCommand;
import frc.robot.GlobalManager;
import frc.robot.Subsystems;
import frc.robot.GlobalManager.CommandConditionals.ShooterMoveType;
import frc.robot.commands.supersystem.shooter.ShooterRecover;
import frc.robot.commands.supersystem.shooter.ShooterStop;

import static java.util.Map.entry;

import java.util.Map;


/**
 * Add your docs here.
 */
public class OnShouldShoot extends SelectCommand {
    public OnShouldShoot(Subsystems subsystems){
        super(Map.ofEntries(
            entry(ShooterMoveType.STOPPED, new ShooterStop(subsystems.shooter)),
            entry(ShooterMoveType.SPINNING, new ShooterRecover(subsystems.shooter))),
    
        GlobalManager.CommandConditionals::shouldRun
);
    }
}

