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
    // Small holding output applied after the arm reaches the top switch.
    // Just enough to counteract gravity, far below stall-burnout current.
    // Tune: raise until the arm stays put, then stop. Check the motor for
    // heat after a few minutes of holding; if it's hot, lower this.
    public static final double kArmHoldSpeed = 0.08;
  }

  public static class IntakeConstants {
    public static final int kIntakeMotorPwmPort = 7;
    // Preset speed; forward uses +kIntakeSpeed, reverse uses -kIntakeSpeed.
    public static final double kIntakeSpeed = 0.7;
  }

  public static class BlinkinConstants {
    // REV Blinkin LED driver PWM channel (5V addressable WS2812 strip on
    // the Blinkin's 7-pin JST output, 12V XT30 power from the PDP/PDH).
    public static final int kBlinkinPwmPort = 5;
    // Patterns from the REV Blinkin LED Pattern Table (SPARK pulse values).
    // Fixed-palette favorites:
    public static final double RAINBOW = -0.99;
    public static final double CONFETTI = -0.87;
    public static final double SHOT_RED = -0.85;
    public static final double SHOT_BLUE = -0.83;
    public static final double FIRE_MEDIUM = -0.67;
    public static final double TWINKLES = -0.63;
    public static final double LARSON_SCANNER = -0.51;
    public static final double LIGHT_CHASE_BLUE = -0.43;
    public static final double STROBE_RED = -0.25;
    public static final double STROBE_BLUE = -0.23;
    public static final double STROBE_GOLD = -0.21;
    public static final double STROBE_WHITE = -0.19;
    public static final double COLOR1_CHASE = -0.17;
    public static final double COLOR2_CHASE = -0.15;
    public static final double COLOR12_CHASE = -0.13;
    // Solid colors:
    public static final double SOLID_HOT_PINK = 0.57;
    public static final double SOLID_RED = 0.61;
    public static final double SOLID_ORANGE = 0.65;
    public static final double SOLID_YELLOW = 0.69;
    public static final double SOLID_LIME = 0.73;
    public static final double SOLID_GREEN = 0.77;
    public static final double SOLID_AQUA = 0.81;
    public static final double SOLID_SKY_BLUE = 0.83;
    public static final double SOLID_BLUE = 0.87;
    public static final double SOLID_VIOLET = 0.91;
    public static final double SOLID_WHITE = 0.93;
    public static final double SOLID_GRAY = 0.95;
    public static final double OFF = 0.99; // Black (strip dark)

    // Pattern selected per robot action (priority: shooter > arm > drive).
    public static final double IDLE = SOLID_BLUE;
    public static final double DRIVE_FORWARD = COLOR12_CHASE;
    public static final double DRIVE_BACKWARD = COLOR2_CHASE;
    public static final double DRIVE_TURN = LIGHT_CHASE_BLUE;
    public static final double ARM_MOVING = CONFETTI;
    public static final double ARM_AT_TOP = SOLID_VIOLET;
    public static final double SHOOTING = SOLID_RED;   // actively outputting
    public static final double INTAKING = SOLID_GREEN; // actively intaking
  }
}