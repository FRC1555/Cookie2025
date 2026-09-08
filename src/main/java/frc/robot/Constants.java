// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    // Xbox controller USB port on the Driver Station.
    public static final int kDriverControllerPort = 0;
    // Joystick deadband applied to the drive sticks.
    public static final double kDriveDeadband = 0.05;
  }

  public static class DriveConstants {
    // One PWM channel per side. Each channel feeds a Y-splitter driving two
    // Victor SPX controllers (PWM mode), one per bag motor.
    public static final int kLeftMotorPwmPort = 0;
    public static final int kRightMotorPwmPort = 1;
    // Default drive speed scale (0.0-1.0). Adjustable on the fly with the POV pad.
    public static final double kDefaultDriveSpeed = 0.3;
  }

  public static class ArmConstants {
    public static final int kArmMotorPwmPort = 2;
    public static final int kTopLimitSwitchDioPort = 0;
    public static final int kBottomLimitSwitchDioPort = 1;
    public static final double kArmUpSpeed = 0.3;
    public static final double kArmDownSpeed = -0.15;
  }

  public static class IntakeConstants {
    public static final int kIntakeMotorPwmPort = 7;
    // Preset speed; forward uses +kIntakeSpeed, reverse uses -kIntakeSpeed.
    public static final double kIntakeSpeed = 0.7;
  }
}