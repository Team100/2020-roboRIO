/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.GlobalManager.IndexerManager.IndexerLocationState;

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

        public static boolean shouldIntake = false;

        public static void setShouldIntake(boolean si){
            shouldIntake = si;
        }

        public static boolean getShouldIntake(){
            return shouldIntake;
        }

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

        public static IndexerLocationState[] locationStatesOrder = {IndexerLocationState.EMPTY, IndexerLocationState.ONE_PC, IndexerLocationState.TWO_PC, IndexerLocationState.THREE_PC, IndexerLocationState.THREE_PC_SHIFTED, IndexerLocationState.FOUR_PC, IndexerLocationState.FOUR_PC_SHIFTED, IndexerLocationState.FIVE_PC};
        public enum IndexerLocationState {
            EMPTY, ONE_PC, TWO_PC, THREE_PC, THREE_PC_SHIFTED, FOUR_PC, FOUR_PC_SHIFTED, FIVE_PC, UNCERTAIN
        }

        public enum IndexerActionState {
            LOADED, LOADING, WAITING_TO_LOAD, UNLOADING, WAITING_TO_UNLOAD, NEUTRAL
        }

        public static IndexerLocationState locationState = IndexerLocationState.EMPTY;
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
            return GlobalManager.SupersystemManager.shouldIntake;
        }

        public static boolean shouldShift(){
            return IndexerManager.locationState == IndexerManager.IndexerLocationState.THREE_PC || IndexerManager.locationState == IndexerLocationState.FOUR_PC;
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

    public static class CommandConditionals{



        public enum IntakeMoveType{
            INTAKING, STOPPED
        }
        public static IntakeMoveType intakeMoveType;



        public enum B1C2FAction {
            STOP_MOTORS, NONE
        }
        public static B1C2FAction evaluateB1C2F() {
            GlobalManager.IndexerManager.IndexerLocationState ls = GlobalManager.IndexerManager.locationState;
            if (ls == GlobalManager.IndexerManager.IndexerLocationState.EMPTY ||
                    ls == GlobalManager.IndexerManager.IndexerLocationState.ONE_PC ||
                    ls == GlobalManager.IndexerManager.IndexerLocationState.TWO_PC ||
                    ls == GlobalManager.IndexerManager.IndexerLocationState.THREE_PC_SHIFTED ||
                    ls == GlobalManager.IndexerManager.IndexerLocationState.FOUR_PC_SHIFTED ||
                    ls == GlobalManager.IndexerManager.IndexerLocationState.FIVE_PC) {
                return B1C2FAction.STOP_MOTORS;
            }
    
            return B1C2FAction.NONE;
        }
        public enum B2C2FAction{
            STOP_MOTORS, NONE, SET_UNCERTAIN
        }
    
        public enum B2C2TAction {
            STOP_MOTORS, NONE, SET_UNCERTAIN
        }
    
        public static B2C2FAction evaluateB2C2F() {
            GlobalManager.IndexerManager.IndexerLocationState ls = GlobalManager.IndexerManager.locationState;
            // if (ls == IndexerLocationState.FOUR_PC || ls == IndexerLocationState.FIVE_PC) {
            //     return B2C2FAction.STOP_MOTORS;
            // }
            return B2C2FAction.NONE;
        }
        public static B2C2TAction evaluateB2C2T() {
            GlobalManager.IndexerManager.IndexerLocationState ls = GlobalManager.IndexerManager.locationState;
            if (ls == GlobalManager.IndexerManager.IndexerLocationState.THREE_PC
            || ls == GlobalManager.IndexerManager.IndexerLocationState.FOUR_PC
            || ls == GlobalManager.IndexerManager.IndexerLocationState.FIVE_PC ) {
                return B2C2TAction.STOP_MOTORS;
            }
            /*if (ls == GlobalManager.IndexerManager.IndexerLocationState.EMPTY ||
                    ls == GlobalManager.IndexerManager.IndexerLocationState.ONE_PC ||
                    ls == GlobalManager.IndexerManager.IndexerLocationState.TWO_PC) {
                return B2C2TAction.SET_UNCERTAIN;
            }*/
            return B2C2TAction.NONE;
        }
        public static enum ShooterMoveType {
            NONE, SPINNING, SPINNINGUP, STOPPED
        }
        
        public static ShooterMoveType shouldSpinup(){
            boolean ta = GlobalManager.TurretManager.targetAcquired;
            boolean sr = GlobalManager.ShooterManager.speedReached;
            /*
            if (ta && !sr) {
                return ShooterMoveType.SPINNINGUP;
            }
            if (!ta) {
                return ShooterMoveType.NONE;
            }
            if (ta && sr) {
                return ShooterMoveType.SPINNING;
            }
            */
            //TODO Uncomment Above
            return ShooterMoveType.SPINNINGUP;
        }
    
        public static ShooterMoveType shouldRun(){
            /*boolean ta = GlobalManager.TurretManager.targetAcquired;
            boolean sr = GlobalManager.ShooterManager.speedReached;
    
            if (ta && sr) {
                return ShooterMoveType.SPINNING;
            }*/

            //TODO Uncomment Above
            return ShooterMoveType.SPINNING;
        }
    
        public static ShooterMoveType shouldStop(){
            boolean ta = GlobalManager.TurretManager.targetAcquired;
    
            if (!ta) {
                return ShooterMoveType.STOPPED;
            }
            return ShooterMoveType.NONE;
        }




        public enum IndexerMoveType {
            NONE, S1F, S1FANDS2F
        }
        
        public static IndexerMoveType indexerShouldMoveForward() {
            GlobalManager.IndexerManager.IndexerLocationState ls = GlobalManager.IndexerManager.locationState;
        
            if (ls == GlobalManager.IndexerManager.IndexerLocationState.EMPTY ||
                    ls == GlobalManager.IndexerManager.IndexerLocationState.ONE_PC ||
                    ls == GlobalManager.IndexerManager.IndexerLocationState.TWO_PC ||
                    ls == GlobalManager.IndexerManager.IndexerLocationState.THREE_PC_SHIFTED ||
                    ls == GlobalManager.IndexerManager.IndexerLocationState.FOUR_PC_SHIFTED) {
                return IndexerMoveType.S1F;
            }
            if (ls == GlobalManager.IndexerManager.IndexerLocationState.THREE_PC || ls == IndexerLocationState.FOUR_PC) {
                return IndexerMoveType.S1FANDS2F;
            }
            return IndexerMoveType.NONE;
        }

        public static boolean needsToShift(){
            if(GlobalManager.IndexerManager.locationState == IndexerLocationState.THREE_PC 
            || GlobalManager.IndexerManager.locationState == IndexerLocationState.FOUR_PC 
            || GlobalManager.IndexerManager.locationState == IndexerLocationState.FIVE_PC){
                return true;
            }
            return false;
        }

        public static boolean shouldExit() {
            if (GlobalManager.IndexerManager.numBalls <= 0) {
                return true;
            }
            return false;
        }
    }


}
