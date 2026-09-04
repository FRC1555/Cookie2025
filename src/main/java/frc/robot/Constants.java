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
    public static final int kDriverControllerPort = 0;
  }

 public static final class Arm {
  public static final int kArmCanId = 6;
  public static final boolean kArmInverted = false;
  public static final int kCurrentLimit = 40;

  public static final double kSoftLimitReverse = 0.0;
  public static final double kSoftLimitForward = 4.6;

  public static final double kArmGearRatio = 1.0 / (48.0 * 4.0);
  public static final double kPositionFactor = kArmGearRatio * 2.0 * Math.PI; //multiply SM value by this number and get arm position in radians
  public static final double kVelocityFactor = kArmGearRatio * 2.0 * Math.PI / 60.0;
  public static final double kArmFreeSpeed = 5676.0 * kVelocityFactor;
  public static final double kArmZeroCosineOffset = - Math.PI / 6; // radians to add to converted arm position to get real-world arm position (starts at ~30deg angle)
  // public static final ArmFeedforward kArmFeedforward = new ArmFeedforward(0.0, 0.4, 12.0/kArmFreeSpeed, 0.0);

 }
}