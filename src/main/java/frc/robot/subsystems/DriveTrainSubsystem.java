package frc.robot.subsystems;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
/**
 *
 */
public class DriveTrainSubsystem extends SubsystemBase {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    //Declaring motors
    private static Victor leftMotors;
    private static Victor rightMotors; 
    public double currentDriveSpeed;
    
    public DriveTrainSubsystem() {
        leftMotors = new Victor(0);
        rightMotors = new Victor(1);
        currentDriveSpeed = 0.3;
    }
    
    //Stops the drive train
    public void stop() {
    	leftMotors.set(0);
        rightMotors.set(0);
        SmartDashboard.putNumber("Left Drive speed: ", 0);
        SmartDashboard.putNumber("Right Drive speed: ", 0);    
    }
    
    //Drives the robot with two separate powers for the wheels
    public void driveTank(double Lspeed, double Rspeed) {
    	leftMotors.set(-Lspeed * currentDriveSpeed);
        rightMotors.set(Rspeed * currentDriveSpeed);
        SmartDashboard.putNumber("Left Drive speed: ", Lspeed);
        SmartDashboard.putNumber("Right Drive speed: ", Rspeed);
    }

    //Drives the robot as if the front were the back
    public void driveTankInverted(double Lspeed, double Rspeed) {
    	leftMotors.set(-Rspeed * 0.3);
    	rightMotors.set(Lspeed * 0.3);
    }
    
    //Drives straight
    public void driveStraight(double speed) {
    	leftMotors.set(-speed * 0.3);
    	rightMotors.set(speed * 0.3);
    }
    
    //Function pending
    //Will allow you to drive with separate powers for the wheels for a set amount of time
    public void driveTank(double Lspeed, double Rspeed, long time) {
    	driveTank(Lspeed, Rspeed);
    	//insert wait command here
    	stop();
    }
    
    //Function pending
    //Will allow you to drive straight for a set amount of time
    public void driveStraight(double speed, double time) {
    	driveStraight(speed);
    	//insert wait command here
    	stop();
    }

    public void setDriveSpeed(double newDriveSpeed){
        currentDriveSpeed = newDriveSpeed;
    }


}

