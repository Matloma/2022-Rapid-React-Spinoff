// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {

  TalonFX telescopingMotor;
  Solenoid modeSelector;
  boolean FAST;


  /** Creates a new Climber. */
  public Climber() {
    telescopingMotor = new TalonFX(Constants.telescoping_motor_port);
    modeSelector = new Solenoid(PneumaticsModuleType.REVPH, Constants.telescoping_power_mode_solenoid_port);
    FAST = true;
  }

  public void moveArms(double speed){
    telescopingMotor.set(TalonFXControlMode.Position, telescopingMotor.getSelectedSensorPosition()+speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
