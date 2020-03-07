/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.stateTransitions.TriggerMap;
import frc.robot.commands.stateTransitions.Conditional.OnB1C2F;
import frc.robot.commands.stateTransitions.Conditional.OnB2C2F;
import frc.robot.commands.stateTransitions.Conditional.OnB2C2T;
import frc.robot.commands.stateTransitions.Conditional.OnRobotFull;
import frc.robot.commands.stateTransitions.Conditional.OnShouldIntake;
import frc.robot.commands.stateTransitions.Conditional.ShouldShift;
import frc.robot.commands.supersystem.turret.TurretLock;
import frc.robot.commands.supersystem.turret.TurretScan;
import frc.robot.commands.supersystem.turret.TurretSlew;

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

    public Trigger indexerShiftSensor;

    public Trigger shouldIntake;
    public Trigger indexerShouldShift;
  
      public Trigger cameraTrigger;


    /**
     * Create a new 
     * instance of all of the triggers
     * @param subsystems the subsystems that can be impacted
     * @param container a map to the RobotContainer for getting Joystick access
     */
    public Triggers(Subsystems subsystems, RobotContainer container) {
        this.subsystems = subsystems;
        indexerFull = new Trigger(GlobalManager.IndexerManager::subsystemIsFull);
        indexerEntranceSensor = new Trigger(subsystems.stageOne::getSensorValue);
        indexerExitSensor = new Trigger(subsystems.stageTwo::getSensorValue);

        indexerShiftSensor = new Trigger(subsystems.stageTwo::getShiftSensorValue);
        

      
        shouldIntake = new Trigger(GlobalManager.SupersystemManager::getShouldIntake);
        indexerShouldShift = new Trigger(GlobalManager.IndexerManager::shouldShift);

        this.triggerMap = new TriggerMap(this.subsystems);
        this.shouldIntake.whenActive(new OnShouldIntake(this.subsystems));
        this.indexerShouldShift.whenActive(new ShouldShift(this.subsystems));
        //this.indexerEntranceSensor.whenActive(new InstantCommand(()->System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$")));
        this.indexerEntranceSensor.whenActive(new OnB1C2F(this.subsystems));
        this.indexerShiftSensor.whenActive(new OnB2C2T(this.subsystems)); //TODO Change name of cmd to B2C2F
        this.indexerShiftSensor.whenInactive(new OnB2C2F(this.subsystems));

        this.indexerFull.whenActive(new OnRobotFull(this.subsystems));
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
