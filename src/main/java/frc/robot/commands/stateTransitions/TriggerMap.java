package frc.robot.commands.stateTransitions;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import frc.robot.GlobalManager;

import java.util.Map;

import static java.util.Map.entry;

public class TriggerMap {

    private GlobalManager.IndexerManager.IndexerLocationState selectLocationState() {
        return GlobalManager.IndexerManager.locationState;
    }

    public final Command onIndexerShouldMoveForward = new SelectCommand(
            Map.ofEntries(
                    entry(GlobalManager.IndexerManager.IndexerLocationState.ONE_PC, null) //Replace null with actual
            ),
            this::selectLocationState
    )
}
