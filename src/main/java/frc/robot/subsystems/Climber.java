/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.NeutralMode;
import frc.robot.FRCLib.Motors.FRCTalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import frc.robot.Constants;
import frc.robot.Constants.ClimberConstants.LeftWinch;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public FRCTalonSRX LeftWinch;
  public FRCTalonSRX RightWinch;

  public Climber() {
    LeftWinch = new FRCTalonSRX.FRCTalonSRXBuilder(Constants.ClimberConstants.LeftWinch.CAN_ID)
    .withInverted(Constants.ClimberConstants.LeftWinch.INVERT)
    .withFeedbackPort(Constants.ClimberConstants.LeftWinch.FEEDBACK_PORT)
    .withSensorPhase(Constants.ClimberConstants.LeftWinch.SENSOR_PHASE)
    .withTimeout(Constants.ClimberConstants.LeftWinch.TIMEOUT)
    .withCurrentLimitEnabled(Constants.ClimberConstants.LeftWinch.ENABLE_CURRENT_LIMIT)
    .withCurrentLimit(Constants.ClimberConstants.LeftWinch.CURRENT_LIMIT)
    .withOpenLoopRampRate(Constants.ClimberConstants.LeftWinch.OPEN_LOOP_RAMP)
    .withNominalOutputForward(Constants.ClimberConstants.LeftWinch.NOMINAL_OUTPUT_FORWARD)
    .withNominalOutputReverse(Constants.ClimberConstants.LeftWinch.NOMINAL_OUTPUT_REVERSE)
    .withPeakOutputForward(Constants.ClimberConstants.LeftWinch.PEAK_OUTPUT_FORWARD)
    .withPeakOutputReverse(Constants.ClimberConstants.LeftWinch.PEAK_OUTPUT_REVERSE).build();

    RightWinch = new FRCTalonSRX.FRCTalonSRXBuilder(Constants.ClimberConstants.RightWinch.CAN_ID)
    .withInverted(Constants.ClimberConstants.RightWinch.INVERT)
    .withFeedbackPort(Constants.ClimberConstants.RightWinch.FEEDBACK_PORT)
    .withSensorPhase(Constants.ClimberConstants.RightWinch.SENSOR_PHASE)
    .withTimeout(Constants.ClimberConstants.RightWinch.TIMEOUT)
    .withCurrentLimitEnabled(Constants.ClimberConstants.RightWinch.ENABLE_CURRENT_LIMIT)
    .withCurrentLimit(Constants.ClimberConstants.RightWinch.CURRENT_LIMIT)
    .withOpenLoopRampRate(Constants.ClimberConstants.RightWinch.OPEN_LOOP_RAMP)
    .withNominalOutputForward(Constants.ClimberConstants.RightWinch.NOMINAL_OUTPUT_FORWARD)
    .withNominalOutputReverse(Constants.ClimberConstants.RightWinch.NOMINAL_OUTPUT_REVERSE)
    .withPeakOutputForward(Constants.ClimberConstants.RightWinch.PEAK_OUTPUT_FORWARD)
    .withPeakOutputReverse(Constants.ClimberConstants.RightWinch.PEAK_OUTPUT_REVERSE).build();

    LeftWinch.setNeutralMode(NeutralMode.Brake);
    RightWinch.setNeutralMode(NeutralMode.Brake);

    addChild("Left Winch", LeftWinch);
    addChild("Right Winch", RightWinch);

  }

  public void initDefaultCommand() {

  }

  public void spin(double speed) {
    LeftWinch.drivePercentOutput(speed);
    RightWinch.drivePercentOutput(speed);
  }

  public void periodic() {
    }

  public void teleopInit() {

  }
}