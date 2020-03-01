
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.FRCLib.AutoHelperFunctions;

/**
 * Handles conversion between units for the Talon auton
 */
public class AutonConversionFactors {


    public static final double convertTalonSRXNativeUnitsToWPILibTrajecoryUnits(double talonVelocity, double tpr, double diameter, double gearRatio){
        double ticksPerMeter = convertTalonEncoderTicksToMeters(talonVelocity, diameter, tpr, gearRatio);
        double metersPerTick = 1/ticksPerMeter;

        double ticksPerSecond = talonVelocity * 10;
        double metersPerSecond = ticksPerSecond * metersPerTick;
        return metersPerSecond;
    }


    public static double convertWPILibUnitsToTalonSRXNativeUnits(double metersPerSecond, double tpr, double diameter, double gearRatio){
        double circumference = Math.PI * diameter;
        double tprAtWheel = tpr * gearRatio;
        double revPerMeterAtWheel = 1/circumference * metersPerSecond;
        double ticksAtWheel = revPerMeterAtWheel * tprAtWheel;
        double inTicksPerMillisecond = ticksAtWheel / 10;

        return inTicksPerMillisecond;

    }

    public static double convertTalonEncoderTicksToMeters(double ticks, double diameter, double tpr, double gearRatio){
            double circumference = Math.PI *diameter;
            double wheelRevolutionsPerMeter = 1/circumference;
            double ticksPerWheelRevolution = tpr * gearRatio;
            double ticksPerMeter = ticksPerWheelRevolution * wheelRevolutionsPerMeter;
            double result = ticks * 1/ticksPerMeter;
            return result;
    }

    public static double convertFeetToMeters(double value) {
        return value * 0.3048;
    }

    public static double convertMetersToFeet(double value) {
        return value * 1 / 0.3048;
    }
}