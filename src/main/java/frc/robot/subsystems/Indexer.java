// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Indexer extends SubsystemBase {

  TalonSRX frontLeftMotor;
  TalonSRX frontRightMotor;
  TalonSRX backLeftMotor;
  TalonSRX backRightMotor;

  double frontSpeed;
  double backSpeed;

  /** Creates a new Indexer. */
  public Indexer() {

    frontLeftMotor = new TalonSRX(Constants.front_left_indexer_motor_port);
    frontRightMotor = new TalonSRX(Constants.front_right_indexer_motor_port);
    backLeftMotor= new TalonSRX(Constants.back_left_indexer_motor_port);
    backRightMotor= new TalonSRX(Constants.back_right_indexer_motor_port);

    frontSpeed = 0;
    backSpeed = 0;

    frontRightMotor.setInverted(true);
  }

  public void setFrontSpeed(double speed){
    if(Math.abs(speed) >= Math.abs(frontSpeed)+0.05 || Math.abs(speed) <= Math.abs(frontSpeed)-0.05){
      frontLeftMotor.set(TalonSRXControlMode.PercentOutput, speed);
      frontRightMotor.set(TalonSRXControlMode.PercentOutput, speed);
      frontSpeed = speed;
    } else if (Math.abs(speed) <= 0.05 && frontSpeed != 0){
      frontLeftMotor.set(TalonSRXControlMode.PercentOutput, 0);
      frontRightMotor.set(TalonSRXControlMode.PercentOutput, 0);
      frontSpeed = 0;
    }
  }

  public void setBackSpeed(double speed){
    if(Math.abs(speed) >= Math.abs(backSpeed)+0.05 || Math.abs(speed) <= Math.abs(backSpeed)-0.05){
      backLeftMotor.set(TalonSRXControlMode.PercentOutput, speed);
      backRightMotor.set(TalonSRXControlMode.PercentOutput, speed);
      backSpeed = speed;
    } else if (Math.abs(speed) <= 0.05 && backSpeed != 0){
      backLeftMotor.set(TalonSRXControlMode.PercentOutput, 0);
      backRightMotor.set(TalonSRXControlMode.PercentOutput, 0);
      backSpeed = 0;
    }
  }

  public void setAllSpeed(double speed){
    setFrontSpeed(speed);
    setBackSpeed(speed);
  }

  public void indexXbox(XboxController xbox1, XboxController xbox2){
    setAllSpeed(xbox1.getLeftTriggerAxis());
    setAllSpeed(xbox2.getLeftTriggerAxis());
  }

  public void stop(){
    setAllSpeed(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
