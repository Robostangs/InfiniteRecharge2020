
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
    
    private static PIDController anglePID;
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


    //shooter angle PID
        anglePID = new PIDController(Constants.ANGLE_kP, Constants.ANGLE_kI, Constants.ANGLE_kD); //need period? 
        anglePID.enableContinuousInput(-180, 180); //likely subject to change
        anglePID.setTolerance(1d); //also subject to change

    //motor pid
        launcherPID = new PIDController(Constants.LAUCHER_kP, Constants.LAUNCHER_kI, Constants.LAUNCHER_kD);
        launcherPID.enableContinuousInput(-180, 180); 
        launcherPID.setTolerance(1d); 
        
    }

    public static double setFarAngle(double power){
        //anglePID.calculate(shooterAngle.get(), solve for angle in trajectory UAM)   Run this whenever Manip has limelight activated
        //if(angleIsAtSetpoint() = true)
            //disable pid if possible
        return 0;
    }

    public static double setLayupAngle(double setAngle){
        return anglePID.calculate(shooterAngle.get(), Constants.SHOOTER_LAYUP_ANGLE);
    }

    public static void shoot(double power){
        
        
    }

    

    /*///
     *   Shooter Angle PID Methods
    *////

    public static boolean angleIsAtSetpoint() 
    {
        return anglePID.atSetpoint();
    }

    public static double getAngleError()
    {
        return anglePID.getPositionError();
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
