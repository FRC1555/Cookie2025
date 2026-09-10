package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.BlinkinConstants;

/**
 * REV Blinkin LED driver on its own PWM channel (see BlinkinConstants).
 * The Blinkin reads the pulse like a SPARK motor controller and selects one
 * of its preloaded patterns; see the pattern constants in BlinkinConstants
 * (values from the REV Blinkin LED Pattern Table).
 */
public class BlinkinSubsystem extends SubsystemBase {
    private final Spark blinkin;
    private double currentPattern;

    public BlinkinSubsystem() {
        blinkin = new Spark(BlinkinConstants.kBlinkinPwmPort);
        currentPattern = BlinkinConstants.IDLE;
    }

    /** Select a pattern; use one of the BlinkinConstants pattern values. */
    public void setPattern(double pattern) {
        currentPattern = pattern;
        blinkin.set(pattern);
    }

    public double getPattern() {
        return currentPattern;
    }

    // Live telemetry for Shuffleboard/Elastic.
    @Override
    public void periodic() {
        SmartDashboard.putNumber("LEDs/Pattern", currentPattern);
    }
}