package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
//import com.ctre.pheonix.motorcontrol.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.Solenoid;


public class DriveTrain
{
    public static DriveTrain instance;
    static CANSparkMax rightFront, leftFront, rightBack, leftBack;
    private static CANPIDController pidControllerLeftFront, pidControllerRightFront;
    private static CANEncoder encoderLeftFront, encoderRightFront;
    private static AHRS hyro;
    private static PIDController hyropid;
    private static boolean isPidEnabled = false;


    public static DriveTrain getInstance() {
        if (instance == null)
            instance = new DriveTrain();
        return instance;
    }

    public DriveTrain(){
        rightFront = new CANSparkMax(Constants.TALON_RIGHTFRONT, MotorType.kBrushless);
        leftFront = new CANSparkMax(Constants.TALON_LEFTFRONT, MotorType.kBrushless);
        leftBack = new CANSparkMax(Constants.TALON_LEFTBACK, MotorType.kBrushless);
        rightBack = new CANSparkMax(Constants.TALON_RIGHTBACK, MotorType.kBrushless);
       
        hyro = new AHRS(SPI.Port.kMXP);
        hyropid = new PIDController(Constants.HYRO_kP, Constants.HYRO_kI, Constants.HYRO_kD);

        hyropid.enableContinuousInput(-180, 180);
        hyropid.setTolerance(1d);
        
        pidControllerRightFront = rightFront.getPIDController();
        pidControllerLeftFront = leftFront.getPIDController();
        

        encoderRightFront = rightFront.getEncoder();
        encoderLeftFront = leftFront.getEncoder();


        pidControllerRightFront.setP(Constants.kP);
        pidControllerRightFront.setI(Constants.kI);
        pidControllerRightFront.setD(Constants.kD);
        pidControllerRightFront.setIZone(Constants.kIz);

        pidControllerLeftFront.setP(Constants.kP);
        pidControllerLeftFront.setI(Constants.kI);
        pidControllerLeftFront.setD(Constants.kD);
        pidControllerLeftFront.setIZone(Constants.kIz);

        pidControllerLeftFront.setOutputRange(Constants.kMinOutput, Constants.kMaxOutput);
        pidControllerRightFront.setOutputRange(Constants.kMinOutput, Constants.kMaxOutput);

        
        rightBack.follow(rightFront);
        leftBack.follow(leftFront);
    }


    public static void drive(double powerLeft, double powerRight){
        pidControllerRightFront.setReference(powerRight, ControlType.kDutyCycle);
        pidControllerLeftFront.setReference(powerLeft, ControlType.kDutyCycle);
    }

    public static void drivePower(double powerLeft, double powerRight){
        rightFront.set(powerRight);
        leftFront.set(powerLeft);
    }

    public static void arcadeDrive(double fwd, double tur) {
        //Arcade Drive      
		drive(Utils.ensureRange(fwd + tur, -1d, 1d), Utils.ensureRange(fwd - tur, -1d, 1d));
    }

    public static double getAHRS(){
        return hyro.getAngle();
    }

    public static double getEncoderRight(){
        return encoderRightFront.getPosition();
    }

    public static double getEncoderLeft(){
        return encoderLeftFront.getPosition();
    }

    public static double getVelocityRight(){
        return encoderLeftFront.getVelocity();
    }

    public static double getVelocityLeft(){
        return encoderLeftFront.getVelocity();
    }

    public static double getAvgVelocity(){
        //System.out.println("LEFT: " + encoderLeftFront.getVelocity());
        return (encoderLeftFront.getVelocity());
    }

    public static void turnToAngle(double angle){
		hyropid.setSetpoint(angle);
		if(!ispidEnabled()){
			System.out.println("PID Enabled");
			hyropid.reset();
			pidEnable();
		}	
    }
    
	public static void targetedDrive(double power){

        if(isPidEnabled){
            DriveTrain.arcadeDrive(hyropid.calculate(getAHRS()),power);
        }
	}

   public static void pidDisable(){
        System.out.println("PID Disabled");
        isPidEnabled = false;
    }

    public static void pidEnable(){
        isPidEnabled = true;
    }
    public static boolean ispidEnabled(){
        return isPidEnabled;
    }



}