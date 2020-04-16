package frc.robot.FRCLib.Cyclone.twister.nebula;


/**
 * A class to represent the centerpoint offset for Nebula
 * 
 * This allows points to be based on a more centralized location
 * or where accuracy is more important.
 */
public class NebulaCenterpointOffset{
    public double dx;
    public double dy;

    public NebulaCenterpointOffset(double dx, double dy, NebulaUnits units){ // Nebula does all calculations in Meters
        if(units == NebulaUnits.FEET){
            this.dx = NebulaCalc.feetToMeters(dx);
            this.dy = NebulaCalc.feetToMeters(dy);
        } else{
            this.dx = dx;
            this.dy = dy;
        }

    }

  
}