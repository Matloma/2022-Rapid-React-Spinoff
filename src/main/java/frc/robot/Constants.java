// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    //Swerve Drive Ports
        public static final int front_left_swerve_drive_port = 2;                             //CAN
        public static final int front_left_swerve_steer_port = 1;                             //CAN
        public static final int front_right_swerve_drive_port = 4;                            //CAN
        public static final int front_right_swerve_steer_port = 3;                            //CAN
        public static final int back_left_swerve_drive_port = 6;                              //CAN
        public static final int back_left_swerve_steer_port = 5;                              //CAN
        public static final int back_right_swerve_drive_port = 8;                             //CAN
        public static final int back_right_swerve_steer_port = 7;                             //CAN

    //Intake Ports
        public static final int intake_forward_solenoid_port = 1;                             //PH
        public static final int intake_reverse_solenoid_port = 0;                             //PH
        public static final int intake_motor_port = 0;                                        //CAN

    //Indexer Ports
        public static final int front_left_indexer_motor_port = 0;                            //CAN
        public static final int front_right_indexer_motor_port = 0;                           //CAN
        public static final int back_left_indexer_motor_port = 0;                             //CAN
        public static final int back_right_indexer_motor_port = 0;                            //CAN
        public static final I2C.Port i2cPort = I2C.Port.kOnboard;                             //I2C

    //Shooter Ports
        public static final int turret_motor_port = 15;                                       //CAN
        public static final int flywheel_motor_port = 13;                                     //CAN
        public static final int flywheel_motor_port_2 = 14;                                   //CAN

        //LIMELIGHT Constants
        public static final double camera_angle = 0;
        public static final double hub_height = 0;
        public static final double LimeLightHeight = 0;

    //Climber Ports
        public static final int telescoping_motor_port = 16;                                  //CAN
        public static final int telescoping_power_mode_solenoid_port = 2;                     //PH

    //Other Ports
        public static final int xbox_controller_1 = 0;                                        //USB - PC
        public static final int xbox_controller_2 = 1;                                        //USB - PC

    //Constants
        public static final double wheel_distance_from_center_left_right = 0.296525;
        public static final double wheel_distance_from_center_front_back = 0.295775;
        public static final double pos_units_per_degree = 72.857777778;
        public static final double turret_units_per_degree = 227.601111;

    //Xbox Controller Button Numbers
        public static final int a_button_num = 0;
        public static final int b_button_num = 1;
        public static final int x_button_num = 2;
        public static final int y_button_num = 3;
        public static final int left_button_num = 4;
        public static final int right_button_num = 5;
        public static final int back_button_num = 6;
        public static final int start_button_num = 7;
        public static final int leftj_button_num = 8;
        public static final int rightj_button_num = 9;
        
    //Variables
        public static double max_throttle = 0.5;
        public static String teamColor = DriverStation.getAlliance().toString().toLowerCase();
        public static double shooterOffset = 0;
        

        
    //Controls
        /*
        Xbox 1

        Back - Switch Field Oriented                          //Check
        Start - Align Wheels to Start Position                //Check
        Joysticks - Drive                                     //Check
        Left Bumper - Decrease Speed by 0.5                   //Check
        Right Bumper - Increase Speed by 0.5                  //Check

        
        Xbox 2 
        
        A - Intake
        B - Indexer
        Right Bumper - Shoot
        Left Trigger - Climb Down
        Right Trigger - Climb Up
        POV Left - Shooter Left
        POV Right - Shooter Right

        */
}
