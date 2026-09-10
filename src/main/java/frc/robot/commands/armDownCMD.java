package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.ArmSubsystem;

public class armDownCMD extends Command{
    private final ArmSubsystem m_armSubsystem;
    private final Timer rampTimer = new Timer();

    public armDownCMD(ArmSubsystem _armSubsystem){
        m_armSubsystem = _armSubsystem;
        addRequirements(m_armSubsystem);
    }

    public void initialize() {
        rampTimer.reset();
        rampTimer.start();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // Soft start: begin slow, ramp to full down speed so gravity doesn't
        // slam the arm. Fast enough to reach full speed quickly.
        double t = rampTimer.get();
        double frac = Math.min(t / ArmConstants.kArmDownRampTime, 1.0);
        double speed = ArmConstants.kArmDownSpeed * frac
                     + ArmConstants.kArmDownRampStart * (1.0 - frac);
        m_armSubsystem.setArmSpeed(speed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        rampTimer.stop();
        m_armSubsystem.setArmSpeed(0);
    }

    // Ends when the arm reaches the bottom limit switch.
    @Override
    public boolean isFinished() {
        return !m_armSubsystem.botLimSwitchPressed();
    }}