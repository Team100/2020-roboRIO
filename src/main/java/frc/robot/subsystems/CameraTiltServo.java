/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class CameraTiltServo extends SubsystemBase {
  private Servo arm;
  private int setpoint;
  
  /**
   * Creates a new CameraTiltServo.
   */
  public CameraTiltServo() {
    setpoint = Constants.DriverCameraConstants.CameraTiltServo.SETPOINT_ONE;
    arm = new Servo(Constants.DriverCameraConstants.CameraTiltServo.PWM_ID_SERVO);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    arm.setAngle(setpoint);
  }

  public void setpointOne() {
    setpoint = Constants.DriverCameraConstants.CameraTiltServo.SETPOINT_ONE;
  }

  public void setpointTwo() {
    setpoint = Constants.DriverCameraConstants.CameraTiltServo.SETPOINT_TWO;
  }
}