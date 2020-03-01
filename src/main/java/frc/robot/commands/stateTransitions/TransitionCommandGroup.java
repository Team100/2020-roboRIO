package frc.robot.commands.stateTransitions;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.GlobalManager;
import frc.robot.Subsystems;
import frc.robot.commands.supersystem.indexer.IndexerDriveBackward;
import frc.robot.commands.supersystem.indexer.IndexerStop;
import frc.robot.commands.supersystem.shooter.ShooterRecover;
import frc.robot.commands.supersystem.shooter.ShooterRun;
import frc.robot.commands.supersystem.shooter.ShooterStop;

public class TransitionCommandGroup {
    public Subsystems subsystems;


    public TransitionCommandGroup(Subsystems subsystems) {
        this.subsystems = subsystems;
    }


    public void addOneToIndexerStage(){
        int indexOfCurrent = java.util.Arrays.asList(GlobalManager.IndexerManager.locationStatesOrder).indexOf(GlobalManager.IndexerManager.locationState);

        GlobalManager.IndexerManager.locationState = GlobalManager.IndexerManager.locationStatesOrder[indexOfCurrent + 1];
        GlobalManager.IndexerManager.numBalls += 1;
    }
    public Command incrementIndexerStage(){
        return new InstantCommand(this::addOneToIndexerStage);
    }

    public void zeroIndexerStage(){
        GlobalManager.IndexerManager.numBalls = 0;
    }
    public Command resetIndexerStage(){
        return new InstantCommand(this::zeroIndexerStage);
    }

    
    public Command bypassCommand(){
        return new PrintCommand("Bypassing");
    }

    public Command setIndexerUncertainCommand(){
        return new SequentialCommandGroup(new IndexerDriveBackward(subsystems.stageOne, subsystems.stageTwo), resetIndexerStage());
    }

    public Command stopIndexer(){
        return new SequentialCommandGroup(new IndexerStop(subsystems.stageOne, subsystems.stageTwo), incrementIndexerStage());
    }

    public Command shouldSpinup(){
        return new SequentialCommandGroup(new ShooterRecover(subsystems.shooter));
    }

    public Command shouldRun(){
        return new SequentialCommandGroup(new ShooterRun(subsystems.shooter));
    }

    public Command shouldStop(){
        return new SequentialCommandGroup(new ShooterStop(subsystems.shooter));
    }
}
