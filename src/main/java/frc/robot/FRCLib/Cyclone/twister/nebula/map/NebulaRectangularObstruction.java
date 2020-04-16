/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.FRCLib.Cyclone.twister.nebula.map;

import frc.robot.FRCLib.Cyclone.twister.nebula.NebulaCenterpointOffset;

/**
 * Add your docs here.
 */
public class NebulaRectangularObstruction implements NebulaObstruction {

    public NebulaMapUnits units;
    public NebulaCenterpointOffset offset;
    public double left;
    public double right;
    public double bottom;
    public double top;

    /**
     * Create a regular obstruction using field centric meters
     * @param left
     * @param right
     * @param bottom
     * @param top
     */
    public NebulaRectangularObstruction(double left, double right, double bottom, double top){
        this.units = NebulaMapUnits.FIELD_CENTRIC_METERS;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
        this.top = top;
    }

    public NebulaRectangularObstruction(double left, double right, double bottom, double top, NebulaMapUnits units, NebulaCenterpointOffset offset){
        if(units != NebulaMapUnits.CENTERPOINT_METERS && units != NebulaMapUnits.CENTERPOINT_FEET){
        
            throw new IllegalArgumentException("Improper units when creating a NebulaRectangularObstruction. When creating a NebulaRectangularObstruction with an offset, the units must be CENTERPOINT_METERS or CENTERPOINT_FEET. When creating the rectange at "+this.left+","+this.top+", you are sending "+units.toString());
        }
        this.offset = offset;
        this.units = NebulaMapUnits.FIELD_CENTRIC_METERS;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
        this.top = top;
    }

    /**
     * Get the left bounds in field-centric meters
     * @return
     */
    public double getLeftBounds(){
        return 0;
    }

    /**
     * Get the right bounds in field-centric meters
     * @return
     */
    public double getRightBounds(){
        return 0;

    }

    /**
     * Get the lower bounds in field-centric meters
     * @return
     */
    public double getLowerBounds(){
        return 0;

    }

    /**
     * Get the upper bounds in field-centric meters
     * @return
     */
    public double getUpperBounds(){
        return 0;

    }
}
