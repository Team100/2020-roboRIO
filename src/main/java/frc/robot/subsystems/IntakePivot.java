/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.FRCLib.Motors.FRCTalonSRX;

public class IntakePivot extends SubsystemBase {
  public FRCTalonSRX pivot;

  public static enum LocationState {
    MOVING, STATIONARY
  }

  public LocationState locationState;

  public static enum ValidAngles {
    DOWN, UP, UNCERTAIN
  }

  public ValidAngles currentAngle;

  /**
   * Creates a new IntakePivot.
   */
  public IntakePivot() {
    pivot = new FRCTalonSRX.FRCTalonSRXBuilder(Constants.IntakeConstants.IntakeMotors.IntakePivot.CAN_ID)
        .withInverted(Constants.IntakeConstants.IntakeMotors.IntakePivot.INVERT)
        .withFeedbackPort(Constants.IntakeConstants.IntakeMotors.IntakePivot.FEEDBACK_PORT)
        .withSensorPhase(Constants.IntakeConstants.IntakeMotors.IntakePivot.SENSOR_PHASE)
        .withTimeout(Constants.IntakeConstants.IntakeMotors.IntakePivot.TIMEOUT)
        .withCurrentLimitEnabled(Constants.IntakeConstants.IntakeMotors.IntakePivot.ENABLE_CURRENT_LIMIT)
        .withCurrentLimit(Constants.IntakeConstants.IntakeMotors.IntakePivot.CURRENT_LIMIT)
        .withOpenLoopRampRate(Constants.IntakeConstants.IntakeMotors.IntakePivot.OPEN_LOOP_RAMP)
        .withNominalOutputForward(Constants.IntakeConstants.IntakeMotors.IntakePivot.NOMINAL_OUTPUT_FORWARD)
        .withNominalOutputReverse(Constants.IntakeConstants.IntakeMotors.IntakePivot.NOMINAL_OUTPUT_REVERSE)
        .withPeakOutputForward(Constants.IntakeConstants.IntakeMotors.IntakePivot.PEAK_OUTPUT_FORWARD)
        .withPeakOutputReverse(Constants.IntakeConstants.IntakeMotors.IntakePivot.PEAK_OUTPUT_REVERSE)
        .withKP(Constants.IntakeConstants.IntakeMotionParameters.KP)
        .withKI(Constants.IntakeConstants.IntakeMotionParameters.KI)
        .withKD(Constants.IntakeConstants.IntakeMotionParameters.KD)
        .withKF(Constants.IntakeConstants.IntakeMotionParameters.KF)
        .build();


        pivot.motor.configAllowableClosedloopError(0, 2, 10);


        pivot.motor.configSelectedFeedbackSensor(FeedbackDevice.Analog);
        pivot.motor.setSelectedSensorPosition(pivot.getRawAnalogSensor());
        addChild("intakePivot", pivot);
      
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Raw Pivot", pivot.getSelectedSensorPosition());
  }
}
