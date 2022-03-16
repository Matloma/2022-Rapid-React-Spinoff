// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {

  AHRS gyro;

  TalonFX FLD;
  TalonFX FLS;
  TalonFX FRD;
  TalonFX FRS;
  TalonFX BLD;
  TalonFX BLS;
  TalonFX BRD;
  TalonFX BRS;

  Translation2d FLposition;
  Translation2d FRposition;
  Translation2d BLposition;
  Translation2d BRposition;

  SwerveDriveKinematics kinematics;

  ChassisSpeeds speeds;

  SwerveModuleState[] states;
  SwerveModuleState frontLeft;
  SwerveModuleState frontRight;
  SwerveModuleState backLeft;
  SwerveModuleState backRight;



  /** Creates a new DriveTrain. */
  public DriveTrain(AHRS gyro) {
    this.gyro = gyro;

    FLD = new TalonFX(Constants.front_left_swerve_drive_port);
    FLS = new TalonFX(Constants.front_left_swerve_steer_port);
    FRD = new TalonFX(Constants.front_right_swerve_drive_port);
    FRS = new TalonFX(Constants.front_right_swerve_steer_port);
    BLD = new TalonFX(Constants.back_left_swerve_drive_port);
    BLS = new TalonFX(Constants.back_left_swerve_steer_port);
    BRD = new TalonFX(Constants.back_right_swerve_drive_port);
    BRS = new TalonFX(Constants.back_right_swerve_steer_port);

    FLposition = new Translation2d(Constants.wheel_distance_from_center_front_back, Constants.wheel_distance_from_center_left_right);
    FRposition = new Translation2d(Constants.wheel_distance_from_center_front_back, -Constants.wheel_distance_from_center_left_right);
    BLposition = new Translation2d(-Constants.wheel_distance_from_center_front_back, Constants.wheel_distance_from_center_left_right);
    BRposition = new Translation2d(-Constants.wheel_distance_from_center_front_back, -Constants.wheel_distance_from_center_left_right);

    kinematics = new SwerveDriveKinematics(FLposition, FRposition, BLposition, BRposition);

    speeds = new ChassisSpeeds(0, 0, 0);

    states = kinematics.toSwerveModuleStates(speeds);

    frontLeft = states[0];
    frontRight = states[1];
    backLeft = states[2];
    backRight = states[3];
    
  }

  public void drive(double y, double x, double r){

    speeds = new ChassisSpeeds(y, x, r);

    states = kinematics.toSwerveModuleStates(speeds);

    frontLeft = states[0];
    frontRight = states[1];
    backLeft = states[2];
    backRight = states[3];

    setModules();
  }

  public void driveFO(double y, double x, double r){
    speeds = ChassisSpeeds.fromFieldRelativeSpeeds(y, x, r, Rotation2d.fromDegrees(gyro.getAngle()));

    states = kinematics.toSwerveModuleStates(speeds);

    frontLeft = states[0];
    frontRight = states[1];
    backLeft = states[2];
    backRight = states[3];

    setModules();
  }

  public void driveXbox(XboxController xbox, double speed, boolean FO){
    if(FO) {
      driveFO(xbox.getLeftY()*speed, xbox.getLeftX()*speed, xbox.getRightX()*speed);
    } else {
      drive(xbox.getLeftY()*speed, xbox.getLeftX()*speed, xbox.getRightX()*speed);
    }
  }

  public void setModules(){
    FLD.set(TalonFXControlMode.PercentOutput, frontLeft.speedMetersPerSecond);
    FRD.set(TalonFXControlMode.PercentOutput, frontRight.speedMetersPerSecond);
    BLD.set(TalonFXControlMode.PercentOutput, backLeft.speedMetersPerSecond);
    BRD.set(TalonFXControlMode.PercentOutput, backRight.speedMetersPerSecond);
    FLS.set(TalonFXControlMode.Position, frontLeft.angle.getDegrees()*Constants.pos_units_per_degree);
    FRS.set(TalonFXControlMode.Position, frontRight.angle.getDegrees()*Constants.pos_units_per_degree);
    BLS.set(TalonFXControlMode.Position, backLeft.angle.getDegrees()*Constants.pos_units_per_degree);
    BRS.set(TalonFXControlMode.Position, backRight.angle.getDegrees()*Constants.pos_units_per_degree);
  }

  public void stop(){
    FLD.set(TalonFXControlMode.PercentOutput, 0);
    FRD.set(TalonFXControlMode.PercentOutput, 0);
    BLD.set(TalonFXControlMode.PercentOutput, 0);
    BRD.set(TalonFXControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
