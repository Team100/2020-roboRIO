package frc.robot.commands.stateTransitions;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import frc.robot.GlobalManager;
import frc.robot.Subsystems;
import frc.robot.commands.supersystem.indexer.IndexerDriveForward;
import frc.robot.commands.supersystem.indexer.indexStageOne.IndexerStageOneDriveForward;

import java.util.Map;

import static java.util.Map.entry;

public class TriggerMap {
    public Subsystems subsystems;

    public GlobalManager.IndexerManager.IndexerLocationState selectLocationState() {
        return GlobalManager.IndexerManager.locationState;
    }

    public final Command onIndexerShouldMoveForward = new SelectCommand(
            Map.ofEntries(
                    entry(GlobalManager.IndexerManager.IndexerLocationState.EMPTY, new IndexerStageOneDriveForward(subsystems.stageOne)),
                    entry(GlobalManager.IndexerManager.IndexerLocationState.ONE_PC, new IndexerStageOneDriveForward(subsystems.stageOne)),
                    entry(GlobalManager.IndexerManager.IndexerLocationState.TWO_PC, new IndexerStageOneDriveForward(subsystems.stageOne)),
                    entry(GlobalManager.IndexerManager.IndexerLocationState.THREE_PC, new IndexerDriveForward(subsystems.stageOne, subsystems.stageTwo)),
                    entry(GlobalManager.IndexerManager.IndexerLocationState.THREE_PC_SHIFTED, new IndexerStageOneDriveForward(subsystems.stageOne)),
                    entry(GlobalManager.IndexerManager.IndexerLocationState.FOUR_PC, new IndexerStageOneDriveForward(subsystems.stageOne)),
                    entry(GlobalManager.IndexerManager.IndexerLocationState.FIVE_PC, new InstantCommand(()->System.out.println("Bypassing, FIVEPC"))),
                    entry(GlobalManager.IndexerManager.IndexerLocationState.UNCERTAIN, new InstantCommand(()->System.out.println("Bypassing, UNCERTAIN")))


                    ),
            this::selectLocationState
    );
}
