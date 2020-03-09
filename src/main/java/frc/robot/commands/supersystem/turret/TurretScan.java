/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.supersystem.turret;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.GlobalManager;
import frc.robot.FRCLib.Conversions.EncoderConversionFactors;
import frc.robot.FRCLib.Cyclone.CycloneController;
import frc.robot.subsystems.Turret;

public class TurretScan extends CommandBase {

    /**
     * Turret subsystem
     */
    private Turret turret;

    private CycloneController cyclone;

    /**
     * Creates a new TurretScan.
     */
    public TurretScan(Turret turret, CycloneController cyclone) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.turret = turret;
        this.cyclone = cyclone;
        addRequirements(this.turret);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        /* 
         * What do we want to do when idle? We have multiple options.
         * Stop - Stop the motor and wait until the target is in range
         * Sweep - Sweep back and forth until target is seen
         * Predict - Use Ramsete data to estimate our field-relative position and heading
         * 
         * Whatever we do it may be nice to add a manual 'nudge' for the drivers 
         * in case the target is just slightly out of range
         */


        //Let's try Predict first
        Rotation2d rotation = cyclone.twisterController.getRobotPose().getRotation();
        Translation2d position = cyclone.twisterController.getRobotPose().getTranslation();
        Translation2d goal = new Translation2d(9.13, 2.36);

        double theta_r = Math.toDegrees(Math.atan(
            (goal.getY() - position.getY()) / (goal.getX() - position.getX())
        ));
        double theta_t = theta_r - rotation.getDegrees();

        turret.set(ControlMode.Position, EncoderConversionFactors.CONVERT_ANGLE_TO_MA3_ENCODER_TICKS(theta_t));
        //That wasn't too bad, was it?
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
