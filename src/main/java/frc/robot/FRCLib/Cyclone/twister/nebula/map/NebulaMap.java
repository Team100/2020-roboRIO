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
public class NebulaMap {
    double delta;
    int[][] map;

    public void constructMap(double mapX, double mapY){
        int numXSegments = (int) Math.round(mapX / delta);
        int numYSegments = (int) Math.round(mapY / delta);

        map = new int[numXSegments][numYSegments];
    };
}
