// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {

  TalonSRX turretMotor;
  TalonSRX flywheelMotor;
  TalonSRX flywheelMotor2;

  LimeLight limelight;
  
  
  /** Creates a new Shooter. */
  public Shooter(LimeLight limelight) {
    this.limelight = limelight;
    turretMotor = new TalonSRX(Constants.turret_motor_port);
    flywheelMotor = new TalonSRX(Constants.flywheel_motor_port);
    flywheelMotor2 = new TalonSRX(Constants.flywheel_motor_port_2);
    turretMotor.setNeutralMode(NeutralMode.Brake);
    flywheelMotor.setNeutralMode(NeutralMode.Coast);
    flywheelMotor2.setNeutralMode(NeutralMode.Coast);
    flywheelMotor2.setInverted(true);
    turretMotor.configForwardSoftLimitThreshold(90*Constants.turret_units_per_degree);
    turretMotor.configReverseSoftLimitThreshold(-90*Constants.turret_units_per_degree);
  }

  public void aim(){
    if(limelight.getHorizontalOffset()+Constants.shooterOffset<-5)
      turretMotor.set(TalonSRXControlMode.PercentOutput, -0.05);
    else if(limelight.getHorizontalOffset()+Constants.shooterOffset>5)
      turretMotor.set(TalonSRXControlMode.PercentOutput, 0.05);
    else 
      stopAim();

  }

  public void stopAim(){
    turretMotor.set(TalonSRXControlMode.PercentOutput, 0);
  }

  public void shootXbox(XboxController xbox1, XboxController xbox2){
    if(limelight.isInView()){
      aim();
    }
  }

  public void shoot(double speed){
    flywheelMotor.set(TalonSRXControlMode.PercentOutput, speed);
    flywheelMotor2.set(TalonSRXControlMode.PercentOutput, speed);
  }

  public void offsetRight(){
    Constants.shooterOffset += 1;
  }

  public void offsetLeft(){
    Constants.shooterOffset -= 1;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
