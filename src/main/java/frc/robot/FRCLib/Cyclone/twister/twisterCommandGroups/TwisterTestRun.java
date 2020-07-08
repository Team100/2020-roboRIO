/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.FRCLib.Cyclone.twister.twisterCommandGroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.FRCLib.Cyclone.twister.TwisterPath;
import frc.robot.FRCLib.Cyclone.twister.TwisterPoint;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class TwisterTestRun extends SequentialCommandGroup {
  /**
   * Creates a new TwisterTestRun.
   */
  public TwisterTestRun(RobotContainer container) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());

    //TODO FIGURE OUT HOW TO GET THE ROBOT POSITION ON RUNTIME
    super(container.cyclone.getAutoCommand(new TwisterPath("Step 1", new TwisterPoint[] { new TwisterPoint(4, 0, 0) })),
        container.cyclone.getAutoCommand(new TwisterPath("Step 2", new TwisterPoint[] { new TwisterPoint(3, 0, 0) })));
  }
}
