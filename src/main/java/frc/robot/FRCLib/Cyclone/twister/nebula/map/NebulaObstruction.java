/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.FRCLib.Cyclone.twister.nebula.map;

/**
 * Add your docs here.
 */
public interface NebulaObstruction {
    /**
     * Get the left bounds in field-centric meters
     * @return
     */
    public double getLeftBounds();

    /**
     * Get the right bounds in field-centric meters
     * @return
     */
    public double getRightBounds();

    /**
     * Get the lower bounds in field-centric meters
     * @return
     */
    public double getLowerBounds();

    /**
     * Get the upper bounds in field-centric meters
     * @return
     */
    public double getUpperBounds();
}
