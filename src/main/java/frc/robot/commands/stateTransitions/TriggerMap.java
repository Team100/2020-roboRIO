package frc.robot.commands.stateTransitions;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.GlobalManager;
import frc.robot.Subsystems;
import frc.robot.commands.supersystem.indexer.IndexerDriveForward;
import frc.robot.commands.supersystem.indexer.indexStageOne.IndexerStageOneDriveForward;
import frc.robot.commands.supersystem.shooter.ShooterRecover;
import frc.robot.commands.supersystem.shooter.ShooterStop;

import java.util.Map;

import static java.util.Map.entry;

public class TriggerMap {
    public Subsystems subsystems;
    public TransitionCommandGroup tcg;

    public TriggerMap(Subsystems subsystems) {
        this.subsystems = subsystems;
        this.tcg = new TransitionCommandGroup(this.subsystems);

    }

    public GlobalManager.IndexerManager.IndexerLocationState selectLocationState() {
        return GlobalManager.IndexerManager.locationState;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public enum IndexerMoveType {
        NONE, S1F, S1FANDS2F
    }

    public IndexerMoveType indexerShouldMoveForward() {
        GlobalManager.IndexerManager.IndexerLocationState ls = GlobalManager.IndexerManager.locationState;

        if (ls == GlobalManager.IndexerManager.IndexerLocationState.EMPTY ||
                ls == GlobalManager.IndexerManager.IndexerLocationState.ONE_PC ||
                ls == GlobalManager.IndexerManager.IndexerLocationState.TWO_PC ||
                ls == GlobalManager.IndexerManager.IndexerLocationState.THREE_PC_SHIFTED ||
                ls == GlobalManager.IndexerManager.IndexerLocationState.FOUR_PC) {
            return IndexerMoveType.S1F;
        }
        if (ls == GlobalManager.IndexerManager.IndexerLocationState.THREE_PC) {
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

    public enum B1C2FAction {
        STOP_MOTORS, NONE
    }

    public Command newBall = new SequentialCommandGroup(
            tcg.stopIndexer(),
            tcg.incrementIntakeStage()
    );
    public B1C2FAction evaluateB1C2F() {
        GlobalManager.IndexerManager.IndexerLocationState ls = GlobalManager.IndexerManager.locationState;
        if (ls == GlobalManager.IndexerManager.IndexerLocationState.EMPTY ||
                ls == GlobalManager.IndexerManager.IndexerLocationState.ONE_PC ||
                ls == GlobalManager.IndexerManager.IndexerLocationState.TWO_PC ||
                ls == GlobalManager.IndexerManager.IndexerLocationState.THREE_PC_SHIFTED ||
                ls == GlobalManager.IndexerManager.IndexerLocationState.FOUR_PC) {
            return B1C2FAction.STOP_MOTORS;
        }

        return B1C2FAction.NONE;
    }

    public final Command onB1C2F = new SelectCommand(
            Map.ofEntries(
                    entry(B1C2FAction.STOP_MOTORS, tcg.stopIndexer()),
                    entry(B1C2FAction.NONE, tcg.bypassCommand())
            ), this::evaluateB1C2F);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public enum B2C2TAction {
        STOP_MOTORS, NONE, SET_UNCERTAIN
    }

    public B2C2TAction evaluateB2C2T() {
        GlobalManager.IndexerManager.IndexerLocationState ls = GlobalManager.IndexerManager.locationState;
        if (ls == GlobalManager.IndexerManager.IndexerLocationState.THREE_PC) {
            return B2C2TAction.STOP_MOTORS;
        }
        if (ls == GlobalManager.IndexerManager.IndexerLocationState.EMPTY ||
                ls == GlobalManager.IndexerManager.IndexerLocationState.ONE_PC ||
                ls == GlobalManager.IndexerManager.IndexerLocationState.TWO_PC) {
            return B2C2TAction.SET_UNCERTAIN;
        }
        return B2C2TAction.NONE;
    }

    public final Command onB2C2T = new SelectCommand(
            Map.ofEntries(
                    entry(B2C2TAction.STOP_MOTORS, tcg.stopIndexer()),
                    entry(B2C2TAction.SET_UNCERTAIN, tcg.setIndexerUncertainCommand()),
                    entry(B2C2TAction.NONE, tcg.bypassCommand())

            ), this::evaluateB2C2T);


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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


}
