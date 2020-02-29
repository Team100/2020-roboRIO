package frc.robot.commands.stateTransitions;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Subsystems;
import frc.robot.commands.supersystem.indexer.IndexerStop;

public class TransitionCommandGroup {
    public Subsystems subsystems;

    public TransitionCommandGroup(Subsystems subsystems) {
        this.subsystems = subsystems;
    }

    public Command stopIndexer(){
        return new SequentialCommandGroup(new IndexerStop(subsystems.stageOne, subsystems.stageTwo));
    }
}
