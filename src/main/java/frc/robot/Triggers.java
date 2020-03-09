/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.stateTransitions.TriggerMap;
import frc.robot.commands.stateTransitions.Conditional.OnB1C2F;
import frc.robot.commands.stateTransitions.Conditional.OnB2C2T;
import frc.robot.commands.stateTransitions.Conditional.OnRobotFull;
import frc.robot.commands.stateTransitions.Conditional.OnShouldIntake;
import frc.robot.commands.stateTransitions.Conditional.ShouldShift;
import frc.robot.commands.stateTransitions.Conditional.turret.OnTargetStatusUpdate;

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
        // public TriggerMap triggerMap;

        
        // Indexer Triggers
        public Trigger indexerEntranceSensor;
        public Trigger indexerExitSensor;
        public Trigger indexerShiftSensor;
        public Trigger indexerShouldShift;
        public Trigger indexerFull;

        //Intake Triggers
        public Trigger shouldIntake;
    
        //Turret Triggers
        public Trigger cameraTrigger;
        public Trigger lockTrigger;


        /**
         * Create a new 
         * instance of all of the triggers
         * @param subsystems the subsystems that can be impacted
         * @param container a map to the RobotContainer for getting Joystick access
         */
        public Triggers(Subsystems subsystems, RobotContainer container) {
            this.subsystems = subsystems;

            // Indexer Triggers
            indexerFull = new Trigger(GlobalManager.IndexerManager::subsystemIsFull);
            indexerEntranceSensor = new Trigger(subsystems.stageOne::getSensorValue);
            indexerExitSensor = new Trigger(subsystems.stageTwo::getSensorValue);
            indexerShiftSensor = new Trigger(subsystems.stageTwo::getShiftSensorValue);
        
            // Intake Triggers
            shouldIntake = new Trigger(GlobalManager.SupersystemManager::getShouldIntake);
            indexerShouldShift = new Trigger(GlobalManager.IndexerManager::shouldShift);

            // TurretTriggers
            cameraTrigger = new Trigger(() -> GlobalManager.TurretManager.targetAcquired);
            lockTrigger = new Trigger(() -> GlobalManager.TurretManager.targetLocked);

            
            // this.triggerMap = new TriggerMap(this.subsystems);


            // Indexer Triggers
            this.indexerShouldShift.whenActive(new ShouldShift(this.subsystems));
            // this.indexerEntranceSensor.whenActive(new InstantCommand(()->System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$")));
            this.indexerEntranceSensor.whenActive(new OnB1C2F(this.subsystems));
            this.indexerShiftSensor.whenInactive(new OnB2C2T(this.subsystems)); //TODO Change name of cmd to B2C2F
            // this.indexerShiftSensor.whenInactive(new OnB2C2F(this.subsystems));
            this.indexerFull.whenActive(new OnRobotFull(this.subsystems));

            // Intake Triggers
            this.shouldIntake.whenActive(new OnShouldIntake(this.subsystems));

            // Turret Trigger
            this.cameraTrigger.whenActive(new OnTargetStatusUpdate(this.subsystems, container.cyclone));
            this.cameraTrigger.whenInactive(new OnTargetStatusUpdate(this.subsystems, container.cyclone));
            this.lockTrigger.whenActive(new OnTargetStatusUpdate(this.subsystems, container.cyclone));
        }
}
