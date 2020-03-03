package frc.robot.FRCLib.Cyclone;

import frc.robot.FRCLib.Cyclone.ChaCha.ChaChaController;

public class CycloneController {
    public enum CycloneState{
        UNKNOWN, HOMING, INTAKING, DRIVING, SHOOTING, COLOR_WHEEL, CLIMBING, ERROR
    }
    public CycloneState cycloneState;

    public ChaChaController chaChaController;

    public void periodic(){
        chaChaController.periodic();
    }

    public boolean isChaChaComplete(){
        return chaChaController.isInAccpetableRange();
    }
}
