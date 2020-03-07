package frc.robot.FRCLib.Cyclone;

import frc.robot.FRCLib.Cyclone.twister.TwisterController;

public class CycloneController {
    public enum CycloneState{
        UNKNOWN, HOMING, INTAKING, DRIVING, SHOOTING, COLOR_WHEEL, CLIMBING, ERROR
    }
    public CycloneState cycloneState;

    public TwisterController twisterController;

    public void periodic(){
        twisterController.periodic();
    }

    public boolean isChaChaComplete(){
        return twisterController.isInAccpetableRange();
    }
}
