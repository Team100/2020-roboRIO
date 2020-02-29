package frc.robot.commands.stateTransitions;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.GlobalManager;
import frc.robot.Subsystems;
import frc.robot.commands.supersystem.indexer.IndexerDriveForward;
import frc.robot.commands.supersystem.indexer.indexStageOne.IndexerStageOneDriveForward;
import frc.robot.commands.supersystem.shooter.ShooterRecover;
import frc.robot.commands.supersystem.shooter.ShooterStop;

import java.util.Map;
import java.util.Set;

import static java.util.Map.entry;

public class TriggerMap {
    public Subsystems subsystems;

    public TriggerMap(Subsystems subsystems) {
        this.subsystems = subsystems;

    }

    public GlobalManager.IndexerManager.IndexerLocationState selectLocationState() {
        return GlobalManager.IndexerManager.locationState;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public enum IndexerMoveType {
        NONE, S1F, S1FANDS2F
    }

    public IndexerMoveType indexerShouldMoveForward() {
        GlobalManager.IndexerManager.IndexerLocationState a = GlobalManager.IndexerManager.locationState;

        if (a == GlobalManager.IndexerManager.IndexerLocationState.EMPTY ||
                a == GlobalManager.IndexerManager.IndexerLocationState.ONE_PC ||
                a == GlobalManager.IndexerManager.IndexerLocationState.TWO_PC ||
                a == GlobalManager.IndexerManager.IndexerLocationState.THREE_PC_SHIFTED ||
                a == GlobalManager.IndexerManager.IndexerLocationState.FOUR_PC) {
            return IndexerMoveType.S1F;
        }
        if (a == GlobalManager.IndexerManager.IndexerLocationState.THREE_PC) {
            return IndexerMoveType.S1FANDS2F;
        }
        return IndexerMoveType.NONE;
    }

    public final Command onIndexerShouldMoveForward = new SelectCommand(
            Map.ofEntries(
                    entry(IndexerMoveType.S1F, new IndexerStageOneDriveForward(subsystems.stageOne)),
                    entry(IndexerMoveType.S1FANDS2F, new IndexerDriveForward(subsystems.stageOne, subsystems.stageTwo)),
                    entry(IndexerMoveType.NONE, new InstantCommand(() -> System.out.println("Bypassing")))

            ),


            this::indexerShouldMoveForward
    );


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean shouldExit() {
        if (GlobalManager.IndexerManager.numBalls <= 0) {
            return true;
        }
        return false;
    }

    public final Command onHasShotBall = new SelectCommand(
            Map.ofEntries(
                    entry(true, new ShooterStop(subsystems.shooter)),
                    entry(false, new ShooterRecover(subsystems.shooter))
            ),
            this::shouldExit
    );
}
