package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

public class armDownCMD extends Command{
    private final ArmSubsystem m_armSubsystem;
    public armDownCMD(ArmSubsystem _armSubsystem){
        m_armSubsystem = _armSubsystem;
        addRequirements(m_armSubsystem);
    }

    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_armSubsystem.setArmSpeed(-0.15);
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_armSubsystem.setArmSpeed(0);
    }
  
    // Ends when the arm reaches the bottom limit switch.
    @Override
    public boolean isFinished() {
        return !m_armSubsystem.botLimSwitchPressed();
    }}
