/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Manages state for the entire robot
 */
public class GlobalManager {

    /**
     * Possible control modes of the robot
     */
    public enum RobotControlMode{
        CYCLONE, SEMI_AUTO, FULL_MANUAL
    }

    /**
     * The robot's current control mode
     */
    public RobotControlMode robotControlMode;

    /**
     * All management that applies to the operation of the Cyclone
     */
    public static class CycloneManager{

        /**
         * Possible control modes for Cyclone to be in
         */
        public enum CycloneControlMode{
            CHA_CHA, STATE_MACHINE
        }

        /**
         * The current control mode of the CycloneController
         */
        public CycloneControlMode cycloneControlMode;
    }

    /**
     * Manages states that pertain to the Supersystem
     */
    public static class SupersystemManager {
       

        /**
         * States that the supersystem can exist in
         */
        public enum SupersystemState {
            NEUTRAL, INTAKING, QUEUEING, ALIGNING, SHOOTING, JAMMED, REVERSING
        }

        /**
         * The current state of the supersystem
         */
        public static SupersystemState supersystemState;

        /**
         * Is the shooter ready to shoot
         */
        public static boolean shooterReady = false;

        /**
         * Is the turret locked and ready to shoot
         */
        public static boolean turretReady = false;



        /**
         * States for objects that extend outside of the frame perimeter INSIDE: Safely
         * inside of the frame perimeter EXTENDED: Unsafe and outside of the frame
         * perimeter
         */
        public enum FramePerimeterState {
            INSIDE, EXTENDED
        }

        /**
         * Is the intake safely inside of the frame perimeter
         */
        public static FramePerimeterState intakeFramePerimeterState;

        /**
         * Is the color wheel manipulator safely inside of the frame perimeter
         */
        public static FramePerimeterState colorFramePerimeterState;

        ///////////////////////////////////////////////////////////////////////////////
        // Agregations of states                                                     //
        ///////////////////////////////////////////////////////////////////////////////

        /**
         * Is the device ready to shoot
         */
        public static boolean isReadyToShoot = shooterReady && turretReady;


        
    }

    public static class IndexerManager {

        public static IndexerLocationState[] locationStatesOrder = {IndexerLocationState.EMPTY, IndexerLocationState.ONE_PC, IndexerLocationState.TWO_PC, IndexerLocationState.THREE_PC, IndexerLocationState.THREE_PC_SHIFTED, IndexerLocationState.FOUR_PC, IndexerLocationState.FIVE_PC};
        public enum IndexerLocationState {
            EMPTY, ONE_PC, TWO_PC, THREE_PC, THREE_PC_SHIFTED, FOUR_PC, FIVE_PC, UNCERTAIN
        }

        public enum IndexerActionState {
            LOADED, LOADING, WAITING_TO_LOAD, UNLOADING, WAITING_TO_UNLOAD, NEUTRAL
        }

        public static IndexerLocationState locationState;
        public static IndexerActionState actionState;

        public static int numBalls;

        public static boolean isFull = false;

        public static boolean subsystemIsFull(){
            return locationState == IndexerLocationState.FIVE_PC;
        }

        /**
         * Is the Indexer full
         */
        public static boolean indexerFull = IndexerManager.locationState == IndexerManager.IndexerLocationState.FIVE_PC;

        public static boolean shouldIntake(){
            return true;
        }

        public static boolean shouldShift(){
            return IndexerManager.locationState == IndexerManager.IndexerLocationState.THREE_PC;
        }


    }

    public static class ShooterManager{
        public enum ShooterActionState {
            NONE, RECOVERING, SPINNING, STOPPED
        }
        public static ShooterActionState actionState;

        public static boolean speedReached = ShooterManager.actionState == ShooterManager.ShooterActionState.SPINNING;
    }

    public static class TurretManager {
        public enum TurretActionState {
            NONE, MOVING, LOCKED
        }
        public static boolean targetAcquired;
    }


}
