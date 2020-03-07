package frc.robot.FRCLib.Cyclone.twister;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;

/**
 * A waypoint for a TwisterController
 */
public class TwisterPoint {
    /**
     * The x coordinate of the waypoint (absolute)
     */
    public double x;
    /**
     * The y coordinate of the waypoint (absolute)
     */
    public double y;
    /**
     * The angle of the waypoint (absolute)
     */
    public double theta;

    /**
     * Is the waypoint required to be navigated through
     *
     * If true, the stripping in the TwisterController will
     * automatically stop when this point is first
     */
    public boolean isRequired;

    /**
     * Creates a new TwisterPoint given an x and y coordinate
     * @param x The x coordinate for the point
     * @param y The y coordinate for the point
     */
    public TwisterPoint(double x, double y){
        this.x = x;
        this.y = y;
        this.theta = 0;
        this.isRequired = false;
    }

    /**
     * Creates a TwisterPoint given an x and y coordinate as well as a requirement
     * @param x The x coordinate for the point
     * @param y The y coordinate for the point
     * @param isRequired Is the point required as part of the path
     */
    public TwisterPoint(double x, double y, boolean isRequired){
        this.x = x;
        this.y = y;
        this.theta = 0;
        this.isRequired = isRequired;
    }

    /**
     * Creates a TwisterPoint given an x and y coordinate as well as an angle
     * @param x The x coordinate for the point
     * @param y The y coordinate for the point
     * @param theta The angle of the point
     */
    public TwisterPoint(double x, double y, double theta) {
        this.x = x;
        this.y = y;
        this.theta = theta;
        this.isRequired = false;
    }

    /**
     * Creates a TwisterPoint given an x and y coordinate as well as an angle and a requirement
     * @param x The x coordinate for the point
     * @param y The y coordinate for the point
     * @param theta The angle of the point
     * @param isRequired
     */
    public TwisterPoint(double x, double y, double theta, boolean isRequired) {
        this.x = x;
        this.y = y;
        this.theta = theta;
        this.isRequired = isRequired;
    }

    /**
     * Returns the point as a Pose2d, for the end coordinate of the path
     * @return the point as a Pose2d
     */
    public Pose2d asPose2d(){
        return new Pose2d(this.x, this.y, new Rotation2d(this.theta));
    }

    /**
     * Returns the point as a Translation2d, for waypoints
     *
     * Angle data will not be converted.
     * @return the point as a Translation2d
     */
    public Translation2d asWaypoint(){
        return new Translation2d(this.x, this.y);
    }
}
