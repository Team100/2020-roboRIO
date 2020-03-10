/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.supersystem.turret;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.GlobalManager;
import frc.robot.RobotContainer;
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

    /**
     * Creates a new TurretSlew.
     */
    public TurretSlew(Turret turret) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.turret = turret;
        addRequirements(this.turret);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        turret.actionState = ActionState.MOVING;
        double cameraAngle = 0; //RobotContainer.mockHAngle(); //Server.target.getHAngle();
        this.setpoint = EncoderConversionFactors.CONVERT_ANGLE_TO_MA3_ENCODER_TICKS(cameraAngle) +
                        turret.getMotor().getSelectedSensorPosition();

        turret.set(ControlMode.MotionMagic, setpoint);
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
