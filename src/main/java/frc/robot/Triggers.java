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
import frc.robot.commands.stateTransitions.TriggerMap;
import frc.robot.commands.supersystem.turret.*;

/**
 * All of the virtual triggers for state transitions on the robot
 */
public class Triggers {

    /**
     * The subsystems that the Trigger has access to
     */
    public Subsystems subsystems;

    /**
     * The triggermap for all of the commands
     */
    public TriggerMap triggerMap;

    public Trigger indexerFull;

    /**
     * The trigger for everything that happens re: the indexerEntranceSensor
     */
    
    //Indexer Triggers
    public Trigger indexerEntranceSensor;

    /**
     * The trigger for everthing that happens re: the indexerExitSensor
     */
    public Trigger indexerExitSensor;

    public Trigger shouldIntake;
    public Trigger indexerShouldShift;
  
      public Trigger cameraTrigger;


    /**
     * Create a new instance of all of the triggers
     * @param subsystems the subsystems that can be impacted
     */
    public Triggers(Subsystems subsystems) {
        this.subsystems = subsystems;
        indexerFull = new Trigger(GlobalManager.IndexerManager::subsystemIsFull);
        indexerEntranceSensor = new Trigger(subsystems.stageOne::getSensorValue);
        indexerExitSensor = new Trigger(subsystems.stageTwo::getSensorValue);

        shouldIntake = new Trigger(GlobalManager.IndexerManager::shouldIntake);
        indexerShouldShift = new Trigger(GlobalManager.IndexerManager::shouldShift);

        this.triggerMap = new TriggerMap(this.subsystems);

        this.shouldIntake.whenActive(triggerMap.shouldIntake);
        this.indexerShouldShift.whenActive(triggerMap.shouldShift);

        this.indexerEntranceSensor.whenInactive(triggerMap.onB1C2F);
        this.indexerExitSensor.whenActive(triggerMap.onB2C2T);
      cameraTrigger = new Trigger(() -> GlobalManager.TurretManager.targetAcquired); //subsystems.turret::hasTarget);

        turretConditionals(subsystems.turret);
        indexerConditionals(subsystems.stageOne, subsystems.stageTwo);
    //Turret Triggers
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
