package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import frc.RobotTests.ShooterTester;






public class Shooter extends Subsystems{

    public static Shooter instance;
    private  TalonFX launcherLeft, launcherRight;
    private  TalonSRX elevator;
    private  Solenoid hood;
 
    
    public static Shooter getInstance(){
        if(instance == null)
            instance = new Shooter();
        return instance;
    }

    private Shooter() {
        launcherLeft = new TalonFX(Constants.SHOOTER_TALON_LEFT);
        launcherRight = new TalonFX(Constants.SHOOTER_TALON_RIGHT);

        elevator = new TalonSRX(Constants.ELEVATOR_TALON);

        hood = new Solenoid(Constants.SOLENOID_HOOD);

    
        
        
        
        //resets before setting configs
        launcherLeft.configFactoryDefault();
        launcherRight.configFactoryDefault();

        //sets the feedback device (encoder) and type of loop (controlled)
        launcherLeft.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

        //setting sensorphase to true simply allows setinverted to be called
        launcherLeft.setSensorPhase(true);
        launcherRight.setSensorPhase(true);

        //maxes and mins
        launcherLeft.configNominalOutputForward(0, Constants.kTimeoutMs);
        launcherLeft.configNominalOutputReverse(0, Constants.kTimeoutMs);
        launcherLeft.configPeakOutputForward(1, Constants.kTimeoutMs);
        launcherLeft.configPeakOutputReverse(-1, Constants.kTimeoutMs);

        
        launcherLeft.config_kP(Constants.kPIDLoopIdx, Constants.SHOOTER_kP, Constants.kTimeoutMs);
        launcherLeft.config_kI(Constants.kPIDLoopIdx, Constants.SHOOTER_kI, Constants.kTimeoutMs);
        launcherLeft.config_kD(Constants.kPIDLoopIdx, Constants.SHOOTER_kD, Constants.kTimeoutMs);
        launcherLeft.config_kD(Constants.kPIDLoopIdx, Constants.SHOOTER_kD, Constants.kTimeoutMs);
        launcherLeft.config_kF(Constants.kPIDLoopIdx, Constants.SHOOTER_FEED_FWD, Constants.kTimeoutMs);
        
        launcherLeft.setInverted(true); 
        launcherRight.follow(launcherLeft); 
        
        
    }

    public  void launch(double speed)   //launches with pid
    {
        launcherLeft.set(ControlMode.Velocity, speed );
        launcherRight.follow(launcherLeft);
    }

    public  void launchNoPID(double speed) // percent output
    {
        launcherLeft.set(ControlMode.PercentOutput, speed);
        launcherRight.follow(launcherLeft);
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

    public  void hoodBack() //controlled by solenoid
    {
        hood.set(false);
    }

    public void hoodForward()
    {
        hood.set(true);
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
        return launcherLeft.getSelectedSensorVelocity() * 2;
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