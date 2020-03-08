/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.FRCLib.AutoHelperFunctions.AutonConversionFactors;
import frc.robot.FRCLib.Cyclone.twister.TwisterController;
import frc.robot.FRCLib.Motors.FRCTalonFX;

public class Drivetrain extends SubsystemBase {

  private FRCTalonFX leftMaster, leftFollower, rightMaster, rightFollower;

  //public AHRS ahrs;

  public ADXRS450_Gyro gyro;

  public DifferentialDriveOdometry odometry;


  /**
   * Creates a new ExampleSubsystem.
   */
  public Drivetrain() {

    //ahrs = new AHRS(Constants.DrivetrainConstants.NAVX_PORT);
    
    gyro = new ADXRS450_Gyro();
    leftMaster = new FRCTalonFX.FRCTalonFXBuilder(Constants.DrivetrainConstants.DrivetrainMotors.LeftMaster.CAN_ID)
        .withKP(Constants.DrivetrainConstants.DrivetrainMotors.LeftMaster.KP)
        .withKI(Constants.DrivetrainConstants.DrivetrainMotors.LeftMaster.KI)
        .withKD(Constants.DrivetrainConstants.DrivetrainMotors.LeftMaster.KD)
        .withKF(Constants.DrivetrainConstants.DrivetrainMotors.LeftMaster.KF)
        .withInverted(Constants.DrivetrainConstants.DrivetrainMotors.LeftMaster.INVERTED)
        .withSensorPhase(Constants.DrivetrainConstants.DrivetrainMotors.LeftMaster.SENSOR_PHASE)
        .withPeakOutputForward(Constants.DrivetrainConstants.DrivetrainMotors.LeftMaster.PEAK_OUTPUT_FORWARD)
        .withPeakOutputReverse(Constants.DrivetrainConstants.DrivetrainMotors.LeftMaster.PEAK_OUTPUT_REVERSE)
        .withNeutralMode(Constants.DrivetrainConstants.DrivetrainMotors.LeftMaster.NEUTRAL_MODE).build();

    leftFollower = new FRCTalonFX.FRCTalonFXBuilder(Constants.DrivetrainConstants.DrivetrainMotors.LeftFollower.CAN_ID)
        .withKP(Constants.DrivetrainConstants.DrivetrainMotors.LeftFollower.KP)
        .withKI(Constants.DrivetrainConstants.DrivetrainMotors.LeftFollower.KI)
        .withKD(Constants.DrivetrainConstants.DrivetrainMotors.LeftFollower.KD)
        .withKF(Constants.DrivetrainConstants.DrivetrainMotors.LeftFollower.KF)
        .withInverted(Constants.DrivetrainConstants.DrivetrainMotors.LeftFollower.INVERTED)
        .withSensorPhase(Constants.DrivetrainConstants.DrivetrainMotors.LeftFollower.SENSOR_PHASE)

        .withPeakOutputForward(Constants.DrivetrainConstants.DrivetrainMotors.LeftFollower.PEAK_OUTPUT_FORWARD)
        .withPeakOutputReverse(Constants.DrivetrainConstants.DrivetrainMotors.LeftFollower.PEAK_OUTPUT_REVERSE)
        .withNeutralMode(Constants.DrivetrainConstants.DrivetrainMotors.LeftFollower.NEUTRAL_MODE)
        .withMaster(leftMaster).build();

    rightMaster = new FRCTalonFX.FRCTalonFXBuilder(Constants.DrivetrainConstants.DrivetrainMotors.RightMaster.CAN_ID)
        .withKP(Constants.DrivetrainConstants.DrivetrainMotors.RightMaster.KP)
        .withKI(Constants.DrivetrainConstants.DrivetrainMotors.RightMaster.KI)
        .withKD(Constants.DrivetrainConstants.DrivetrainMotors.RightMaster.KD)
        .withKF(Constants.DrivetrainConstants.DrivetrainMotors.RightMaster.KF)
        .withInverted(Constants.DrivetrainConstants.DrivetrainMotors.RightMaster.INVERTED)
        .withSensorPhase(Constants.DrivetrainConstants.DrivetrainMotors.RightMaster.SENSOR_PHASE)
        .withPeakOutputForward(Constants.DrivetrainConstants.DrivetrainMotors.RightMaster.PEAK_OUTPUT_FORWARD)
        .withPeakOutputReverse(Constants.DrivetrainConstants.DrivetrainMotors.RightMaster.PEAK_OUTPUT_REVERSE)
        .withNeutralMode(Constants.DrivetrainConstants.DrivetrainMotors.RightMaster.NEUTRAL_MODE).build();

    rightFollower = new FRCTalonFX.FRCTalonFXBuilder(
        Constants.DrivetrainConstants.DrivetrainMotors.RightFollower.CAN_ID)
            .withKP(Constants.DrivetrainConstants.DrivetrainMotors.RightFollower.KP)
            .withKI(Constants.DrivetrainConstants.DrivetrainMotors.RightFollower.KI)
            .withKD(Constants.DrivetrainConstants.DrivetrainMotors.RightFollower.KD)
            .withKF(Constants.DrivetrainConstants.DrivetrainMotors.RightFollower.KF)
            .withInverted(Constants.DrivetrainConstants.DrivetrainMotors.RightFollower.INVERTED)
            .withSensorPhase(Constants.DrivetrainConstants.DrivetrainMotors.RightFollower.SENSOR_PHASE)
            .withPeakOutputForward(Constants.DrivetrainConstants.DrivetrainMotors.RightFollower.PEAK_OUTPUT_FORWARD)
            .withPeakOutputReverse(Constants.DrivetrainConstants.DrivetrainMotors.RightFollower.PEAK_OUTPUT_REVERSE)
            .withNeutralMode(Constants.DrivetrainConstants.DrivetrainMotors.RightFollower.NEUTRAL_MODE)
            .withMaster(rightMaster).build();
    
    odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));

    addChild("drivetrainLeftMaster", leftMaster);
    addChild("drivetrainRightMaster", rightMaster);
    addChild("drivetrainLeftFollower", leftFollower);
    addChild("drivetrainRightFollower", rightFollower);
    addChild("driveGyro", gyro);

  }

  public void set(double left, double right) {
    this.tankDrivePercentOutput(left, right);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    double leftLeaderDistance = AutonConversionFactors.convertTalonEncoderTicksToMeters(leftMaster.getSelectedSensorPosition(), Constants.DrivetrainConstants.DrivetrainParameters.WHEEL_DIAMETER, Constants.DrivetrainConstants.DrivetrainParameters.TICKS_PER_REV, Constants.DrivetrainConstants.DrivetrainParameters.GEARING_RATIO);
    double rightLeaderDistance = AutonConversionFactors.convertTalonEncoderTicksToMeters(rightMaster.getSelectedSensorPosition(), Constants.DrivetrainConstants.DrivetrainParameters.WHEEL_DIAMETER, Constants.DrivetrainConstants.DrivetrainParameters.TICKS_PER_REV, Constants.DrivetrainConstants.DrivetrainParameters.GEARING_RATIO);
    SmartDashboard.putNumber("Current Compass",Rotation2d.fromDegrees(getHeading()).getRadians());
    odometry.update(Rotation2d.fromDegrees(getHeading()), leftLeaderDistance, rightLeaderDistance);
    SmartDashboard.putNumber("Left Sensor Velocity",this.leftMaster.getSensorVelocity());
    SmartDashboard.putNumber("Right Sensor Velocity", this.rightMaster.getSensorVelocity());
    SmartDashboard.putString("Left Control Mode", this.leftMaster.motor.getControlMode().toString());
    SmartDashboard.putNumber("Left Sensor Position", this.leftMaster.getSelectedSensorPosition());




  }

  public Pose2d getPose() {
    return odometry.getPoseMeters();
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(
        AutonConversionFactors.convertTalonSRXNativeUnitsToWPILibTrajecoryUnits(this.leftMaster.getSensorVelocity(),
        Constants.DrivetrainConstants.DrivetrainParameters.TICKS_PER_REV,
            Constants.DrivetrainConstants.DrivetrainParameters.WHEEL_DIAMETER, Constants.DrivetrainConstants.DrivetrainParameters.GEARING_RATIO),
        AutonConversionFactors.convertTalonSRXNativeUnitsToWPILibTrajecoryUnits(this.rightMaster.getSensorVelocity(),
        Constants.DrivetrainConstants.DrivetrainParameters.TICKS_PER_REV,
            Constants.DrivetrainConstants.DrivetrainParameters.WHEEL_DIAMETER, Constants.DrivetrainConstants.DrivetrainParameters.GEARING_RATIO));
  }

  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    odometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
  }

  public void tankDrivePercentOutput(double leftPO, double rightPO) {
    this.leftMaster.drivePercentOutput(leftPO);
    this.rightMaster.drivePercentOutput(rightPO);
  }

  public void tankDriveVelocity(double leftVel, double rightVel) {

    double leftLeaderNativeVelocity = AutonConversionFactors.convertWPILibUnitsToTalonSRXNativeUnits(leftVel, Constants.DrivetrainConstants.DrivetrainParameters.TICKS_PER_REV,
        Constants.DrivetrainConstants.DrivetrainParameters.WHEEL_DIAMETER, Constants.DrivetrainConstants.DrivetrainParameters.GEARING_RATIO
        );
    double rightLeaderNativeVelocity = AutonConversionFactors.convertWPILibUnitsToTalonSRXNativeUnits(rightVel, Constants.DrivetrainConstants.DrivetrainParameters.TICKS_PER_REV,
    Constants.DrivetrainConstants.DrivetrainParameters.WHEEL_DIAMETER, Constants.DrivetrainConstants.DrivetrainParameters.GEARING_RATIO);
      
    this.leftMaster.driveVelocity(leftLeaderNativeVelocity);
    this.rightMaster.driveVelocity(rightLeaderNativeVelocity);

    if (Constants.DrivetrainConstants.DEBUG) {
      SmartDashboard.putNumber("LeftIntentedVelocity", leftLeaderNativeVelocity);
      SmartDashboard.putNumber("RightIntentedVelocity", rightLeaderNativeVelocity);

      SmartDashboard.putNumber("LeftIntendedVsActual", leftLeaderNativeVelocity - this.leftMaster.getSensorVelocity());
      SmartDashboard.putNumber("Left Setpoint", this.leftMaster.motor.getClosedLoopTarget());

    }

  }

  public void resetEncoders() {
    leftMaster.setSensorPosition(0);
    rightMaster.setSensorPosition(0);
  }

  public double getAverageEncoderDistance() {
    return (leftMaster.getSelectedSensorPosition() + rightMaster.getSelectedSensorPosition()) / 2.0;
  }

  public void zeroHeading() {
    //ahrs.zeroYaw();
    gyro.reset();
  }

  public double getHeading() {
    return -1 * Math.IEEEremainder(gyro.getAngle(), 360);
    //return -1 * Math.IEEEremainder(ahrs.getFusedHeading(), 360);
    
  }

  public double getTurnRate() {
    //return ahrs.getRate();
    return gyro.getRate();
  }
}
