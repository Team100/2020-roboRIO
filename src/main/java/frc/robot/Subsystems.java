/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.subsystems.*;

/**
 * A subsystem cluster for easy initialization and distribution
 */
public class Subsystems {
    public final Drivetrain drivetrain = new Drivetrain();
    public final IndexerStageOne stageOne = new IndexerStageOne();
    public final IndexerStageTwo stageTwo = new IndexerStageTwo();
    public final Intake intake = new Intake();
    public final IntakePivot intakePivot = new IntakePivot();
    public final Shooter shooter = new Shooter();
    public final Turret turret = new Turret();
    public final PDP pdpSubsystem = new PDP();
}
