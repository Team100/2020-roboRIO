package frc.robot.FRCLib.Cyclone;

import frc.robot.Subsystems;
import frc.robot.FRCLib.Cyclone.twister.TwisterController;

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
    }

    public boolean isTwisterComplete(){
        return twisterController.isInAccpetableRange();
    }
}
