package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

    // Live telemetry for Shuffleboard: raw switch states (by DIO port) plus motor output.
    // Watch these while moving the arm by hand: each switch should flip at its end of travel.
    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Arm/Top Switch (DIO 0)", topLimSwitch.get());
        SmartDashboard.putBoolean("Arm/Bottom Switch (DIO 1)", botLimSwitch.get());
        SmartDashboard.putNumber("Arm/Motor", armMotor.get());
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
