/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.FRCLib.Motors.FRCTalonFX;

public class Drivetrain extends SubsystemBase {
    private FRCTalonFX leftMaster, leftFollower, rightMaster, rightFollower;
    private double leftOutput, rightOutput;

    /**
     * Creates a new ExampleSubsystem.
     */
    public Drivetrain() {
        leftMaster = new FRCTalonFX.FRCTalonFXBuilder(Constants.DrivetrainConstants.DrivetrainMotors.LeftMaster.CAN_ID)
        .withPeakOutputForward(Constants.DrivetrainConstants.DrivetrainMotors.LeftMaster.PEAK_OUTPUT_FORWARD)
        .withPeakOutputReverse(Constants.DrivetrainConstants.DrivetrainMotors.LeftMaster.PEAK_OUTPUT_REVERSE)
        .withNeutralMode(Constants.DrivetrainConstants.DrivetrainMotors.LeftMaster.NEUTRAL_MODE)
        .build();
        leftFollower = new FRCTalonFX.FRCTalonFXBuilder(Constants.DrivetrainConstants.DrivetrainMotors.LeftFollower.CAN_ID)
        .withPeakOutputForward(Constants.DrivetrainConstants.DrivetrainMotors.LeftFollower.PEAK_OUTPUT_FORWARD)
        .withPeakOutputReverse(Constants.DrivetrainConstants.DrivetrainMotors.LeftFollower.PEAK_OUTPUT_REVERSE)
        .withNeutralMode(Constants.DrivetrainConstants.DrivetrainMotors.LeftFollower.NEUTRAL_MODE)
        .build();
        rightMaster = new FRCTalonFX.FRCTalonFXBuilder(Constants.DrivetrainConstants.DrivetrainMotors.RightMaster.CAN_ID)
        .withPeakOutputForward(Constants.DrivetrainConstants.DrivetrainMotors.RightMaster.PEAK_OUTPUT_FORWARD)
        .withPeakOutputReverse(Constants.DrivetrainConstants.DrivetrainMotors.RightMaster.PEAK_OUTPUT_REVERSE)
        .withNeutralMode(Constants.DrivetrainConstants.DrivetrainMotors.RightMaster.NEUTRAL_MODE)
        .build();
        rightFollower = new FRCTalonFX.FRCTalonFXBuilder(Constants.DrivetrainConstants.DrivetrainMotors.RightFollower.CAN_ID)
        .withPeakOutputForward(Constants.DrivetrainConstants.DrivetrainMotors.RightFollower.PEAK_OUTPUT_FORWARD)
        .withPeakOutputReverse(Constants.DrivetrainConstants.DrivetrainMotors.RightFollower.PEAK_OUTPUT_REVERSE)
        .withNeutralMode(Constants.DrivetrainConstants.DrivetrainMotors.RightFollower.NEUTRAL_MODE)
        .build();

        leftFollower.motor.follow(leftMaster.motor);
        rightFollower.motor.follow(rightMaster.motor);
    }

    public void set(double left, double right) {
        leftOutput = left;
        rightOutput = right;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        leftMaster.drivePercentOutput(leftOutput*Constants.DrivetrainConstants.DrivetrainParameters.MAX_OUTPUT);
        rightMaster.drivePercentOutput(rightOutput*Constants.DrivetrainConstants.DrivetrainParameters.MAX_OUTPUT);
    }
}
