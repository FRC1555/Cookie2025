package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import frc.robot.Constants.IntakeConstants;

/** Intake roller on its own PWM channel (see IntakeConstants). */
public class ShooterSubsystem extends SubsystemBase {
    private Victor intake;

    public ShooterSubsystem(){
        intake = new Victor(IntakeConstants.kIntakeMotorPwmPort);
    }

    public void deadIntake(){
        intake.set(0);
    }

    public void forwIntake(){
        intake.set(IntakeConstants.kIntakeSpeed);
    }

    public void backIntake(){
        intake.set(-IntakeConstants.kIntakeSpeed);
    }
}