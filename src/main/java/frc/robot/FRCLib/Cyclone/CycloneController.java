package frc.robot.FRCLib.Cyclone;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems;
import frc.robot.FRCLib.Cyclone.twister.TwisterController;
import frc.robot.FRCLib.Cyclone.twister.TwisterPath;

public class CycloneController {
    public enum CycloneState{
        UNKNOWN, HOMING, INTAKING, DRIVING, SHOOTING, COLOR_WHEEL, CLIMBING, ERROR
    }
    public Subsystems subsystems;
    public CycloneState cycloneState;

    public TwisterController twisterController;

    public CycloneController(Subsystems subsystems){

        this.subsystems = subsystems;
        this.twisterController = new TwisterController(this.subsystems);
    }

    public void periodic(){
        twisterController.periodic();
        SmartDashboard.putNumber("X", twisterController.getRobotPose().getTranslation().getX());
        SmartDashboard.putNumber("Y", twisterController.getRobotPose().getTranslation().getY());
        SmartDashboard.putNumber("Heading", twisterController.getRobotPose().getRotation().getDegrees());
    }

    public Command getAutoCommand(TwisterPath path){
        twisterController.loadPath(path);
        return twisterController.generateAutonomous();
    }

    public Command dash(TwisterPath path, double angle){
        return twisterController.runPathWithDash(path, angle);
    }

    public boolean isTwisterComplete(){
        return twisterController.isInAccpetableRange();
    }
}
