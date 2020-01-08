
package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.controller.PIDController;

public class Shooter{

    public static Shooter instance;
    //shooter motor controllers
    private static Servo shooterAngle; 
    private static TalonSRX launcher;

    private static PIDController launcherPID;

    public static Shooter getInstance(){
        if (instance == null)
            instance = new Shooter(); 
        return instance;
    }

    public Shooter(){

        launcher = new TalonSRX(Constants.LAUNCHER_TALONSRX); 
        shooterAngle = new Servo(Constants.SHOOTER_SERVO); //servo is a type of motorcontroller
        //encoder for launcher


    //motor pid
        launcherPID = new PIDController(Constants.LAUCHER_kP, Constants.LAUNCHER_kI, Constants.LAUNCHER_kD);
        launcherPID.enableContinuousInput(-180, 180); 
        launcherPID.setTolerance(1d); 
        
    }

    public static void setFarAngle(double angle){
        shooterAngle.setAngle(angle);
        //change to a setpoint 0-1 for actuator, testing required...
        
    }

    public static void setLayupAngle(){
        shooterAngle.setAngle(Constants.SHOOTER_LAYUP_ANGLE);
    }

    public static void shootAtVelocity(double velo){
        launcherPID.setSetpoint(velo);
        shoot(launcherPID.calculate(launcher.getSelectedSensorVelocity()));
    }

    public static void shoot(double power){
        
        
    }

   
    /*///
    *   Launcher PID Methods
    *///

    public static boolean launcherIsAtSetpoint()
    {
        return launcherPID.atSetpoint();
    }

    public static double getPosError()
    {
        return launcherPID.getPositionError();
    }


}
