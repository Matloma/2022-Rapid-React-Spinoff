// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

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
  public static ColorSensorV3 colorSensor;
  public static LimeLight limelight;

  private final DriveTrain swerve;
  private final DriveXbox driveXbox;

  private final Intake intake;
  private final IntakeXbox intakeXbox;

  private final Indexer indexer;
  private final IndexerXbox indexerXbox;

  private final Climber climber;
  private final ClimberXbox climberXbox;

  private final Shooter shooter;
  private final ShooterXbox shooterXbox;

  public static PneumaticHub hub;

  public static boolean isFieldOriented;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    hub = new PneumaticHub(53);

    xbox1 = new XboxController(Constants.xbox_controller_1);
    xbox2 = new XboxController(Constants.xbox_controller_2);

    gyro = new AHRS();
    colorSensor = new ColorSensorV3(Constants.i2cPort);
    limelight = new LimeLight();

    swerve = new DriveTrain(gyro);
    driveXbox = new DriveXbox(swerve);
    driveXbox.addRequirements(swerve);
    swerve.setDefaultCommand(driveXbox);

    intake = new Intake(hub);
    intakeXbox = new IntakeXbox(intake);
    intakeXbox.addRequirements(intake);
    intake.setDefaultCommand(intakeXbox);

    indexer = new Indexer(colorSensor);
    indexerXbox = new IndexerXbox(indexer);
    indexerXbox.addRequirements(indexer);
    indexer.setDefaultCommand(indexerXbox);

    climber = new Climber(hub);
    climberXbox = new ClimberXbox(climber);
    climberXbox.addRequirements(climber);
    climber.setDefaultCommand(climberXbox);

    shooter = new Shooter(limelight);
    shooterXbox = new ShooterXbox(shooter);
    shooterXbox.addRequirements(shooter);
    shooter.setDefaultCommand(shooterXbox);

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
    Button back = new JoystickButton(xbox1, Constants.back_button_num);
    back.whenPressed(new ToggleFieldOriented());
    Button start = new JoystickButton(xbox1, Constants.start_button_num);
    start.whenPressed(new AlignWheelsForward(swerve));
    Button LBumper = new JoystickButton(xbox1, Constants.left_button_num);
    LBumper.whenPressed(new DecreaseSpeed(swerve));
    Button RBumper = new JoystickButton(xbox1, Constants.right_button_num);
    RBumper.whenPressed(new IncreaseSpeed(swerve));
    Button Y = new JoystickButton(xbox1, Constants.y_button_num);
    Y.whenPressed(new ToggleIntake(intake));
    Button subY = new JoystickButton(xbox2, Constants.y_button_num);
    subY.whenPressed(new ToggleIntake(intake));
    Button subRBumper = new JoystickButton(xbox2, Constants.right_button_num);
    subRBumper.whenPressed(new Shoot(shooter));
    subRBumper.whenReleased(new StopShoot(shooter));
    Button subPOVLeft = new POVButton(xbox2, -90);
    subPOVLeft.whenPressed(new OffsetLeft(shooter));
    Button subPOVRight = new POVButton(xbox2, 90);
    subPOVRight.whenPressed(new OffsetRight(shooter));
    Button subA = new JoystickButton(xbox2, Constants.a_button_num);
    subA.whenPressed(new IntakeBalls(intake));
    subA.whenReleased(new StopIntakeBalls(intake));
    Button subB = new JoystickButton(xbox2, Constants.b_button_num);
    subB.whenPressed(new IndexBalls(indexer));
    subB.whenReleased(new StopIndexBalls(indexer));
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
