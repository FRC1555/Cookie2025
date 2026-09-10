// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.BlinkinConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.BlinkinSubsystem;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final DriveTrainSubsystem m_drivetrain = new DriveTrainSubsystem();
  private final ShooterSubsystem m_shooter = new ShooterSubsystem();
  private final ArmSubsystem m_arm = new ArmSubsystem();
  private final BlinkinSubsystem m_blinkin = new BlinkinSubsystem();
  private final XboxController m_driveController =
      new XboxController(OperatorConstants.kDriverControllerPort);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Tank drive on the sticks.
    m_drivetrain.setDefaultCommand(new RunCommand(
      () ->
        m_drivetrain.driveTank(
          MathUtil.applyDeadband(m_driveController.getLeftY(), OperatorConstants.kDriveDeadband),
          MathUtil.applyDeadband(m_driveController.getRightY(), OperatorConstants.kDriveDeadband))
      , m_drivetrain)
    );

    // LEDs reflect the robot's current action. Priority: shooter > arm > drive.
    // Reads each subsystem's live state every loop, so no LED command can
    // fight another (this default command owns the Blinkin exclusively).
    m_blinkin.setDefaultCommand(new RunCommand(this::updateLeds, m_blinkin));

    // Arm: Y up, A down.
    new JoystickButton(m_driveController, XboxController.Button.kY.value)
        .toggleOnTrue(new frc.robot.commands.armUpCMD(m_arm));
    new JoystickButton(m_driveController, XboxController.Button.kA.value)
        .toggleOnTrue(new frc.robot.commands.armDownCMD(m_arm));

    // Intake: left bumper forward (output), right bumper reverse (intake).
    new JoystickButton(m_driveController, XboxController.Button.kLeftBumper.value)
        .onTrue(new RunCommand(() -> m_shooter.forwIntake(), m_shooter))
        .onFalse(new RunCommand(() -> m_shooter.deadIntake(), m_shooter));
    new JoystickButton(m_driveController, XboxController.Button.kRightBumper.value)
        .onTrue(new RunCommand(() -> m_shooter.backIntake(), m_shooter))
        .onFalse(new RunCommand(() -> m_shooter.deadIntake(), m_shooter));

    // Drive speed presets (fraction of full power).
    new POVButton(m_driveController, 0)
        .onTrue(new InstantCommand(() -> m_drivetrain.setDriveSpeed(1), m_drivetrain));
    new POVButton(m_driveController, 90)
        .onTrue(new InstantCommand(() -> m_drivetrain.setDriveSpeed(0.75), m_drivetrain));
    new POVButton(m_driveController, 180)
        .onTrue(new InstantCommand(() -> m_drivetrain.setDriveSpeed(0.35), m_drivetrain));
    new POVButton(m_driveController, 270)
        .onTrue(new InstantCommand(() -> m_drivetrain.setDriveSpeed(0.5), m_drivetrain));
  }

  /** Pick the LED pattern for the current robot state (called every loop). */
  private void updateLeds() {
    // Shooter has the highest priority: green while intaking, red while outputting.
    double shooterOut = m_shooter.getOutput();
    if (shooterOut < -0.05) {
      m_blinkin.setPattern(BlinkinConstants.INTAKING);
      return;
    }
    if (shooterOut > 0.05) {
      m_blinkin.setPattern(BlinkinConstants.SHOOTING);
      return;
    }

    // Arm: violet while parked at the top switch, confetti while moving.
    // Note: topLimitSwitchPressed() returns false when the top switch is
    // actually pressed (normally-closed convention), so invert it here.
    if (!m_arm.topLimitSwitchPressed()) {
      m_blinkin.setPattern(BlinkinConstants.ARM_AT_TOP);
      return;
    }
    if (Math.abs(m_arm.getOutput()) > 0.05) {
      m_blinkin.setPattern(BlinkinConstants.ARM_MOVING);
      return;
    }

    // Drive: chase forward, different chase backward, turn-chase while turning.
    double ly = MathUtil.applyDeadband(m_driveController.getLeftY(), 0.05);
    double ry = MathUtil.applyDeadband(m_driveController.getRightY(), 0.05);
    if (ly < -0.01 && ry < -0.01) {
      m_blinkin.setPattern(BlinkinConstants.DRIVE_FORWARD);
    } else if (ly > 0.01 && ry > 0.01) {
      m_blinkin.setPattern(BlinkinConstants.DRIVE_BACKWARD);
    } else if (Math.abs(ly) > 0.01 || Math.abs(ry) > 0.01) {
      m_blinkin.setPattern(BlinkinConstants.DRIVE_TURN);
    } else {
      m_blinkin.setPattern(BlinkinConstants.IDLE);
    }
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public boolean getAutonomousCommand() {
    // An example command will be run in autonomous
    return true;
  }
}
