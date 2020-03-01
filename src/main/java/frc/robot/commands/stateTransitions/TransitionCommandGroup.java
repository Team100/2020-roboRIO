package frc.robot.commands.stateTransitions;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.GlobalManager;
import frc.robot.Subsystems;
import frc.robot.commands.supersystem.indexer.IndexerStop;

public class TransitionCommandGroup {
    public Subsystems subsystems;


    public TransitionCommandGroup(Subsystems subsystems) {
        this.subsystems = subsystems;
    }


    public void addOneToIntakeStage(){
        int indexOfCurrent = java.util.Arrays.asList(GlobalManager.IndexerManager.locationStatesOrder).indexOf(GlobalManager.IndexerManager.locationState);

        GlobalManager.IndexerManager.locationState = GlobalManager.IndexerManager.locationStatesOrder[indexOfCurrent + 1];
        GlobalManager.IndexerManager.numBalls += 1;
    }
    public Command incrementIntakeStage(){
        return new InstantCommand(this::addOneToIntakeStage);
    }


    public Command bypassCommand(){
        return new PrintCommand("Bypassing");
    }

    public Command setIndexerUncertainCommand(){
        return new PrintCommand("IMPLEMENT THIS");
    }

    public Command stopIndexer(){
        return new SequentialCommandGroup(new IndexerStop(subsystems.stageOne, subsystems.stageTwo), incrementIntakeStage());
    }
}
