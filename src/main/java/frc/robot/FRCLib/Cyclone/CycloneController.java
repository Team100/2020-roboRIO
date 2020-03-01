package frc.robot.FRCLib.Cyclone;

import frc.robot.FRCLib.Cyclone.ChaCha.ChaChaController;

public class CycloneController {

    public ChaChaController chaChaController;

    public void periodic(){
        chaChaController.periodic();
    }

    public boolean isChaChaComplete(){
        return chaChaController.isInAccpetableRange();
    }
}
