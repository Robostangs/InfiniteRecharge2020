package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Servo;

import com.ctre.phoenix.motorcontrol.can.TalonFXPIDSetConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;





public class Shooter {

    public static Shooter instance;
    private static TalonFX launcherLeft, launcherRight;
    private static TalonSRX elevator;
    private static Servo servoLeft, servoRight;
 
    
    public static Shooter getInstance(){
        if(instance == null)
            instance = new Shooter();
        return instance;
    }

    public Shooter(){
        launcherLeft = new TalonFX(Constants.SHOOTER_TALON_LEFT);
        launcherRight = new TalonFX(Constants.SHOOTER_TALON_RIGHT);

        elevator = new TalonSRX(Constants.ELEVATOR_TALON);

        servoLeft = new Servo(Constants.LINEAR_ACTUATOR_LEFT);
        servoLeft.setBounds(2.0, 1.8, 1.5, 1.2, 1.0); 
        servoRight = new Servo(Constants.LINEAR_ACTUATOR_RIGHT);
        servoRight.setBounds(2.0, 1.8, 1.5, 1.2, 1.0); 

    
        launcherRight.follow(launcherLeft);
        
        
        //resets before setting configs
        launcherLeft.configFactoryDefault();

        //sets the feedback device (encoder) and type of loop (controlled)
        launcherLeft.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

        //allows setinverted to be called
        launcherLeft.setSensorPhase(true);

        //maxes and mins
        launcherLeft.configNominalOutputForward(0, Constants.kTimeoutMs);
        launcherLeft.configNominalOutputReverse(0, Constants.kTimeoutMs);
        launcherLeft.configPeakOutputForward(1, Constants.kTimeoutMs);
        launcherLeft.configPeakOutputReverse(-1, Constants.kTimeoutMs);

        //PID values
        launcherLeft.config_kP(Constants.kPIDLoopIdx, Constants.SHOOTER_kP, Constants.kTimeoutMs);
        launcherLeft.config_kI(Constants.kPIDLoopIdx, Constants.SHOOTER_kI, Constants.kTimeoutMs);
        launcherLeft.config_kD(Constants.kPIDLoopIdx, Constants.SHOOTER_kD, Constants.kTimeoutMs);
        launcherLeft.config_kF(Constants.kPIDLoopIdx, Constants.SHOOTER_FEED_FWD, Constants.kTimeoutMs);
        
        
    }

    public static void launch(double speed)
    {

        
        launcherLeft.set(ControlMode.Velocity, speed);
        launcherLeft.setInverted(true);
        //launcherRight.setInverted(true);
        
    }

    public static void pidDisable(double speed)
    {
        launcherLeft.set(ControlMode.PercentOutput, speed);
    }

    public static double getPercentOutput()
    {
        return launcherLeft.getMotorOutputPercent();
    }

   public static void elevate(double power)
    {
        elevator.set(ControlMode.PercentOutput, power);
    }

    //actuator
    public static void hoodAngle(double angle)
    {
        hoodPosition(Utils.angleToActuator(angle));
    }

    //value between 0 and 1
    public static void hoodPosition(double position)
    {
        servoLeft.setSpeed(position);
        servoRight.setSpeed(position);
    }


    public static void setkP(double x){
        launcherLeft.config_kP(Constants.kPIDLoopIdx, x, Constants.kTimeoutMs);
    }

    public static void setkI(double x){
        launcherLeft.config_kI(Constants.kPIDLoopIdx, x, Constants.kTimeoutMs);
    }

    public static void setkD(double x){
        launcherLeft.config_kD(Constants.kPIDLoopIdx, x, Constants.kTimeoutMs);
    }

    public static void setkF(double x){
        launcherLeft.config_kF(Constants.kPIDLoopIdx, x, Constants.kTimeoutMs);
    }

    public static double getVelo(){
        return launcherLeft.getSelectedSensorVelocity();
    }

    public static double getTemp(){
        return launcherLeft.getTemperature();
    }
}