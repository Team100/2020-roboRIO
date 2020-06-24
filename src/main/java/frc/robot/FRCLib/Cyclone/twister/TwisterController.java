package frc.robot.FRCLib.Cyclone.twister;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.FRCLib.AutoHelperFunctions.PathGenerator;
import frc.robot.Subsystems;

import java.util.ArrayList;

/**
 * Handles the management of a Twister-enabled robot
 */
public class TwisterController {
    /**
     * The current path that the TwisterController is set to
     */
    public TwisterPath currentPath;

    /**
     * A reference to all of the robot subsystems
     */
    public Subsystems subsystems;

    /**
     * Navigation directions for the TwisterPath
     */
    public enum PathDirection {
        NEUTRAL, POSITIVE, NEGATIVE
    }

    /**
     * The direction that the TwisterController is currently driving in
     */
    public PathDirection direction = PathDirection.NEUTRAL;

    /**
     * The acceptable offset that the robot can be in for the TwisterController to be complete
     */
    public double acceptableError = 0.5;

    /**
     * The last waypoint in the trajectory
     */
    public Pose2d endPosition;


    /**
     * Creates a new TwisterController given a set of subsystems
     * @param subsystems the reference to all subsystems on the robot
     */
    public TwisterController(Subsystems subsystems){
        this.subsystems = subsystems;
    }

    /**
     * Sets the current path for the TwisterController
     * @param currentPath The TwisterPath to run
     */
    public void loadPath(TwisterPath currentPath){
        this.currentPath = currentPath;
    }

    /**
     * Runs a given path
     * @param currentPath the path to run
     * @return autonomous command
     */
    public Command runPath(TwisterPath currentPath){
        this.loadPath(currentPath);
        this.stripCurrentPath();
        return this.generateAutonomous();
    }

    /**
     * Runs a given path
     * @param currentPath the path to run
     * @param angle angle in degrees
     * @return autonomous command
     */
    public Command runPathWithDash(TwisterPath currentPath, double angle){
        this.loadPath(currentPath);
        this.addDash(this.generateDashPoint(angle));

        return this.generateAutonomous();
    }

    /**
     * Adds a Dash path modification to the path
     *
     * ChaCha slides should have requirement as true
     * @param point
     * @return the new command
     */
    public Command addDash(TwisterPoint point){
        this.stripCurrentPath();
        this.currentPath.prependNewChaChaPoint(point);
        return this.generateAutonomous();
    }

    /**
     * Creates a new point given a direction to dash to
     * 
     * @param angle direction to dash in degrees
     * 
     */
    public TwisterPoint generateDashPoint(double angle) {
        System.out.println("&*&*&*&*&*&*&*&*&*&*&*&*&*&*&*&*&*&*&*&* = " + angle);
        // double x = this.getRobotPose().getTranslation().getX()
        //          + Math.sin(Math.toRadians(angle)
        //          + this.getRobotPose().getRotation().getRadians());
        // double y = this.getRobotPose().getTranslation().getY()
        //          + Math.cos(Math.toRadians(angle)
        //          + this.getRobotPose().getRotation().getRadians());
        double x = this.getRobotPose().getTranslation().getX() + 2;
        double y = this.getRobotPose().getTranslation().getY() + 1;


        return new TwisterPoint(x, y, false);
    }

    /**
     * Strips the current TwisterPath of any extraneous data
     *
     * This can include points that have already been translated through
     * as well as any other data that is not beneficial for the operation of
     * the TwisterController
     *
     * To make a point be part of the TwisterPath, add required to the TwisterPoint
     */
    public void stripCurrentPath(){
        Pose2d currentPose = this.getRobotPose();
        TwisterPoint endPoint = this.currentPath.path.get(this.currentPath.path.size()-1);

        if(currentPose.getTranslation().getX() < endPoint.x){
            this.direction = PathDirection.POSITIVE;
        }
        else if(currentPose.getTranslation().getX() > endPoint.x){
            this.direction = PathDirection.NEGATIVE;
        }

        recursiveStrip(this.currentPath, this.getRobotPose());
    }

    /**
     * A recursive method for stripping extraneous data from a TwisterPath
     * @param path
     * @param pose
     * @return a stripped TwisterPath
     */
    public TwisterPath recursiveStrip(TwisterPath path, Pose2d pose){
        if(path == null){
            return null;
        }
        if(path.path.size() <= 0) {
            System.out.println("Completely stripped path");
            return path;
        }

        if(path.path.get(0).isRequired){
            return path;
        }
        else if(this.direction == PathDirection.POSITIVE){
            if(pose.getTranslation().getX() >= path.path.get(0).x) {
                //Currently more positive than point
                path.path.remove(0);
                return recursiveStrip(path, pose);
            }
        }
        else if(this.direction == PathDirection.NEGATIVE){
            if(pose.getTranslation().getX() <= path.path.get(0).x){
                path.path.remove(0);
                return recursiveStrip(path, pose);
            }
        }

        return path;
    }

    /**
     * Generates an autonomous command that can then be run by scheduler
     * @return
     */
    public Command generateAutonomous(){
        // this.stripCurrentPath();
        Pose2d start = this.getRobotPose();
        Pose2d end = this.currentPath.path.get(this.currentPath.path.size() - 1).asPose2d();
        endPosition = end;



        ArrayList<Translation2d> midpoints = new ArrayList<>();
        for(int i = 0; i < this.currentPath.path.size() - 1; i++){
            midpoints.add(this.currentPath.path.get(i).asWaypoint());
        }

        // System.out.println("%%#%%%#%%#%%#%%#%%# Returning path: " + this.currentPath.getName());
        return PathGenerator.createAutoNavigationCommand(subsystems.drivetrain, start, midpoints, end);
    }

    /**
     * Gets the current pose of the robot
     * @return
     */
    public Pose2d getRobotPose(){
        return this.subsystems.drivetrain.getPose();
    }

    /**
     * A periodic call to the TwisterController for anything that needs to happen often
     *
     */
    public void periodic(){

    }

    /**
     * Returns whether the robot is complete or not
     * @return whether deltaX and deltaY are within the acceptable range
     */
    public boolean isInAcceptableRange(){
        double deltaX = Math.abs(getRobotPose().getTranslation().getX() - endPosition.getTranslation().getX());
        double deltaY = Math.abs(getRobotPose().getTranslation().getY() - endPosition.getTranslation().getY());


        return ((deltaX * deltaX) + (deltaY * deltaY))<(acceptableError * acceptableError); //a^2 + b^2 < c^2
    }
}