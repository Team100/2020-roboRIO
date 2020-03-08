package frc.robot.FRCLib.Cyclone.twister;

import frc.robot.Subsystems;

/**
 * A TwisterPoint based off of the current robot pose
 *
 * This is designed for use with the ChaCha slide
 */
public class TwisterRelativePoint extends TwisterPoint {


    /**
     * Create a relative trajectory point
     * @param x the x offset in meters
     * @param y the y offset in meters
     * @param subsystems the robot subsystems
     */
    public TwisterRelativePoint(double x, double y, Subsystems subsystems) {
        super(subsystems.drivetrain.getPose().getTranslation().getX()+x, subsystems.drivetrain.getPose().getTranslation().getY() + y, 0, true);
    }
}
