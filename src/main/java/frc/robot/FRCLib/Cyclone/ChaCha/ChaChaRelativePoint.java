package frc.robot.FRCLib.Cyclone.ChaCha;

import frc.robot.Subsystems;

/**
 * A ChaChaPoint based off of the current robot pose
 *
 * This is designed for use with the ChaCha slide
 */
public class ChaChaRelativePoint extends ChaChaPoint {


    /**
     * Create a relative trajectory point
     * @param x the x offset in meters
     * @param y the y offset in meters
     * @param subsystems the robot subsystems
     */
    public ChaChaRelativePoint(double x, double y, Subsystems subsystems) {
        super(subsystems.drivetrain.getPose().getTranslation().getX()+x, subsystems.drivetrain.getPose().getTranslation().getY() + y, 0, true);
    }
}
