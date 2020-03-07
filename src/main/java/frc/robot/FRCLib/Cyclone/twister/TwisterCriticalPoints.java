package frc.robot.FRCLib.Cyclone.twister;

public class TwisterCriticalPoints {
    //TODO see if y needs to be inverted
    public static TwisterPoint frontOfLoadingTriangle =      new TwisterPoint(-6.08, 1.85);
    public static TwisterPoint loadingZoneAtLine =           new TwisterPoint(-3.75, 1.85);
    public static TwisterPoint lineUpForControlPanel =       new TwisterPoint(-1.14, 0);
    public static TwisterPoint loadingSideNoReturn =         new TwisterPoint(-2.34, 0.85);
    public static TwisterPoint insideStructureTurnPoint =    new TwisterPoint(0.43, 2.06);
    public static TwisterPoint onTopOfFirstBall =            new TwisterPoint(1.14, 0);
    public static TwisterPoint onTopOfSecondBall =           new TwisterPoint(2.07, 0);
    public static TwisterPoint onTopOfThirdBall =            new TwisterPoint(2.97, 0);
    public static TwisterPoint shootLineDirectLine =         new TwisterPoint(6.03, 1.69);
    public static TwisterPoint colorWheelCenterpoint =       new TwisterPoint(0, 0);          // FIELD ORIGIN
    public static TwisterPoint twoBallsNearControlPanel =    new TwisterPoint(2.98, 1.99);
    public static TwisterPoint threeAwayBallsInsideStruct =  new TwisterPoint(2.66, 3.06);    // NEED TO EXIT @ 45DEG
    public static TwisterPoint threeAwayBallsOutsideStruct = new TwisterPoint(3.92,3.61);
    public static TwisterPoint shootTriangle =               new TwisterPoint(8.37, 1.69);
}
