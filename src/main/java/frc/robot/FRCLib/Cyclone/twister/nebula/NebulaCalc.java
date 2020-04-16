/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.FRCLib.Cyclone.twister.nebula;

import frc.robot.FRCLib.Cyclone.twister.TwisterPoint;


/**
 * Calculations for the Nebula Library
 */
public class NebulaCalc {

    /**
     * Calculate the distance between two points using the Slope Formula
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return      The distance between the two points
     */
    public static double slopeDistance(double x1, double y1, double x2, double y2){
        return (y2-y1)/(x2-x1);
    }

    public static double metersToFeet(double meters){
        return meters * 3.28084;
    }

    public static double feetToMeters(double feet){
        return feet / 3.28084;
    }



    public static TwisterPoint convertRelativePointToAbsolutePoint(NebulaCenterpointOffset offset, TwisterPoint point, NebulaUnits pointUnits){
        double newX = offset.dx + point.x;
        double newY = offset.dy + point.y;
        if(pointUnits == NebulaUnits.FEET){
            newX = offset.dx + NebulaCalc.feetToMeters(point.x);
            newY = offset.dy + NebulaCalc.feetToMeters(point.y);
        }
        
        return new TwisterPoint(newX, newY, point.isRequired);
    }
}
