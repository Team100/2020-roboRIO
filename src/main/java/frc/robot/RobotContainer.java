/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.List;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import frc.robot.FRCLib.AutoHelperFunctions.PathGenerator;

import frc.robot.commands.drivetrain.ArcadeDrive;
import frc.robot.commands.supersystem.indexer.IndexerDriveBackward;
import frc.robot.commands.supersystem.indexer.IndexerDriveForward;
import frc.robot.commands.supersystem.indexer.indexStageOne.IndexerStageOneDriveForward;
import frc.robot.commands.supersystem.indexer.indexStageOne.IndexerStageOneStop;
import frc.robot.commands.supersystem.indexer.indexStageTwo.IndexerStageTwoDriveForward;
import frc.robot.commands.supersystem.indexer.indexStageTwo.IndexerStageTwoStop;
import frc.robot.commands.supersystem.intake.IntakeIntake;
import frc.robot.commands.supersystem.intake.IntakeStop;
import frc.robot.commands.supersystem.intake.intakePivot.IntakeMoveJoystick;
import frc.robot.commands.supersystem.shooter.ShooterRun;
import frc.robot.commands.supersystem.shooter.ShooterStop;
import frc.robot.commands.supersystem.turret.TurretStop;
import frc.robot.subsystems.*;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.PDP;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final Drivetrain drivetrain;
    private final IndexerStageOne stageOne;
    private final IndexerStageTwo stageTwo;
    private final Intake intake;
    private final IntakePivot intakePivot;
    private final Shooter shooter;
    private final Turret turret;
    private final Joystick leftJoystick;
    private final Joystick rightJoystick;
    private final Joystick gamepad;
    private final PDP pdpSubsystem;

    public JoystickButton wholeIndexerForward;
    public JoystickButton wholeIndexerReverse;
    public JoystickButton indexerStageOneForward;
    public JoystickButton indexerStageTwoForward;

    public JoystickButton intakeIntake;
    public JoystickButton shooterShoot;

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // OI Initiation
        leftJoystick = new Joystick(0);
        rightJoystick = new Joystick(1);
        gamepad = new Joystick(2);

        // Subsystem Initiation
        drivetrain = new Drivetrain();
        stageOne = new IndexerStageOne();
        stageTwo = new IndexerStageTwo();
        intake = new Intake();
        intakePivot = new IntakePivot();
        shooter = new Shooter();
        turret = new Turret();
        pdpSubsystem = new PDP();

        // Default Commands
        this.setDefaultCommands();


        // Button to Command Mapping
        configureButtonBindings();
    }

    public void setDefaultCommands() {
        drivetrain.setDefaultCommand(new ArcadeDrive(drivetrain, leftJoystick, rightJoystick));
        stageOne.setDefaultCommand(new IndexerStageOneStop(stageOne));
        stageTwo.setDefaultCommand(new IndexerStageTwoStop(stageTwo));
        intake.setDefaultCommand(new IntakeStop(intake));
        intakePivot.setDefaultCommand(new IntakeMoveJoystick(intakePivot, gamepad));
        shooter.setDefaultCommand(new ShooterStop(shooter));
        turret.setDefaultCommand(new TurretStop(turret));
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by instantiating a {@link GenericHID} or one of its subclasses
     * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
     * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        wholeIndexerForward = new JoystickButton(gamepad, 1);
        wholeIndexerReverse = new JoystickButton(gamepad, 3);
        indexerStageOneForward = new JoystickButton(gamepad, 2);
        indexerStageTwoForward = new JoystickButton(gamepad, 4);

        wholeIndexerForward.whileHeld(new IndexerDriveForward(stageOne, stageTwo));
        wholeIndexerReverse.whileHeld(new IndexerDriveBackward(stageOne, stageTwo));
        indexerStageOneForward.whileHeld(new IndexerStageOneDriveForward(stageOne));
        indexerStageTwoForward.whileHeld(new IndexerStageTwoDriveForward(stageTwo));

        ////////////////////////////////////////////////////////////////////////////
        intakeIntake = new JoystickButton(gamepad, 5);
        intakeIntake.whileHeld(new IntakeIntake(intake));

        ////////////////////////////////////////////////////////////////////////////
        shooterShoot = new JoystickButton(gamepad, 6);
        shooterShoot.whileHeld(new ShooterRun(shooter));

    }


    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        drivetrain.zeroHeading();
        drivetrain.resetOdometry(new Pose2d(0,0, new Rotation2d(0)));
        Pose2d start = new Pose2d(0, 0, new Rotation2d(0));
        List<Translation2d> waypoints = List.of(
            new Translation2d(1.5, -1)
        );
        Pose2d end = new Pose2d(3, 0, new Rotation2d(0));

        return PathGenerator.createAutoNavigationCommand(drivetrain, start, waypoints, end);
    }

    
}

