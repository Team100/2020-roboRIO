package frc.robot.FRCLib.Cyclone.ChaCha;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A path that a ChaChaController can run
 */
public class ChaChaPath {
    /**
     * The path to run
     */
    public ArrayList<ChaChaPoint> path;

    /**
     * Creates a blank ChaChaPath
     */
    public ChaChaPath(){
        this.path = new ArrayList<>();
    }

    /**
     * Creates a ChaChaPath given a set of ChaChaPoints
     * @param path the points to have on the path
     */
    public ChaChaPath(ChaChaPoint[] path){
        this.path = new ArrayList<>(Arrays.asList(path));
    }

    /**
     * Adds a point to the front of the ChaChaPath
     * @param point the point to add
     */
    public void prependNewChaChaPoint(ChaChaPoint point){
        this.path.add(0, point);
    }

    /**
     * Adds a point to the end of the ChaChaPath
     * @param point the point to add
     */
    public void appendNewChaChaPoint(ChaChaPoint point){
        this.path.add(point);
    }
}
