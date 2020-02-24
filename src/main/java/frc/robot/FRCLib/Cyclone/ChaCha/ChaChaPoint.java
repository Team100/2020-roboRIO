package frc.robot.FRCLib.Cyclone.ChaCha;

public class ChaChaPoint {
    public double x;
    public double y;
    public double theta;
    public boolean isRequired;

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
}
