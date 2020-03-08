/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.GlobalManager;
import frc.robot.FRCLib.Motors.FRCTalonSRX;
import frc.robot.commands.supersystem.turret.camera.Server;

public class Turret extends SubsystemBase {

    public FRCTalonSRX turretMotor;

    public static enum ActionState {
        MOVING, STOPPED
    }

    public ActionState actionState;

    public int tickOffset = 0;

    /**
     * Creates a new Turret.
     */
    public Turret() {
        turretMotor = new FRCTalonSRX.FRCTalonSRXBuilder(Constants.TurretConstants.TurretMotors.TurretMotor.CAN_ID)
            .withInverted(Constants.TurretConstants.TurretMotors.TurretMotor.INVERT)
            .withFeedbackPort(Constants.TurretConstants.TurretMotors.TurretMotor.FEEDBACK_PORT)
            .withSensorPhase(Constants.TurretConstants.TurretMotors.TurretMotor.SENSOR_PHASE)
            .withTimeout(Constants.TurretConstants.TurretMotors.TurretMotor.TIMEOUT)
            .withCurrentLimitEnabled(Constants.TurretConstants.TurretMotors.TurretMotor.ENABLE_CURRENT_LIMIT)
            .withCurrentLimit(Constants.TurretConstants.TurretMotors.TurretMotor.CURRENT_LIMIT)
            .withOpenLoopRampRate(Constants.TurretConstants.TurretMotors.TurretMotor.OPEN_LOOP_RAMP)
            .withNominalOutputForward(Constants.TurretConstants.TurretMotors.TurretMotor.NOMINAL_OUTPUT_FORWARD)
            .withNominalOutputReverse(Constants.TurretConstants.TurretMotors.TurretMotor.NOMINAL_OUTPUT_REVERSE)
            .withPeakOutputForward(Constants.TurretConstants.TurretMotors.TurretMotor.PEAK_OUTPUT_FORWARD)
            .withPeakOutputReverse(Constants.TurretConstants.TurretMotors.TurretMotor.PEAK_OUTPUT_REVERSE)
            .withKP(0).withKI(0).withKD(0).withKF(0)
            .withMotionAcceleration(1024).withMotionCruiseVelocity(1024).build();
            turretMotor.motor.configSelectedFeedbackSensor(FeedbackDevice.Analog);

            addChild("turretMotor", turretMotor);
    }

    /**
     * Update any states
     */
    public void updateState() {
        if(Server.target != null) GlobalManager.TurretManager.targetAcquired = Server.target.getDistance() != -1;
        else GlobalManager.TurretManager.targetAcquired = false;
    }

    public FRCTalonSRX getMotor() {
        return this.turretMotor;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        updateState();
    }
}
