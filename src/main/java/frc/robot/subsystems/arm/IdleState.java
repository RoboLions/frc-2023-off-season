// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.arm;

import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.lib.interfaces.Claw;
import frc.robot.lib.statemachine.State;
import frc.robot.lib.statemachine.Transition;

/** Add your docs here. */
public class IdleState extends State {

    @Override
    public void build() {
        // intake from substation
        // if intake button == T and claw sensor == F
        transitions.add(new Transition(() -> {
            return RobotMap.manipulatorController.getRawButton(Constants.ManipulatorControls.SUBSTATION_INTAKE_BUTTON) && 
                RobotMap.claw.getColor() == null;
        }, ArmStateMachine.substationIntakeState));

        // outtake if outtake button pressed
        // transitions.add(new Transition(() -> {
        //     return (RobotMap.manipulatorController.getPOV() >= 135.0 || RobotMap.manipulatorController.getPOV() <= 225.0);
        // }, ArmStateMachine.outtakeState));

        // pickup from ground
        transitions.add(new Transition(() -> {
            return RobotMap.manipulatorController.getRawButton(Constants.ManipulatorControls.GROUND_INTAKE_FRONT);
        }, ArmStateMachine.groundPickupState));

        transitions.add(new Transition(() -> {
            return RobotMap.claw.getColor() != null && 
                    RobotMap.manipulatorController.getRawButton(Constants.ManipulatorControls.HIGH_SCORE_BUTTON);
        }, ArmStateMachine.scoreHighState));
        
        transitions.add(new Transition(() -> {
            return RobotMap.claw.getColor() != null && 
                    RobotMap.manipulatorController.getRawButton(Constants.ManipulatorControls.MID_SCORE_BUTTON);
        }, ArmStateMachine.scoreMidState));
        
        transitions.add(new Transition(() -> {
            return RobotMap.claw.getColor() != null && 
                    RobotMap.manipulatorController.getRawButton(Constants.ManipulatorControls.LOW_SCORE_BUTTON);
        }, ArmStateMachine.scoreLowState));

        // transition to control arm manually
        transitions.add(new Transition(() -> {
            return RobotMap.manipulatorController.getRawButton(Constants.ManipulatorControls.MANUAL_MODE_BUTTON);
        }, ArmStateMachine.manualMoveState));
    }
    
    @Override
    public void init() {
        RobotMap.arm.setIdle();
        Claw.openRequest = true;
        Claw.closeRequest = true;
        // RobotMap.arm.resetEncoders(); TODO uncomment when ready
    }

    @Override
    public void execute() {
        
    }

    @Override
    public void exit() {
        
    }
}
    
