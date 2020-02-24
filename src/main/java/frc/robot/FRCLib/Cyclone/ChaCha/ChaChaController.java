package frc.robot.FRCLib.Cyclone.ChaCha;

import frc.robot.Subsystems;

public class ChaChaController {
    public ChaChaPregeneratedPath currentPath;
    public Subsystems subsystems;

    public ChaChaController(Subsystems subsystems){
        this.subsystems = subsystems;
    }

    public void setCurrentPath(ChaChaPregeneratedPath currentPath){
        this.currentPath = currentPath;
    }
}
