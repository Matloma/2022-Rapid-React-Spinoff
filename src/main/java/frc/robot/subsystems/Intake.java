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

public class Intake extends SubsystemBase {

  Solenoid fs;
  Solenoid rs;

  boolean isUp;

  double intakeSpeed;

  PneumaticHub hub;
  
  TalonFX intakeMotor;

  /** Creates a new Intake. */
  public Intake(PneumaticHub hub) {

    this.hub = hub;

    fs = hub.makeSolenoid(Constants.intake_forward_solenoid_port);
    rs = hub.makeSolenoid(Constants.intake_reverse_solenoid_port);

    intakeSpeed = 0;

    intakeMotor = new TalonFX(Constants.intake_motor_port);

    setIntakeUp();
  }

  public void spinIntake(XboxController xbox1, XboxController xbox2){
    if(xbox1.getLeftBumper()||xbox2.getLeftBumper()){
      if (intakeSpeed != -0.5){
        intakeMotor.set(TalonFXControlMode.PercentOutput, -0.5);
        intakeSpeed = -0.5;
      }
    } else {
      if(xbox1.getLeftTriggerAxis()>0.05){
        if(xbox1.getLeftTriggerAxis()>=intakeSpeed+0.05 || xbox1.getLeftTriggerAxis()<=intakeSpeed-0.05){
          intakeMotor.set(TalonFXControlMode.PercentOutput, xbox1.getLeftTriggerAxis());
          intakeSpeed = xbox1.getLeftTriggerAxis();
        }
      } else if (xbox2.getLeftTriggerAxis()>0.05){
        if(xbox2.getLeftTriggerAxis()>=intakeSpeed+0.05 || xbox2.getLeftTriggerAxis()<=intakeSpeed-0.05){
          intakeMotor.set(TalonFXControlMode.PercentOutput, xbox2.getLeftTriggerAxis());
          intakeSpeed = xbox2.getLeftTriggerAxis();
        }
      } else {
        if(intakeSpeed != 0){
          intakeMotor.set(TalonFXControlMode.PercentOutput, 0);
          intakeSpeed = 0;
        }
      }
    }
  }

  public void intake(){
    intakeMotor.set(TalonFXControlMode.PercentOutput, 1);
    intakeSpeed = 1;
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
    intakeMotor.set(TalonFXControlMode.PercentOutput, 0);
    intakeSpeed = 0;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
