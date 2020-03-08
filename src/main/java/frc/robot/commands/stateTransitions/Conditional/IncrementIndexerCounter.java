/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.stateTransitions.Conditional;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.GlobalManager;
import frc.robot.GlobalManager.IndexerManager;
import frc.robot.GlobalManager.IndexerManager.IndexerLocationState;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class IncrementIndexerCounter extends InstantCommand {
    public IncrementIndexerCounter() {
        // Use addRequirements() here to declare subsystem dependencies.
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        int indexOfCurrent = java.util.Arrays.asList(GlobalManager.IndexerManager.locationStatesOrder).indexOf(GlobalManager.IndexerManager.locationState);

            //GlobalManager.IndexerManager.locationState = GlobalManager.IndexerManager.locationStatesOrder[indexOfCurrent + 1];
            GlobalManager.IndexerManager.locationState = GlobalManager.IndexerManager.locationStatesOrder[indexOfCurrent + 1];
            if(
            GlobalManager.IndexerManager.locationState != IndexerLocationState.FOUR_PC_SHIFTED &&
            GlobalManager.IndexerManager.locationState != IndexerLocationState.THREE_PC_SHIFTED){
            GlobalManager.IndexerManager.numBalls += 1;
            }
            System.out.println("++++++++++++++++++++++++Index of current+++++++++++++++++++++++"+indexOfCurrent);

    }

}
