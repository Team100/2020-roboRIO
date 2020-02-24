package frc.robot.FRCLib.Cyclone.ChaCha;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Transform2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;

public class ChaChaPoint {
    public double x;
    public double y;
    public double theta;
    public boolean isRequired;

    public ChaChaPoint(double x, double y){
        this.x = x;
        this.y = y;
        this.theta = 0;
        this.isRequired = false;
    }
    public ChaChaPoint(double x, double y, boolean isRequired){
        this.x = x;
        this.y = y;
        this.theta = 0;
        this.isRequired = isRequired;
    }
    public ChaChaPoint(double x, double y, double theta) {
        this.x = x;
        this.y = y;
        this.theta = theta;
        this.isRequired = false;
    }

    public ChaChaPoint(double x, double y, double theta, boolean isRequired) {
        this.x = x;
        this.y = y;
        this.theta = theta;
        this.isRequired = isRequired;
    }

    public Pose2d asPose2d(){
        return new Pose2d(this.x, this.y, new Rotation2d(this.theta));
    }
    public Translation2d asWaypoint(){
        return new Translation2d(this.x, this.y);
    }
}
