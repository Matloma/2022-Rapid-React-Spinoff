// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  public static XboxController xbox1;
  public static XboxController xbox2;

  public static AHRS gyro;

  private final DriveTrain swerve;
  private final DriveXbox driveXbox;

  private final Intake intake;
  private final IntakeXbox intakeXbox;

  public static boolean isFieldOriented;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    xbox1 = new XboxController(Constants.xbox_controller_1);
    xbox2 = new XboxController(Constants.xbox_controller_2);

    gyro = new AHRS();

    swerve = new DriveTrain(gyro);
    driveXbox = new DriveXbox(swerve);
    driveXbox.addRequirements(swerve);
    swerve.setDefaultCommand(driveXbox);

    intake = new Intake();
    intakeXbox = new IntakeXbox(intake);
    intakeXbox.addRequirements(intake);
    intake.setDefaultCommand(intakeXbox);

    isFieldOriented = true;

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    Button start = new JoystickButton(xbox1, Constants.start_button_num);
    start.whenPressed(new ToggleFieldOriented());
    Button subStart = new JoystickButton(xbox2, Constants.start_button_num);
    subStart.whenPressed(new ToggleFieldOriented());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }

  public static void toggleFieldOriented(){
    isFieldOriented = !isFieldOriented;
  }
}
