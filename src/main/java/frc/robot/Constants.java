package frc.robot;

public class Constants
{
    public static final int ELEVATOR_TALON = 8;
	public static final int SHOOTER_TALON_RIGHT = 4;
	public static final int SHOOTER_TALON_LEFT = 11;
	public static final int kPIDLoopIdx = 0;
	public static final int kTimeoutMs = 30;

	public static final double SHOOTER_kP = 0.15;
	public static final double SHOOTER_kI = 0;
	public static final double SHOOTER_kD = 5;
	public static final double SHOOTER_FEED_FWD = .048;

	public static final int XBOX_DRIVER = 0;
	public static final int XBOX_MANIP = 1;

	public static final int SOLENOID_HOOD = 3;

	public static final double LAYUP_POSITION = -1;
	public static final double LAYUP_SPEED = 5000;

	public static final int TARGET_HEIGHT = 55;
	public static final double LIMELIGHT_ANGLE = 39.14;

	public static final int TALON_LEFTBACK = 15;
	public static final int TALON_LEFTFRONT = 13;
	public static final int TALON_RIGHTBACK = 1;
	public static final int TALON_RIGHTFRONT = 2; 
	public static final int TALON_RIGHTMIDDLE = 20;
	public static final int TALON_LEFTMIDDLE = 14;
	

	public static final int LED_CHANNEL = 3;
	public static final int SOLENOID_SHIFTER = 1;

	public static final double kP = 0;
	public static final double kI = 0;
	public static final double kD = 0;
	public static final int kIz = 0;

	public static final double kP2 = 0.04;
	public static final double kI2 = 0;
	public static final double kD2 = 0;
	public static final int kIz2 = 0;

	public static final double GYROkP = 0.02;
	public static final double GYROkI = 0;
	public static final double GYROkD = 0.0015;

	public static final double kMinOutput = -1;
	public static final double kMaxOutput = 1;
	public static final double DRIVETRAIN_TEST_RPM = 0;

	public static final double DTMAX_AUTO_SPEED = 0.28;

	//motor checks
	public static final double DT_ACCEPTED_MAX_CURRENT = 0; //at 80% power
	public static final double DT_ACCEPTED_MIN_CURRENT = 0;
	
	public static final double DT_ACCEPTED_MAX_VELOCITY = 0;
	public static final double DT_ACCEPTED_MIN_VELOCITY = 0;

	public static final double LAUNCHER_ACCEPTED_MAX_CURRENT = 5; //at 75% power
	public static final double LAUNCHER_ACCEPTED_MIN_CURRENT = 2.5; 
	
	public static final int LAUNCHER_ACCEPTED_MIN_VELOCITY = 19000; 
	public static final int LAUNCHER_ACCEPTED_MAX_VELOCITY = 22000; 

	public static final int TALONSRX_ACCEPTED_MIN_VELOCITY = 0; //at 100% power
	public static final int TALONSRX_ACCEPTED_MIN_CURRENT = 0;

	public static final double TALONSRX_ACCEPTED_MAX_CURRENT = 0;
	public static final int TALONSRX_ACCEPTED_MAX_VELOCITY = 0;


	//Intake
	public static final int INTAKE_BELT = 8;
	public static final int INTAKE_INGESTORBAR = 5;
	public static final int INTAKE_INGESTOR = 9;


	//Shooter formulas
	public static final double ACTUATOR_LAYUP = -1;			//minimum value at close range (layup value)
	public static final double POWER_LAYUP = 4000;				//minimum power at close range (layup value)
	public static final double ACTUATOR_SLOPE = 0;		
	public static final double POWER_SLOPE = 0;
	
	
	//climber
	public static final int leftClimber = 7;
	public static final int rightClimber = 12;
	public static final int CLIMBER_SOLENOID = 4;
	public static final int CLIMBER_STOPPER = 0;

	//sensor values
	public static final int COLOR_HIGH = 9;
	public static final double BELT_BALL_MOVED = -3500;		//encoder value belt moves up when ball is detected
	public static final int COLOR_LOW = 8;
	


	
	



	
	
	
    






}