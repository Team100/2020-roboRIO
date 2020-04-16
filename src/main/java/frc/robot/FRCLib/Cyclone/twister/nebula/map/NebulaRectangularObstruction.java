/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.FRCLib.Cyclone.twister.nebula.map;

import frc.robot.FRCLib.Cyclone.twister.nebula.NebulaCenterpointOffset;

/**
 * A rectangular obstruction for the NebulaMap. A robot cannot pass through a NebulaRectangularObstruction
 */
public class NebulaRectangularObstruction implements NebulaObstruction {

    public NebulaMapUnits units;
    public NebulaCenterpointOffset offset;
    public double delta;
    public double left;
    public double right;
    public double bottom;
    public double top;

    /**
     * Create a rectangular obstruction using field centric meters
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

    /**
     * Create a rectangular obstruction using field centric units
     * @param left
     * @param right
     * @param bottom
     * @param top
     * @param units
     */
    public NebulaRectangularObstruction(double left, double right, double bottom, double top, NebulaMapUnits units){
        if(units != NebulaMapUnits.FIELD_CENTRIC_METERS && units != NebulaMapUnits.FIELD_CENTRIC_FEET){
        
            throw new IllegalArgumentException("Improper units when creating a NebulaRectangularObstruction. When creating a NebulaRectangularObstruction with only dimensions and units, the units must be FIELD_CENTRIC_METERS or FIELD_CENTRIC_FEET. When creating the rectangle at "+this.left+","+this.top+", you are sending "+units.toString());
        }
        this.units = units;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
        this.top = top;
    }

    /**
     * Create a rectangular obstruction using points relative to the centerpoint.
     * @param left
     * @param right
     * @param bottom
     * @param top
     * @param units
     * @param offset
     */
    public NebulaRectangularObstruction(double left, double right, double bottom, double top, NebulaMapUnits units, NebulaCenterpointOffset offset){
        if(units != NebulaMapUnits.CENTERPOINT_METERS && units != NebulaMapUnits.CENTERPOINT_FEET){
        
            throw new IllegalArgumentException("Improper units when creating a NebulaRectangularObstruction. When creating a NebulaRectangularObstruction with an offset, the units must be CENTERPOINT_METERS or CENTERPOINT_FEET. When creating the rectangle at "+this.left+","+this.top+", you are sending "+units.toString());
        }
        this.offset = offset;
        this.units = units;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
        this.top = top;
    }

    /**
     * Create a rectangular obstruction using grid deltas.
     * @param left
     * @param right
     * @param bottom
     * @param top
     * @param units
     * @param delta
     */
    public NebulaRectangularObstruction(double left, double right, double bottom, double top, NebulaMapUnits units, double delta){
        if(units != NebulaMapUnits.GRID_CENTRIC){
        
            throw new IllegalArgumentException("Improper units when creating a NebulaRectangularObstruction. When creating a NebulaRectangularObstruction with a delta, the units must be GRID_CENTRIC. When creating the rectangle at "+this.left+","+this.top+", you are sending "+units.toString());
        }
        this.delta = delta;
        this.units = units;
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
