/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.GlobalManager;
import frc.robot.FRCLib.Motors.FRCTalonSRX;


public class Climber extends SubsystemBase {

  /**
   * The winch motors
   */
  public FRCTalonSRX leftWinch;
  public FRCTalonSRX rightWinch;

  /**
   * The states that the winch can be in
   */
  public static enum ActionState {
    TURN_FORWARD, TURN_BACKWARDS, STOP
  }

  /**
   * The current state of winch
   */
  public ActionState actionState;

  /**
   * Creates a new Indexer.
   */
  public Climber() {
    //sensor = new DigitalInput(Constants.IndexerConstants.IndexerSensors.FrontSensor.ID);
    // Construct Motor Objects
    leftWinch = new FRCTalonSRX.FRCTalonSRXBuilder(
        Constants.ClimberConstants.ClimberMotors.LEFT_CAN_ID)
            .withInverted(Constants.ClimberConstants.ClimberMotors.INVERT)
            .withFeedbackPort(Constants.ClimberConstants.ClimberMotors.FEEDBACK_PORT)
            .withSensorPhase(Constants.ClimberConstants.ClimberMotors.SENSOR_PHASE)
            .withTimeout(Constants.ClimberConstants.ClimberMotors.TIMEOUT)
            .withCurrentLimitEnabled(Constants.ClimberConstants.ClimberMotors.ENABLE_CURRENT_LIMIT)
            .withCurrentLimit(Constants.ClimberConstants.ClimberMotors.CURRENT_LIMIT)
            .withOpenLoopRampRate(Constants.ClimberConstants.ClimberMotors.OPEN_LOOP_RAMP)
            .withNominalOutputForward(Constants.ClimberConstants.ClimberMotors.NOMINAL_OUTPUT_FORWARD)
            .withNominalOutputReverse(Constants.ClimberConstants.ClimberMotors.NOMINAL_OUTPUT_REVERSE)
            .withPeakOutputForward(Constants.ClimberConstants.ClimberMotors.PEAK_OUTPUT_FORWARD)
            .withPeakOutputReverse(Constants.ClimberConstants.ClimberMotors.PEAK_OUTPUT_REVERSE)
            .build();

    rightWinch = new FRCTalonSRX.FRCTalonSRXBuilder(
        Constants.ClimberConstants.ClimberMotors.RIGHT_CAN_ID)
            .withInverted(Constants.ClimberConstants.ClimberMotors.INVERT)
            .withFeedbackPort(Constants.ClimberConstants.ClimberMotors.FEEDBACK_PORT)
            .withSensorPhase(Constants.ClimberConstants.ClimberMotors.SENSOR_PHASE)
            .withTimeout(Constants.ClimberConstants.ClimberMotors.TIMEOUT)
            .withCurrentLimitEnabled(Constants.ClimberConstants.ClimberMotors.ENABLE_CURRENT_LIMIT)
            .withCurrentLimit(Constants.ClimberConstants.ClimberMotors.CURRENT_LIMIT)
            .withOpenLoopRampRate(Constants.ClimberConstants.ClimberMotors.OPEN_LOOP_RAMP)
            .withNominalOutputForward(Constants.ClimberConstants.ClimberMotors.NOMINAL_OUTPUT_FORWARD)
            .withNominalOutputReverse(Constants.ClimberConstants.ClimberMotors.NOMINAL_OUTPUT_REVERSE)
            .withPeakOutputForward(Constants.ClimberConstants.ClimberMotors.PEAK_OUTPUT_FORWARD)
            .withPeakOutputReverse(Constants.ClimberConstants.ClimberMotors.PEAK_OUTPUT_REVERSE)
        .withMaster(leftWinch).build();

            //addChild("frontSensor", sensor);
            //addChild("indexerStageOne", indexerStageOne);

  }

  /**
   * Update any states
   */
  public void updateState() {
    //SmartDashboard.putNumber("INDEXER BALL INDEX VALUE", GlobalManager.IndexerManager.numBalls);
    //SmartDashboard.putString("INDEXER BALL INDEX ENUM", GlobalManager.IndexerManager.locationState.toString());

  }

  public void spin(double speed) {
    leftWinch.drivePercentOutput(speed);
  }

  /**
   * Runs periodically and updates the state of the Winch
   */
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    updateState();
  }
}
