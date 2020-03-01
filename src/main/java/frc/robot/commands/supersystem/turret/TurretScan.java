/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.supersystem.turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turret;

public class TurretScan extends CommandBase {

    /**
     * Turret subsystem
     */
    private Turret turret;

    /**
     * Creates a new TurretScan.
     */
    public TurretScan(Turret turret) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.turret = turret;
        addRequirements(this.turret);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        turret.getMotor().drivePercentOutput(0);
        //wait
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
         */
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
