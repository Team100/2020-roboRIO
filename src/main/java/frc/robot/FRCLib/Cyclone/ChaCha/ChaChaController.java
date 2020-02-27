package frc.robot.FRCLib.Cyclone.ChaCha;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.FRCLib.AutoHelperFunctions.PathGenerator;
import frc.robot.Subsystems;

import java.util.ArrayList;

/**
 * Handles the management of a ChaCha-enabled robot
 */
public class ChaChaController {
    /**
     * The current path that the ChaChaController is set to
     */
    public ChaChaPath currentPath;

    /**
     * A reference to all of the robot subsystems
     */
    public Subsystems subsystems;

    /**
     * Navigation directions for the ChaChaPath
     */
    public enum ChaChaDirection{
        NEUTRAL, POSITIVE, NEGATIVE
    }

    /**
     * The direction that the ChaChaController is currently driving in
     */
    public ChaChaDirection direction = ChaChaDirection.NEUTRAL;

    /**
     * The acceptable offset that the robot can be in for the ChaChaController to be complete
     */
    public double acceptableError = 0.5;

    /**
     * The last waypoint in the trajectory
     */
    public Pose2d endPosition;


    /**
     * Creates a new ChaChaController given a set of subsystems
     * @param subsystems the reference to all subsystems on the robot
     */
    public ChaChaController(Subsystems subsystems){
        this.subsystems = subsystems;
    }

    /**
     * Sets the current path for the ChaChaController
     * @param currentPath The ChaChaPath to run
     */
    public void loadPath(ChaChaPath currentPath){
        this.currentPath = currentPath;
    }

    /**
     * Runs a given path
     * @param currentPath the path to run
     * @return autonomous command
     */
    public Command runPath(ChaChaPath currentPath){
        this.loadPath(currentPath);
        return this.generateAutonomous();
    }

    /**
     * Adds a ChaCha slide path modification to the path
     *
     * ChaCha slides should have requirement as true
     * @param point
     * @return the new command
     */
    public Command addChaChaSlide(ChaChaPoint point){
        this.stripCurrentPath();
        this.currentPath.prependNewChaChaPoint(point);
        return this.generateAutonomous();
    }

    /**
     * Strips the current ChaChaPath of any extraneous data
     *
     * This can include points that have already been translated through
     * as well as any other data that is not beneficial for the operation of
     * the ChaChaController
     *
     * To make a point be part of the ChaChaPath, add required to the ChaChaPoint
     */
    public void stripCurrentPath(){
        Pose2d currentPose = this.getRobotPose();
        ChaChaPoint endPoint = this.currentPath.path.get(this.currentPath.path.size()-1);

        if(currentPose.getTranslation().getX() < endPoint.x){
            this.direction = ChaChaDirection.POSITIVE;
        }
        else if(currentPose.getTranslation().getX() > endPoint.x){
            this.direction = ChaChaDirection.NEGATIVE;
        }

        recursiveStrip(this.currentPath, this.getRobotPose());
    }

    /**
     * A recursive method for stripping extraneous data from a ChaChaPath
     * @param path
     * @param pose
     * @return a stripped ChaChaPath
     */
    public ChaChaPath recursiveStrip(ChaChaPath path, Pose2d pose){
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
        else if(this.direction == ChaChaDirection.POSITIVE){
            if(pose.getTranslation().getX() >= path.path.get(0).x) {
                //Currently more positive than point
                path.path.remove(0);
                return recursiveStrip(path, pose);
            }
        }
        else if(this.direction == ChaChaDirection.NEGATIVE){
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
        this.stripCurrentPath();
        Pose2d start = this.getRobotPose();
        Pose2d end = this.currentPath.path.get(this.currentPath.path.size() - 1).asPose2d();
        endPosition = end;
        ArrayList<Translation2d> midpoints = new ArrayList<>();
        for(int i = 0; i < this.currentPath.path.size() - 1; i++){
            midpoints.add(this.currentPath.path.get(i).asWaypoint());
        }
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
     * A periodic call to the ChaChaController for anything that needs to happen often
     *
     * TODO MAP THIS IN PERIODIC
     */
    public void periodic(){

    }

    /**
     * Returns whether the robot is complete or not
     * @return whether deltaX and deltaY are within the acceptable range
     */
    public boolean isInAccpetableRange(){
        double deltaX = Math.abs(getRobotPose().getTranslation().getX() - endPosition.getTranslation().getX());
        double deltaY = Math.abs(getRobotPose().getTranslation().getY() - endPosition.getTranslation().getY());


        return (deltaX < acceptableError) && (deltaY < acceptableError);
    }
}
