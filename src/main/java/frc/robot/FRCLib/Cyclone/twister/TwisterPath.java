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

    public String name;

    /**
     * Creates a blank TwisterPath
     */
    public TwisterPath(String name){
        this.path = new ArrayList<>();
        this.name = name;
    }

    /**
     * Creates a TwisterPath given a set of TwisterPoints
     * @param path the points to have on the path
     */
    public TwisterPath( String name,TwisterPoint[] path){
        this.path = new ArrayList<>(Arrays.asList(path));
        this.name = name;
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

    public String getName(){
        return this.name;
    }
}
