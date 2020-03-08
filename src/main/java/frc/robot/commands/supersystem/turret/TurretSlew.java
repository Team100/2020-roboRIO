/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.supersystem.turret;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.FRCLib.Conversions.EncoderConversionFactors;
import frc.robot.commands.supersystem.turret.camera.Server;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Turret.ActionState;

public class TurretSlew extends CommandBase {

    /**
     * Turret subsystem
     */
    private Turret turret;

    /**
     * Position Setpoint in encoder ticks
     */
    private double setpoint;

    private Joystick j;

    /**
     * Creates a new TurretSlew.
     */
    public TurretSlew(Turret turret, Joystick j) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.turret = turret;
        this.j = j;
        addRequirements(this.turret);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        turret.turretMotor.motor.setSelectedSensorPosition(0);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        turret.actionState = ActionState.MOVING;
        double cameraAngle = this.j.getDirectionDegrees(); //Server.target.getHAngle();
        this.setpoint = EncoderConversionFactors.CONVERT_ANGLE_TO_MA3_ENCODER_TICKS(cameraAngle) +
                        turret.tickOffset + 
                        turret.turretMotor.getSelectedSensorPosition();

        turret.turretMotor.driveMotionMagic(setpoint);
        
        SmartDashboard.putNumber("Joystick degrees", this.j.getDirectionDegrees());
        SmartDashboard.putNumber("motor target", turret.turretMotor.motor.getClosedLoopTarget());
        SmartDashboard.putNumber("motor voltage", turret.turretMotor.motor.getMotorOutputVoltage());
        SmartDashboard.putNumber("motor setpoint", this.setpoint);
        SmartDashboard.putString("CurrentCommand", turret.getCurrentCommand().getName());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
