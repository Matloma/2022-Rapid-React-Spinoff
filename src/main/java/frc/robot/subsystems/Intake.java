// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

  Compressor comp;

  Solenoid fs;
  Solenoid rs;

  boolean isUp;
  
  TalonSRX intakeMotor;

  /** Creates a new Intake. */
  public Intake() {
    comp = new Compressor(PneumaticsModuleType.REVPH);

    fs = new Solenoid(PneumaticsModuleType.REVPH, Constants.intake_forward_solenoid_port);
    rs = new Solenoid(PneumaticsModuleType.REVPH, Constants.intake_reverse_solenoid_port);

    intakeMotor = new TalonSRX(Constants.intake_motor_port);

    setIntakeUp();
  }

  public void spinIntake(XboxController xbox1, XboxController xbox2){
    if(xbox1.getLeftBumper()||xbox2.getLeftBumper()){
      intakeMotor.set(TalonSRXControlMode.PercentOutput, -0.5);
    } else {
      if(xbox1.getLeftTriggerAxis()>0.05){
        intakeMotor.set(TalonSRXControlMode.PercentOutput, xbox1.getLeftTriggerAxis());
      } else {
        intakeMotor.set(TalonSRXControlMode.PercentOutput, xbox2.getLeftTriggerAxis());
      }
    }
  }

  public void toggleIntake(){
    isUp = !isUp;
    fs.toggle();
    rs.toggle();
  }

  public void setIntakeUp(){
    isUp = true;
    fs.set(true);
    rs.set(false);
  }

  public void stop(){
    intakeMotor.set(TalonSRXControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}