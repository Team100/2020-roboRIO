/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.NeutralMode;
import frc.robot.FRCLib.Motors.FRCTalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import frc.robot.Constants;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ControlPanelSpinner extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private final I2C.Port i2cPort = I2C.Port.kOnboard;

  private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
  private final ColorMatch colorMatcher = new ColorMatch();
  public FRCTalonSRX spinner;

  private int redCounter = 0;
  private int yellowCounter = 0;
  private int blueCounter = 0;
  private int greenCounter = 0;
  private boolean redController = true;
  private boolean blueController = true;
  private boolean yellowController = true;
  private boolean greenController = true;
 
  private int currentColor;
  private int nextColor;
  private int revolutionsCounter = 0;
  private boolean stop = false;

  private final Color detectedColor = colorSensor.getColor();
  private final double red = detectedColor.red;
  private final double blue = detectedColor.blue;
  private final double green = detectedColor.green;
  private double configuratingColors;

  private final Color kBlueTarget = ColorMatch.makeColor(
    Preferences.getInstance().getDouble("RedTile_BlueComponent", Constants.ControlPanelSpinnerConstants.ControlPanelSpinnerColors.RGB_RED_VALUE_FOR_BLUE),
    Preferences.getInstance().getDouble("GreenTile_BlueComponent", Constants.ControlPanelSpinnerConstants.ControlPanelSpinnerColors.RGB_GREEN_VALUE_FOR_BLUE),
    Preferences.getInstance().getDouble("BlueTile_BlueComponent", Constants.ControlPanelSpinnerConstants.ControlPanelSpinnerColors.RGB_BLUE_VALUE_FOR_BLUE));
  private final Color kGreenTarget = ColorMatch.makeColor(
    Preferences.getInstance().getDouble("RedTile_GreenComponent", Constants.ControlPanelSpinnerConstants.ControlPanelSpinnerColors.RGB_RED_VALUE_FOR_GREEN),
    Preferences.getInstance().getDouble("GreenTile_GreenComponent", Constants.ControlPanelSpinnerConstants.ControlPanelSpinnerColors.RGB_GREEN_VALUE_FOR_GREEN),
    Preferences.getInstance().getDouble("BlueTile_GreenComponent", Constants.ControlPanelSpinnerConstants.ControlPanelSpinnerColors.RGB_BLUE_VALUE_FOR_GREEN));
  private final Color kRedTarget = ColorMatch.makeColor(
    Preferences.getInstance().getDouble("RedTile_RedComponent", Constants.ControlPanelSpinnerConstants.ControlPanelSpinnerColors.RGB_RED_VALUE_FOR_RED),
    Preferences.getInstance().getDouble("GreenTile_RedComponent", Constants.ControlPanelSpinnerConstants.ControlPanelSpinnerColors.RGB_GREEN_VALUE_FOR_RED),
    Preferences.getInstance().getDouble("BlueTile_RedComponent", Constants.ControlPanelSpinnerConstants.ControlPanelSpinnerColors.RGB_BLUE_VALUE_FOR_RED));
  private final Color kYellowTarget = ColorMatch.makeColor(
    Preferences.getInstance().getDouble("RedTile_YellowComponent", Constants.ControlPanelSpinnerConstants.ControlPanelSpinnerColors.RGB_RED_VALUE_FOR_YELLOW),
    Preferences.getInstance().getDouble("GreenTile_YellowComponent", Constants.ControlPanelSpinnerConstants.ControlPanelSpinnerColors.RGB_GREEN_VALUE_FOR_YELLOW),
    Preferences.getInstance().getDouble("BlueTile_YellowComponent", Constants.ControlPanelSpinnerConstants.ControlPanelSpinnerColors.RGB_BLUE_VALUE_FOR_YELLOW));

  public ControlPanelSpinner() {
    spinner = new FRCTalonSRX.FRCTalonSRXBuilder(Constants.ControlPanelSpinnerConstants.ControlPanelSpinnerMotors.CAN_ID)
    .withInverted(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.INVERT)
    .withFeedbackPort(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.FEEDBACK_PORT)
    .withSensorPhase(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.SENSOR_PHASE)
    .withTimeout(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.TIMEOUT)
    .withCurrentLimitEnabled(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.ENABLE_CURRENT_LIMIT)
    .withCurrentLimit(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.CURRENT_LIMIT)
    .withOpenLoopRampRate(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.OPEN_LOOP_RAMP)
    .withNominalOutputForward(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.NOMINAL_OUTPUT_FORWARD)
    .withNominalOutputReverse(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.NOMINAL_OUTPUT_REVERSE)
    .withPeakOutputForward(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.PEAK_OUTPUT_FORWARD)
    .withPeakOutputReverse(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.PEAK_OUTPUT_REVERSE).build();

    spinner.setNeutralMode(NeutralMode.Brake);

    addChild("Control Panel Motor", spinner);

     colorMatcher.addColorMatch(kBlueTarget);
    colorMatcher.addColorMatch(kGreenTarget);
    colorMatcher.addColorMatch(kRedTarget);
    colorMatcher.addColorMatch(kYellowTarget);

    configuratingColors = SmartDashboard.getNumber("configurating Colors", 0);
    SmartDashboard.putNumber("configurating Colors", configuratingColors);
  }

  public void initDefaultCommand() {
    calibrate();

    colorMatcher.addColorMatch(kBlueTarget);
    colorMatcher.addColorMatch(kGreenTarget);
    colorMatcher.addColorMatch(kRedTarget);
    colorMatcher.addColorMatch(kYellowTarget);

    configuratingColors = SmartDashboard.getNumber("configurating Colors", 0);
    SmartDashboard.putNumber("configurating Colors", configuratingColors);
  }

  public void spin(double speed) {
    spinner.drivePercentOutput(speed);
  }

  public void calibrate() {
    configuratingColors = SmartDashboard.getNumber("configurating Colors", 0);
    Color detectedColor1 = colorSensor.getColor();
    double red1 = detectedColor1.red;
    double blue1 = detectedColor1.blue;
    double green1 = detectedColor1.green;

    if (configuratingColors == 1) {
      Preferences.getInstance().putDouble("RedTile_RedComponent", red1);
      Preferences.getInstance().putDouble("GreenTile_RedComponent", green1);
      Preferences.getInstance().putDouble("BlueTile_RedComponent", blue1);

    }
    if (configuratingColors == 2) {
      Preferences.getInstance().putDouble("RedTile_BlueComponent", red1);
      Preferences.getInstance().putDouble("GreenTile_BlueComponent", green1);
      Preferences.getInstance().putDouble("BlueTile_BlueComponent", blue1);

    }
    if (configuratingColors == 3) {

      Preferences.getInstance().putDouble("RedTile_GreenComponent", red1);
      Preferences.getInstance().putDouble("GreenTile_GreenComponent", green1);
      Preferences.getInstance().putDouble("BlueTile_GreenComponent", blue1);

    }
    if (configuratingColors == 4) {
      Preferences.getInstance().putDouble("RedTile_YellowComponent", red1);
      Preferences.getInstance().putDouble("GreenTile_YellowComponent", green1);
      Preferences.getInstance().putDouble("BlueTile_YellowComponent", blue1);

    }
  }

  public int getCurrentColor() {
    final Color detectedColor = colorSensor.getColor();
    final ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);
    if (match.color == kBlueTarget) {
      return 1;
    } else if (match.color == kRedTarget) {
      return 3;
    } else if (match.color == kGreenTarget) {
      return 2;
    } else if (match.color == kYellowTarget) {
      return 4;
    } else{
      return 0;
    }
  }

  public int getRevolutionsCounter() {
    return revolutionsCounter;
  }

  public void resetTo0(){
    revolutionsCounter = 0;
  }

  public boolean reachedColor(){
    return stop;
  }

  public void resetToFalse(){
    stop = false;
  }
  public void periodic() {
    final Color detectedColor = colorSensor.getColor();
    //Run the color match algorithm on our detected color
    String colorString;

    final ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);
    if (match.confidence>.91){
    if (match.color == kBlueTarget) {
      currentColor = 1;
      if (nextColor == currentColor) {
        revolutionsCounter = revolutionsCounter + 1;
        nextColor = 2;
      } else {
        nextColor = 2;
     }
      colorString = "Blue";
    } else if (match.color == kRedTarget) {
      currentColor = 3;
        nextColor = 4;
      colorString = "Red";
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
      currentColor = 2;
        nextColor = 3;
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
      currentColor = 4;
        nextColor = 1;
    } else {
      colorString = "Unknown";
    }}
    else{
      colorString = "Unknown";
    }
    //Open Smart Dashboard or Shuffleboard to see the color detected by the sensor.
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);
    SmartDashboard.putNumber("Counter of Changes", revolutionsCounter);
    SmartDashboard.putData("Conrol Panel Spinner", this);

    String gameData = DriverStation.getInstance().getGameSpecificMessage();

    if (match.color == kRedTarget) {
      if (redController) {
        redCounter = 0;
        redController = false;
      }
      redCounter = redCounter + 1;
    } else if (match.color != kRedTarget) {
      redController = true;
    }
    if (match.color == kBlueTarget) {
      if (blueController) {
        blueCounter = 0;
        blueController = false;
      }
      blueCounter = blueCounter + 1;
    } else if (match.color != kBlueTarget) {
      blueController = true;
    }
    if (match.color == kGreenTarget) {
      if (greenController) {
        greenCounter = 0;
        greenController = false;
      }
      greenCounter = greenCounter + 1;
    } else if (match.color != kGreenTarget) {
      greenController = true;
    }
    if (match.color == kYellowTarget) {
      if (yellowController) {
        yellowCounter = 0;
        yellowController = false;
      }
      yellowCounter = yellowCounter + 1;
    } else if (match.color != kYellowTarget) {
      yellowController = true;
    }
    SmartDashboard.putNumber("Red Counter", redCounter);
    SmartDashboard.putNumber("Blue Counter", blueCounter);
    SmartDashboard.putNumber("Green Counter", greenCounter);
    SmartDashboard.putNumber("Yellow Counter", yellowCounter);
    if (gameData.length() > 0) {
      switch (gameData.charAt(0)) {
      case 'B':        // Blue case code
      if (match.confidence>.95){
        if (match.color == kRedTarget) {
          stop = true;
        }}
        break;
      case 'G':        // Green case code
      if (match.confidence>.95) {
        if (match.color == kYellowTarget) {
          stop = true;
        }}
        break;
      case 'R':        // Red case code
      if (match.confidence>.95) {
        if (match.color == kBlueTarget) {
          stop = true;
        }}
        break;
      case 'Y':        // Yellow case code
      if (match.confidence>.95){
        if (match.color == kGreenTarget) {
          stop = true;
        }}
        break;
      default:// This is corrupt data
        break;
      }
    } else {
      stop = false;
      // Code for no data received yet
    }
    SmartDashboard.putString("Game Data", gameData);
  }

  public void teleopInit() {
    configuratingColors = SmartDashboard.getNumber("configurating Colors", 0);
  }
}
