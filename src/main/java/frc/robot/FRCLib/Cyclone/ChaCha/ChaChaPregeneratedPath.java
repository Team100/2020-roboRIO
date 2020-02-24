package frc.robot.FRCLib.Cyclone.ChaCha;

import java.util.ArrayList;
import java.util.Arrays;

public class ChaChaPregeneratedPath {
    public ArrayList<ChaChaPoint> path;
    public ChaChaPregeneratedPath(){
        this.path = new ArrayList<>();
    }
    public ChaChaPregeneratedPath(ChaChaPoint[] path){
        this.path = new ArrayList<>(Arrays.asList(path));
    }

    public void prependNewChaChaPoint(ChaChaPoint point){
        this.path.add(0, point);
    }
    public void appendNewChaChaPoint(ChaChaPoint point){
        this.path.add(point);
    }
}
