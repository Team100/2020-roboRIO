/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Subsystems;
import frc.robot.commands.supersystem.turret.*;

/**
 * Add your docs here.
 */
public class Triggers {
    
    //Indexer Triggers
    public Trigger indexerEntranceSensor;
    public Trigger indexerExitSensor;

    //Turret Triggers
    public Trigger cameraTrigger;


    /**
     * Initialize Triggers
     * 
     * @param subsystems Subsystem cluster
     */
    public Triggers(Subsystems subsystems) {
        //Indexer Triggers
        indexerEntranceSensor = new Trigger(subsystems.stageOne::getSensorValue);
        indexerExitSensor = new Trigger(subsystems.stageTwo::getSensorValue);

        //Turret Triggers
        cameraTrigger = new Trigger(() -> GlobalManager.TurretManager.targetAcquired); //subsystems.turret::hasTarget);

        turretConditionals(subsystems.turret);
        indexerConditionals(subsystems.stageOne, subsystems.stageTwo);
    }

    /**
     * Conditional for Indexer subsystem
     * 
     * @param stageOne Indexer Stage Two subsystem
     * @param stageTwo Indexer Stage Two subsystem
     */
    private void indexerConditionals(frc.robot.subsystems.IndexerStageOne stageOne, frc.robot.subsystems.IndexerStageTwo stageTwo) {
        
    }

    /**
     * Conditional for Turret subsystem
     * 
     * @param turret Turret subsystem
     */
    private void turretConditionals(frc.robot.subsystems.Turret turret) {
        cameraTrigger.whenActive(new ConditionalCommand(    new TurretSlew(turret),
                                                            new TurretLock(turret),
                                                            () -> (true)));
        cameraTrigger.whenInactive(new TurretScan(turret));
    
    }
}
