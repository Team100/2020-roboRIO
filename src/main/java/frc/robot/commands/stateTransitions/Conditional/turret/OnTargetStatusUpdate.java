/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.stateTransitions.Conditional.turret;

import java.util.Map;
import static java.util.Map.entry;

import edu.wpi.first.wpilibj2.command.SelectCommand;
import frc.robot.GlobalManager;
import frc.robot.Subsystems;
import frc.robot.FRCLib.Cyclone.CycloneController;
import frc.robot.GlobalManager.CommandConditionals.TurretConditionals.TurretAction;
import frc.robot.commands.stateTransitions.transitionCommandGroups.BypassCommand;
import frc.robot.commands.supersystem.turret.TurretLock;
import frc.robot.commands.supersystem.turret.TurretScan;
import frc.robot.commands.supersystem.turret.TurretSlew;
import frc.robot.commands.supersystem.turret.TurretStop;

public class OnTargetStatusUpdate extends SelectCommand {
    /**
     * Creates a new OnAcquireTarget.
     */
    public OnTargetStatusUpdate(Subsystems subsystems, CycloneController cyclone) {
        // Use addRequirements() here to declare subsystem dependencies.
        super(Map.ofEntries(
            entry(TurretAction.NONE, new BypassCommand()),
            entry(TurretAction.STOP, new TurretStop(subsystems.turret)),
            entry(TurretAction.SCAN, new TurretScan(subsystems.turret, cyclone)),
            entry(TurretAction.SLEW, new TurretSlew(subsystems.turret)),
            entry(TurretAction.LOCK, new TurretLock(subsystems.turret))
        ), GlobalManager.CommandConditionals.TurretConditionals::targetAction);
    }
}
