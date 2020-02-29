/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Subsystems;
import frc.robot.commands.stateTransitions.TriggerMap;

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

    /**
     * The trigger for everything that happens re: the indexerEntranceSensor
     */
    public Trigger indexerEntranceSensor;

    /**
     * The trigger for everthing that happens re: the indexerExitSensor
     */
    public Trigger indexerExitSensor;

    /**
     * Create a new instance of all of the triggers
     * @param subsystems the subsystems that can be impacted
     */
    public Triggers(Subsystems subsystems) {
        this.subsystems = subsystems;
        indexerEntranceSensor = new Trigger(subsystems.stageOne::getSensorValue);
        indexerExitSensor = new Trigger(subsystems.stageTwo::getSensorValue);

        this.triggerMap = new TriggerMap(this.subsystems);
    }

   
}
