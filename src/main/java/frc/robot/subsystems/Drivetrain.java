/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.FRCLib.Motors.FRCTalonFX;

public class Drivetrain extends SubsystemBase {

  public FRCTalonFX leftMaster;
  public FRCTalonFX leftFollower;

  public FRCTalonFX rightMaster;
  public FRCTalonFX rightFollower;
  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain() {
    leftMaster = new FRCTalonFX.FRCTalonFXBuilder()
    .withCanID(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainLeftMaster.CAN_ID)
    .withInverted(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainLeftMaster.INVERT)
    .withFeedbackPort(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainLeftMaster.FEEDBACK_PORT)
    .withSensorPhase(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainLeftMaster.SENSOR_PHASE)
    .withTimeout(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainLeftMaster.TIMEOUT)
    .withCurrentLimitEnabled(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainLeftMaster.ENABLE_CURRENT_LIMIT)
    .withCurrentLimit(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainLeftMaster.CURRENT_LIMIT)
    .withOpenLoopRampRate(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainLeftMaster.OPEN_LOOP_RAMP)
    .withNominalOutputForward(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainLeftMaster.NOMINAL_OUTPUT_FORWARD)
    .withNominalOutputReverse(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainLeftMaster.NOMINAL_OUTPUT_REVERSE)
    .withPeakOutputForward(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainLeftMaster.PEAK_OUTPUT_FORWARD)
    .withPeakOutputReverse(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainLeftMaster.PEAK_OUTPUT_REVERSE)
    .withSmartDashboardPutEnabled(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainLeftMaster.ENABLE_DEBUGGING)
    .withSmartDashboardPath(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainLeftMaster.SMART_DASHBOARD_PATH)
    .build();

    leftFollower = new FRCTalonFX.FRCTalonFXBuilder()
    .withCanID(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainLeftFollower.CAN_ID)
    .withInverted(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainLeftFollower.INVERT)
    .withFeedbackPort(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainLeftFollower.FEEDBACK_PORT)
    .withSensorPhase(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainLeftFollower.SENSOR_PHASE)
    .withTimeout(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainLeftFollower.TIMEOUT)
    .withCurrentLimitEnabled(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainLeftFollower.ENABLE_CURRENT_LIMIT)
    .withCurrentLimit(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainLeftFollower.CURRENT_LIMIT)
    .withOpenLoopRampRate(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainLeftFollower.OPEN_LOOP_RAMP)
    .withNominalOutputForward(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainLeftFollower.NOMINAL_OUTPUT_FORWARD)
    .withNominalOutputReverse(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainLeftFollower.NOMINAL_OUTPUT_REVERSE)
    .withPeakOutputForward(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainLeftFollower.PEAK_OUTPUT_FORWARD)
    .withPeakOutputReverse(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainLeftFollower.PEAK_OUTPUT_REVERSE)
    .withSmartDashboardPutEnabled(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainLeftFollower.ENABLE_DEBUGGING)
    .withSmartDashboardPath(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainLeftFollower.SMART_DASHBOARD_PATH)
    .build();

    rightMaster = new FRCTalonFX.FRCTalonFXBuilder()
    .withCanID(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainRightMaster.CAN_ID)
    .withInverted(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainRightMaster.INVERT)
    .withFeedbackPort(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainRightMaster.FEEDBACK_PORT)
    .withSensorPhase(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainRightMaster.SENSOR_PHASE)
    .withTimeout(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainRightMaster.TIMEOUT)
    .withCurrentLimitEnabled(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainRightMaster.ENABLE_CURRENT_LIMIT)
    .withCurrentLimit(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainRightMaster.CURRENT_LIMIT)
    .withOpenLoopRampRate(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainRightMaster.OPEN_LOOP_RAMP)
    .withNominalOutputForward(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainRightMaster.NOMINAL_OUTPUT_FORWARD)
    .withNominalOutputReverse(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainRightMaster.NOMINAL_OUTPUT_REVERSE)
    .withPeakOutputForward(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainRightMaster.PEAK_OUTPUT_FORWARD)
    .withPeakOutputReverse(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainRightMaster.PEAK_OUTPUT_REVERSE)
    .withSmartDashboardPutEnabled(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainRightMaster.ENABLE_DEBUGGING)
    .withSmartDashboardPath(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainRightMaster.SMART_DASHBOARD_PATH)
    .build();

    rightFollower = new FRCTalonFX.FRCTalonFXBuilder()
    .withCanID(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainRightFollower.CAN_ID)
    .withInverted(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainRightFollower.INVERT)
    .withFeedbackPort(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainRightFollower.FEEDBACK_PORT)
    .withSensorPhase(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainRightFollower.SENSOR_PHASE)
    .withTimeout(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainRightFollower.TIMEOUT)
    .withCurrentLimitEnabled(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainRightFollower.ENABLE_CURRENT_LIMIT)
    .withCurrentLimit(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainRightFollower.CURRENT_LIMIT)
    .withOpenLoopRampRate(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainRightFollower.OPEN_LOOP_RAMP)
    .withNominalOutputForward(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainRightFollower.NOMINAL_OUTPUT_FORWARD)
    .withNominalOutputReverse(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainRightFollower.NOMINAL_OUTPUT_REVERSE)
    .withPeakOutputForward(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainRightFollower.PEAK_OUTPUT_FORWARD)
    .withPeakOutputReverse(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainRightFollower.PEAK_OUTPUT_REVERSE)
    .withSmartDashboardPutEnabled(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainRightFollower.ENABLE_DEBUGGING)
    .withSmartDashboardPath(Constants.DrivetrainConstants.DrivetrainMotors.DrivetrainRightFollower.SMART_DASHBOARD_PATH)
    .build();

    leftFollower.follow(leftMaster);
    rightFollower.follow(rightMaster);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
