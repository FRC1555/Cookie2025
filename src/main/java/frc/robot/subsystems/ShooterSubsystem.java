package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.Victor;

public class ShooterSubsystem extends SubsystemBase {
    private Victor intake;
    
    public ShooterSubsystem(){
        intake = new Victor(7);
        
        
        
    }
    
    

// To be straightforward, use this, setting the speed directly within the function, requiring more functions. 

    public void deadIntake(){
        intake.set(0);
    }
    
    public void forwIntake(){
        intake.set(0.7);
    }

    public void backIntake(){
        intake.set(-0.7);
    }

    // To have less syntax to write, use this instead of the above ^

    // public void controlIntake(double speed){
    //     Intake.set(speed);
    // }
}