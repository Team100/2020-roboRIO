/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import frc.robot.FRCLib.Cyclone.CycloneController;
import frc.robot.FRCLib.Cyclone.twister.TwisterPathLibrary;
import frc.robot.commands.drivetrain.*;
import frc.robot.commands.supersystem.indexer.*;
import frc.robot.commands.colorSpinner.*;
import frc.robot.commands.driverCamera.CameraSetpointOne;
import frc.robot.commands.driverCamera.CameraSetpointTwo;
import frc.robot.commands.supersystem.indexer.indexStageOne.*;
import frc.robot.commands.supersystem.indexer.indexStageTwo.*;
import frc.robot.commands.supersystem.intake.*;
import frc.robot.commands.supersystem.intake.intakePivot.*;
import frc.robot.commands.supersystem.shooter.*;
import frc.robot.commands.supersystem.turret.*;

import frc.robot.Subsystems;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...

    public final Joystick leftJoystick;
    public final Joystick rightJoystick;
    public final Joystick gamepad;


    public JoystickButton wholeIndexerForward;
    public JoystickButton wholeIndexerReverse;
    public JoystickButton indexerStageOneForward;
    public JoystickButton indexerStageTwoForward;

    public JoystickButton intakeIntake;
    public JoystickButton shooterShoot;

    public JoystickButton spinnerRise;
    public JoystickButton spinnerFall;
    public JoystickButton spinnerThreeTimes;

    public JoystickButton cameraSetpointOne;
    public JoystickButton cameraSetpointTwo;

    public JoystickButton dashLeft;
    public JoystickButton dashRight;
    public JoystickButton zeroPose;

    public CycloneController cyclone;

    

    public Triggers triggers;
    public Subsystems subsystems;

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // OI Initiation
        leftJoystick = new Joystick(0);
        rightJoystick = new Joystick(1);
        gamepad = new Joystick(2);

        // Subsystem Initiation

        subsystems = new Subsystems();

        cyclone = new CycloneController(subsystems);


        // Default Commands
        this.setDefaultCommands();


        // Button to Command Mapping
        configureButtonBindings();

        //Trigger Initialization
        triggers = new Triggers(subsystems, this);
    }

    public void setDefaultCommands() {

        subsystems.drivetrain.setDefaultCommand(new ArcadeDrive(subsystems.drivetrain, leftJoystick, rightJoystick));
        subsystems.stageOne.setDefaultCommand(new IndexerStageOneStop(subsystems.stageOne, true));
        subsystems.stageTwo.setDefaultCommand(new IndexerStageTwoStop(subsystems.stageTwo, true));
        subsystems.intake.setDefaultCommand(new IntakeStop(subsystems.intake));
        subsystems.intakePivot.setDefaultCommand(new IntakeMoveJoystick(subsystems.intakePivot, gamepad));
        subsystems.shooter.setDefaultCommand(new ShooterStop(subsystems.shooter));
        subsystems.spinner.setDefaultCommand(new StopSpinnerWheel(subsystems.spinner));
        subsystems.tiltServo.setDefaultCommand(new CameraSetpointOne(subsystems.tiltServo));
        subsystems.turret.setDefaultCommand(new TurretLock(subsystems.turret));
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


        wholeIndexerForward.whileHeld(new IndexerDriveForward(subsystems.stageOne, subsystems.stageTwo));
        wholeIndexerReverse.whileHeld(new IndexerDriveBackward(subsystems.stageOne, subsystems.stageTwo));
        indexerStageOneForward.whileHeld(new IndexerStageOneDriveForward(subsystems.stageOne));
        indexerStageTwoForward.whileHeld(new IndexerStageTwoDriveForward(subsystems.stageTwo));


        ////////////////////////////////////////////////////////////////////////////
        intakeIntake = new JoystickButton(gamepad, 5);
        //intakeIntake.whileHeld(new IntakeIntake(subsystems.intake));
        intakeIntake.whileHeld(new InstantCommand(()->GlobalManager.SupersystemManager.setShouldIntake(true)));
        intakeIntake.whenInactive(new InstantCommand(()->GlobalManager.SupersystemManager.setShouldIntake(false)));

        ////////////////////////////////////////////////////////////////////////////
        shooterShoot = new JoystickButton(gamepad, 6);
        shooterShoot.whileHeld(new ShooterRun(subsystems.shooter));

        /////////////////////////////////////////////////////////////////////////////
        spinnerRise = new JoystickButton(gamepad, 7);
        spinnerRise.whileHeld(new RiseSpinerWheel(subsystems.spinner));

        spinnerFall = new JoystickButton(gamepad, 8);
        spinnerFall.whileHeld(new LowerSpinerWheel(subsystems.spinner));

        spinnerThreeTimes = new JoystickButton(gamepad, 9);
        spinnerThreeTimes.whenPressed(new ThreeTimes(subsystems.spinner));
        //////////////////////////////////////////////////////////////////////////////
        cameraSetpointOne = new JoystickButton(gamepad, 10);
        cameraSetpointOne.whenPressed(new CameraSetpointOne(subsystems.tiltServo));

        cameraSetpointTwo = new JoystickButton(gamepad, 11);
        cameraSetpointTwo.whenPressed(new CameraSetpointTwo(subsystems.tiltServo));
        ///////////////////////////////////////////////////////////////////////////////
        dashLeft = new JoystickButton(leftJoystick, 5);
        dashLeft.whenPressed(new InstantCommand(() -> this.cyclone.dash(TwisterPathLibrary.straightFourMeters, -90.0).schedule(true)));

        dashRight = new JoystickButton(leftJoystick, 6);
        dashLeft.whenPressed(new InstantCommand(() -> this.cyclone.dash(TwisterPathLibrary.straightFourMeters, 90.0).schedule(true)));

        zeroPose = new JoystickButton(rightJoystick, 2);
        zeroPose.whenPressed(new InstantCommand(() -> {
            subsystems.drivetrain.zeroHeading();
            subsystems.drivetrain.resetOdometry(new Pose2d(0,0, new Rotation2d(0)));
        }));
        ///////////////////////////////////////////////////////////////////////////////
    }

    public void periodic(){
        cyclone.periodic();
        
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        subsystems.drivetrain.zeroHeading();
        subsystems.drivetrain.resetOdometry(new Pose2d(0,0, new Rotation2d(0)));
        
        return cyclone.getAutoCommand(TwisterPathLibrary.straightTwoMeters);
    }

    public double mockDistance() {
        return 10;
    }
}

