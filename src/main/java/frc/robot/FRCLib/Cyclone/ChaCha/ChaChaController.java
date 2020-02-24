package frc.robot.FRCLib.Cyclone.ChaCha;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import frc.robot.Subsystems;

public class ChaChaController {
    /**
     * The current path that the ChaChaController is set to
     */
    public ChaChaPath currentPath;

    /**
     * Whether the ChaChaController should be run or not
     *
     * If true, the ChaChaController will start running the currentPath.
     *
     * Otherwise, the ChaChaController will have no effect.
     */
    public boolean chaChaEnabled = false;

    /**
     * A refrence to all of the robot subsystems
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
    public void setCurrentPath(ChaChaPath currentPath){
        this.currentPath = currentPath;
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
        Pose2d currentPose = subsystems.drivetrain.getPose();
        ChaChaPoint endPoint = this.currentPath.path.get(this.currentPath.path.size()-1);

        if(currentPose.getTranslation().getX() < endPoint.x){
            this.direction = ChaChaDirection.POSITIVE;
        }
        else if(currentPose.getTranslation().getX() > endPoint.x){
            this.direction = ChaChaDirection.NEGATIVE;
        }


        if(this.direction == ChaChaDirection.POSITIVE){
            for(int i = this.currentPath.path.size()-1; i >= 0; i--){

            }

        }
        else if(this.direction == ChaChaDirection.NEGATIVE){

        }
    }

    /**
     * A recursive method for stripping extraneous data from a ChaChaPath
     * @param path
     * @param pose
     * @return
     */
    public ChaChaPath recursiveStrip(ChaChaPath path, Pose2d pose){
        if(path.path.size() <= 0) {
            System.out.println("Completely stripped path");
            return path;
        }

        if(this.direction == ChaChaDirection.POSITIVE){
            if(pose.getTranslation().getX() >= path.path.get(0).x) {
                //Currently more positive than point
                path.path.remove(0);
                return recursiveStrip(path, pose);
            }
        } else if(this.direction == ChaChaDirection.NEGATIVE){
            if(pose.getTranslation().getX() <= path.path.get(0).x){
                path.path.remove(0);
                return recursiveStrip(path, pose);
            }
        }

        return path;
    }
}
