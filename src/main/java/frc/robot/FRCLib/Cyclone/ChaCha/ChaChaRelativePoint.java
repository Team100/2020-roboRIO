package frc.robot.FRCLib.Cyclone.ChaCha;

import frc.robot.Subsystems;

public class ChaChaRelativePoint extends ChaChaPoint {
    public Subsystems subsystems;
    public ChaChaRelativePoint(double x, double y, Subsystems subsystems) {
        super(subsystems.drivetrain.getPose().getTranslation().getX()+x, subsystems.drivetrain.getPose().getTranslation().getY() + y, 0);
        this.subsystems = subsystems;
    }
}
