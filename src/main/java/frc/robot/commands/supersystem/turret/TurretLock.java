/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.supersystem.turret;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.commands.supersystem.turret.camera.Server;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Turret.ActionState;

public class TurretLock extends CommandBase {
    /**
     * Turret subsystem
     */
    public Turret turret;

    /**
     * Position Setpoint in encoder ticks
     */
    private double setpoint;

    public TurretLock(Turret turret) {
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
            double cameraAngle = new Joystick(0).getDirectionDegrees(); //Server.target.getHAngle();
            // double modifiedAngle = (Math.signum(cameraAngle) * 
            //                         Math.pow(Math.abs(cameraAngle), 1.5) / 
            //                         2);

            this.setpoint = (cameraAngle * 
                            Constants.TurretConstants.TurretMotionParameters.TICKS_PER_DEGREE) +
                            turret.tickOffset + 
                            turret.turretMotor.getSelectedSensorPosition();

        turret.turretMotor.drivePosition(setpoint);
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
