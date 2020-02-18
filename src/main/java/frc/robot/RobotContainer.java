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
import frc.robot.commands.drivetrain.ArcadeDrive;
import frc.robot.commands.supersystem.intake.intakePivot.IntakeMoveJoystick;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final Drivetrain drivetrain;
    private final Indexer indexer;
    private final Intake intake;
    private final IntakePivot intakePivot;

    private final Shooter shooter;
    private final Turret turret;

    private final Joystick leftJoystick;
    private final Joystick rightJoystick;
    private final Joystick gamepad;

    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        //OI Initiation
        leftJoystick = new Joystick(0);
        rightJoystick = new Joystick(1);
        gamepad = new Joystick(2);


        //Subsystem Initiation
        drivetrain = new Drivetrain();
        indexer = new Indexer();
        intake = new Intake();
        intakePivot = new IntakePivot();
        shooter = new Shooter();
        turret = new Turret();


        //Default Commands
        this.setDefaultCommands();
      

        //Button to Command Mapping
        configureButtonBindings();
    }

    public void setDefaultCommands(){
        drivetrain.setDefaultCommand(new ArcadeDrive(drivetrain, leftJoystick, rightJoystick));
        intakePivot.setDefaultCommand(new IntakeMoveJoystick(intakePivot, gamepad));
    }

    /**
     * Use this method to define your button->command mappings.  Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {


    }


    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return null;
    }
}
