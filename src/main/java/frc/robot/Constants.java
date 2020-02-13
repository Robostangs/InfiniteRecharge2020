package frc.robot;

public class Constants
{
    public static final int ELEVATOR_TALON = 8;
	public static final int SHOOTER_TALON_RIGHT = 4;
	public static final int SHOOTER_TALON_LEFT = 11;
	public static final int kPIDLoopIdx = 0;
	public static final int kTimeoutMs = 30;

	public static final double SHOOTER_kP = .001;
	public static final double SHOOTER_kI = .001;
	public static final double SHOOTER_kD = 0;
	public static final double SHOOTER_FEED_FWD = .001;

	public static final int XBOX_DRIVER = 0;
	public static final int XBOX_MANIP = 1;

	public static final int LINEAR_ACTUATOR_RIGHT = 0;
	public static final int LINEAR_ACTUATOR_LEFT = 1;

	public static final double LAYUP_POSITION = -0.75;
	public static final double LAYUP_SPEED = 3000;

	public static final int TARGET_HEIGHT = 78;
	public static final double LIMELIGHT_ANGLE = 42;

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

	public static final double kP2 = 0;
	public static final double kI2 = 0;
	public static final double kD2 = 0;
	public static final int kIz2 = 0;

	public static final double GYROkP = 0;
	public static final double GYROkI = 0;
	public static final double GYROkD = 0;

	public static final double kMinOutput = -1;
	public static final double kMaxOutput = 1;
	public static final double DRIVETRAIN_TEST_RPM = 0;

	//motor checks
	public static final double MOTOR_ACCEPTED_MAX_CURRENT = 0; //at 80% power
	public static final double MOTOR_ACCEPTED_MIN_CURRENT = 0;
	
	public static final double MOTOR_ACCEPTED_MAX_VELOCITY = 0;
	public static final double MOTOR_ACCEPTED_MIN_VELOCITY = 0;

	public static final double LAUNCHER_ACCEPTED_MAX_CURRENT = 0; //at 75% power
	public static final double LAUNCHER_ACCEPTED_MIN_CURRENT = 0; 
	
	public static final int LAUNCHER_ACCEPTED_MIN_VELOCITY = 0; 
	public static final int LAUNCHER_ACCEPTED_MAX_VELOCITY = 0; 

	public static final int TALONSRX_ACCEPTED_MIN_VELOCITY = 0; //at 100% power
	public static final int TALONSRX_ACCEPTED_MIN_CURRENT = 0;

	public static final double TALONSRX_ACCEPTED_MAX_CURRENT = 0;
	public static final int TALONSRX_ACCEPTED_MAX_VELOCITY = 0;


	//Intake
	public static final int INTAKE_BELT = 8;
	public static final int INTAKE_INGESTORBAR = 0;
	public static final int INTAKE_INGESTOR = 0;


	//Shooter formulas
	public static final double ACTUATOR_LAYUP = 0;			//minimum value at close range (layup value)
	public static final double POWER_LAYUP = 0;				//minimum power at close range (layup value)
	public static final double ACTUATOR_SLOPE = 0;		
	public static final double POWER_SLOPE = 0;
	
	
	//climber
	public static final int leftClimber = 0;
	public static final int rightClimber = 0;



	
	
	
    






}