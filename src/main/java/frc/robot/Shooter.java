package frc.robot;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Servo;
import frc.RobotTests.ShooterTester;






public class Shooter extends Subsystems{

    public static Shooter instance;
    private  TalonFX launcherLeft, launcherRight;
    private  TalonSRX elevator;
    private  Servo servoLeft, servoRight;
 
    
    public static Shooter getInstance(){
        if(instance == null)
            instance = new Shooter();
        return instance;
    }

    public Shooter() {
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

        
        launcherLeft.config_kP(Constants.kPIDLoopIdx, Constants.SHOOTER_kP, Constants.kTimeoutMs);
        launcherLeft.config_kI(Constants.kPIDLoopIdx, Constants.SHOOTER_kI, Constants.kTimeoutMs);
        launcherLeft.config_kD(Constants.kPIDLoopIdx, Constants.SHOOTER_kD, Constants.kTimeoutMs);
        launcherLeft.config_kF(Constants.kPIDLoopIdx, Constants.SHOOTER_FEED_FWD, Constants.kTimeoutMs);
        
        
    }

    public  void launch(double speed)
    {
        launcherLeft.set(ControlMode.Velocity, speed);
        launcherLeft.setInverted(true);  
    }

    public  void launchNoPID(double leftSpeed, double rightSpeed) // percent output
    {
        launcherLeft.set(ControlMode.PercentOutput, leftSpeed);
        launcherRight.set(ControlMode.PercentOutput, rightSpeed);
    }

    public  void pidDisable(double speed)
    {
        launcherLeft.set(ControlMode.PercentOutput, speed);
    }

    public  double getPercentOutput()
    {
        return launcherLeft.getMotorOutputPercent();
    }

   public  void elevate(double power)
    {
        elevator.set(ControlMode.PercentOutput, power);
    }

    public  double elevatePercent()
    {
        return elevator.getMotorOutputPercent();
    }

    //value between 0 and 1
    public  void hoodPosition(double position)
    {
        servoLeft.setSpeed(position);
        servoRight.setSpeed(position);
    }


    public  void setkP(double x){
        launcherLeft.config_kP(Constants.kPIDLoopIdx, x, Constants.kTimeoutMs);
    }

    public  void setkI(double x){
        launcherLeft.config_kI(Constants.kPIDLoopIdx, x, Constants.kTimeoutMs);
    }

    public  void setkD(double x){
        launcherLeft.config_kD(Constants.kPIDLoopIdx, x, Constants.kTimeoutMs);
    }

    public  void setkF(double x){
        launcherLeft.config_kF(Constants.kPIDLoopIdx, x, Constants.kTimeoutMs);
    }

    public  double getVelo(){
        return launcherLeft.getSelectedSensorVelocity();
    }

    public  double getVeloRight(){
        return launcherLeft.getSelectedSensorVelocity();
    }

    public  double getTemp(){
        return launcherLeft.getTemperature();
    }

    public double getRighTemp(){
        return launcherRight.getTemperature();
    }

    @Override
    public void checkStart() {
        ShooterTester.shooterTest(launcherLeft, launcherRight); 
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub

    }
}