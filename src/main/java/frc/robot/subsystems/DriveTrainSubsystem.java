package frc.robot.subsystems;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import frc.robot.Constants.DriveConstants;

/** Tank drive: one PWM Victor channel per side (see DriveConstants). */
public class DriveTrainSubsystem extends SubsystemBase {

    // One Victor object per side; each PWM channel is Y-split to two controllers.
    private static Victor leftMotors;
    private static Victor rightMotors;
    public double currentDriveSpeed;

    public DriveTrainSubsystem() {
        leftMotors = new Victor(DriveConstants.kLeftMotorPwmPort);
        rightMotors = new Victor(DriveConstants.kRightMotorPwmPort);
        currentDriveSpeed = DriveConstants.kDefaultDriveSpeed;
    }
    
    //Stops the drive train
    public void stop() {
    	leftMotors.set(0);
        rightMotors.set(0);
    }
    
    // Tank drive. Stick forward reads negative Y, so the left channel is negated:
    // pushing both sticks forward drives both sides forward (gearboxes are mirrored).
    public void driveTank(double Lspeed, double Rspeed) {
    	leftMotors.set(-Lspeed * currentDriveSpeed);
        rightMotors.set(Rspeed * currentDriveSpeed);
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
    
    public void setDriveSpeed(double newDriveSpeed){
        currentDriveSpeed = newDriveSpeed;
    }

    // Live telemetry for Shuffleboard: actual motor outputs plus the active speed scale.
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Drive/Left Output", leftMotors.get());
        SmartDashboard.putNumber("Drive/Right Output", rightMotors.get());
        SmartDashboard.putNumber("Drive/Speed Scale", currentDriveSpeed);
    }


}

