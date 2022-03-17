// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Indexer extends SubsystemBase {

  TalonFX frontLeftMotor;
  TalonFX frontRightMotor;
  TalonSRX backLeftMotor;
  TalonSRX backRightMotor;

  double frontSpeed;
  double backSpeed;

  ColorSensorV3 colorSensor;
  ColorMatch colorMatcher;
  Color blueTarget;
  Color redTarget;

  int allianceColor;
  int enemyColor;

  int[] balls;

  /** Creates a new Indexer. */
  public Indexer(ColorSensorV3 colorSensor) {

    frontLeftMotor = new TalonFX(Constants.front_left_indexer_motor_port);
    frontRightMotor = new TalonFX(Constants.front_right_indexer_motor_port);
    backLeftMotor= new TalonSRX(Constants.back_left_indexer_motor_port);
    backRightMotor= new TalonSRX(Constants.back_right_indexer_motor_port);

    frontSpeed = 0;
    backSpeed = 0;

    this.colorSensor = colorSensor;
    colorMatcher = new ColorMatch();
    blueTarget = new Color(0.143, 0.427, 0.429);
    redTarget = new Color(0.561, 0.232, 0.114);
    
    balls = new int[2];

    frontRightMotor.setInverted(true);
    setTeamColor();
  }

  public void setTeamColor(){
    if(Constants.teamColor.equals("blue")){
      allianceColor = 1;
      enemyColor = 2;
    } else if(Constants.teamColor.equals("red")){
      allianceColor = 2;
      enemyColor = 1;
    } else {
      allianceColor = 0;
      enemyColor = 0;
    }
  }

  public void setFrontSpeed(double speed){
    if(Math.abs(speed) >= Math.abs(frontSpeed)+0.05 || Math.abs(speed) <= Math.abs(frontSpeed)-0.05){
      frontLeftMotor.set(TalonFXControlMode.PercentOutput, speed);
      frontRightMotor.set(TalonFXControlMode.PercentOutput, speed);
      frontSpeed = speed;
    } else if (Math.abs(speed) <= 0.05 && frontSpeed != 0){
      frontLeftMotor.set(TalonFXControlMode.PercentOutput, 0);
      frontRightMotor.set(TalonFXControlMode.PercentOutput, 0);
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
    
    setAllSpeed((xbox1.getLeftTriggerAxis()+xbox2.getLeftTriggerAxis())/2);
    while(enemyColor==getColorValue()){
      setAllSpeed(-1);
    }
    
  }

  public void stop(){
    setAllSpeed(0);
  }

  
  public int getColorValue(){
    if(colorSensor.getProximity()<6){
      Color detectedColor = colorSensor.getColor();
      ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);
      if(match.color == blueTarget){
        return 1;
      } else if (match.color == redTarget){
        return 2;
      } 
    }
    return -1;
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run  
    if(allianceColor == 0){
      setTeamColor();
    }
  }
}
