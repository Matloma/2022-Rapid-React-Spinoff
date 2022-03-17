// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Climber extends SubsystemBase {

  PneumaticHub hub;

  TalonFX telescopingMotor;
  Solenoid modeSelector;
  boolean FAST;
  double armSpeed;


  /** Creates a new Climber. */
  public Climber() {

    this.hub=RobotContainer.hub;

    telescopingMotor = new TalonFX(Constants.telescoping_motor_port);
    modeSelector = hub.makeSolenoid(Constants.telescoping_power_mode_solenoid_port);
    modeSelector.set(true);
    FAST = true;
    armSpeed = 0;
  }

  public void toggleSpeed(){
    FAST = !FAST;
    modeSelector.toggle();
  }

  public void setFast(){
    FAST = true;
    modeSelector.set(true);
  }

  public void setTorque(){
    FAST = false;
    modeSelector.set(false);
  }

  public void moveArms(double speed){
    if(speed<-0.1)setTorque();
    else if(speed>0.1)setFast();
    if(Math.abs(speed)<Math.abs(armSpeed)-0.05||Math.abs(speed)>Math.abs(armSpeed)+0.05){
      telescopingMotor.set(TalonFXControlMode.PercentOutput, speed);
    } else if (Math.abs(speed)<0.05){
      telescopingMotor.set(TalonFXControlMode.PercentOutput, 0);
    }
  }

  public void moveArmsXbox(XboxController xbox1, XboxController xbox2){
    moveArms(xbox2.getRightTriggerAxis()-xbox2.getLeftTriggerAxis());
  }

  public void stop(){
    telescopingMotor.set(TalonFXControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
