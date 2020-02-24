/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Subsystems;

/**
 * Add your docs here.
 */
public class Triggers {
    public Trigger indexerEntranceSensor;
    public Trigger indexerExitSensor;

    public Triggers(Subsystems subsystems) {
        indexerEntranceSensor = new Trigger(subsystems.stageOne::getSensorValue);
        indexerExitSensor = new Trigger(subsystems.stageTwo::getSensorValue);
    }

   
}
