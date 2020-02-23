/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * @deprecated
 */
public class Indexer extends SubsystemBase {
  public static enum ActionState {
    MOVE_FOWARD, MOVE_BACKWARDS, STOP
  }

  public DigitalInput frontSensor = new DigitalInput(Constants.IndexerConstants.IndexerSensors.FrontSensor.ID);
  public DigitalInput rearSensor = new DigitalInput(Constants.IndexerConstants.IndexerSensors.RearSensor.ID);

  /**
   * Keeps track of whether the last iteration was positive or not
   */
  public boolean lastIterateFront, lastIterateRear;

  /**
   * Keeps track of how many objects have passed the sensor
   */
  public int frontCount, rearCount;

  /**
   * Creates a new Indexer.
   */
  public Indexer() {

  }

  public void processBallDetectionSensors() {
    if (!frontSensor.get() && lastIterateFront) {
      lastIterateFront = false;
    } else if (frontSensor.get() && !lastIterateFront) {
      lastIterateFront = true;
      frontCount += 1;
      System.out.println("Front Count: " + frontCount);

    }

    if (!rearSensor.get() && lastIterateRear) {
      lastIterateRear = false;
    } else if (rearSensor.get() && !lastIterateRear) {
      lastIterateRear = true;
      rearCount += 1;
      System.out.println("Rear Count: " + rearCount);

    }
  }

  /**
   * Update any states
   */
  public void updateState() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    processBallDetectionSensors();
    updateState();
  }
}
