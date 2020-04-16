/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.FRCLib.Cyclone.twister.nebula;

import frc.robot.FRCLib.Cyclone.twister.TwisterPoint;

/**
 * Add your docs here.
 */
public class NebulaStructures {
    public enum NebulaUnits{
        METERS, FEET
    }

    /**
     * A class to represent the centerpoint offset for Nebula
     * 
     * This allows points to be based on a more centralized location
     * or where accuracy is more important.
     */
    public class NebulaCenterpointOffset{
        public double dx;
        public double dy;

        public NebulaCenterpointOffset(double dx, double dy, NebulaUnits units){ // Nebula does all calculations in Meters
            if(units == NebulaUnits.FEET){
                this.dx = NebulaCalc.feetToMeters(dx);
                this.dy = NebulaCalc.feetToMeters(dy);
            } else{
                this.dx = dx;
                this.dy = dy;
            }

        }

      
    }
}
