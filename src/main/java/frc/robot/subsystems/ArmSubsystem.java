package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

public class ArmSubsystem extends SubsystemBase{
    private Victor armMotor;
    private DigitalInput botLimSwitch;
    private DigitalInput topLimSwitch;

    public ArmSubsystem(){
        armMotor = new Victor(ArmConstants.kArmMotorPwmPort);
        topLimSwitch = new DigitalInput(ArmConstants.kTopLimitSwitchDioPort);
        botLimSwitch = new DigitalInput(ArmConstants.kBottomLimitSwitchDioPort);
    }

    public void setArmSpeed(double speed){
        armMotor.set(speed);
    }

    // Limit-switch convention: these read TRUE while travel is clear and FALSE when
    // tripped, matching the team's historical wiring (see the armUpCMD/armDownCMD end
    // conditions). If the arm buttons stop working or the arm overruns its ends,
    // check this polarity assumption first.
    public boolean topLimitSwitchPressed(){
        return topLimSwitch.get();
    }
    public boolean botLimSwitchPressed(){
        return botLimSwitch.get();
    }
}
