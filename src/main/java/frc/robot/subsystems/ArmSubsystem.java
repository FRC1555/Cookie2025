package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase{
    private Victor armMotor;
    private DigitalInput botLimSwitch;
    private DigitalInput topLimSwitch;

    public ArmSubsystem(){
        armMotor = new Victor(2);
        topLimSwitch = new DigitalInput(0);
        botLimSwitch = new DigitalInput(1);
    }

    public void armUpwards(){
        System.out.println(topLimSwitch.get());
        if(!topLimitSwitchPressed()){
            armMotor.set(0);
        }else if(topLimitSwitchPressed()){
            armMotor.set(.3);
        }
    }

    public void setArmSpeed(double speed){
        armMotor.set(speed);
    }
    public boolean topLimitSwitchPressed(){
        return topLimSwitch.get();
    }
    public boolean botLimSwitchPressed(){
        return botLimSwitch.get();
    }
}
