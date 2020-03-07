package frc.robot.FRCLib.Cyclone.twister;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A path that a TwisterController can run
 */
public class TwisterPath {
    /**
     * The path to run
     */
    public ArrayList<TwisterPoint> path;

    /**
     * Creates a blank TwisterPath
     */
    public TwisterPath(){
        this.path = new ArrayList<>();
    }

    /**
     * Creates a TwisterPath given a set of ChaChaPoints
     * @param path the points to have on the path
     */
    public TwisterPath(TwisterPoint[] path){
        this.path = new ArrayList<>(Arrays.asList(path));
    }

    /**
     * Adds a point to the front of the TwisterPath
     * @param point the point to add
     */
    public void prependNewChaChaPoint(TwisterPoint point){
        this.path.add(0, point);
    }

    /**
     * Adds a point to the end of the TwisterPath
     * @param point the point to add
     */
    public void appendNewChaChaPoint(TwisterPoint point){
        this.path.add(point);
    }
}
