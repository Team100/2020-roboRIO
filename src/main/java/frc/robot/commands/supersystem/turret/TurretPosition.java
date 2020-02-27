/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.supersystem.turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.GlobalManager;
import frc.robot.commands.supersystem.turret.camera.Server;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Turret.ActionState;

public class TurretPosition extends CommandBase {
    /**
     * Creates a new TurretTurn.
     */
    public Turret turret;

    /**
     * Position Setpoint in encoder ticks
     */
    private double setpoint;

    public TurretPosition(Turret turret) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.turret = turret;
        addRequirements(this.turret);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        turret.actionState = ActionState.MOVING;

        if(Server.target != null) {
            if(Server.target.getDistance() != -1) {
                GlobalManager.TurretManager.targetAcquired = true;
                double cameraAngle = Server.target.getHAngle();
                double modifiedAngle = (Math.signum(cameraAngle)*Math.pow(Math.abs(cameraAngle), 1.5)/2);
                this.setpoint = (modifiedAngle * Constants.TurretConstants.TurretMotionParameters.TICKS_PER_DEGREE)
                            + turret.tickOffset + turret.turretMotor.getSelectedSensorPosition();
            } else {
            GlobalManager.TurretManager.targetAcquired = false;
            }
        } else {
            setpoint = turret.turretMotor.getSelectedSensorPosition();
            GlobalManager.TurretManager.targetAcquired = false;
        }

        turret.turretMotor.drivePosition(setpoint);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
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
