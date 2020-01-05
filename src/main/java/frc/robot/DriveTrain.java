package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
//import com.ctre.pheonix.motorcontrol.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Solenoid;

public class DriveTrain
{
    public static DriveTrain instance;
    static CANSparkMax rightFront, leftFront, rightBack, leftBack;
    private static CANPIDController pidControllerLeftFront, pidControllerRightFront;
    private static CANEncoder encoderLeftFront, encoderRightFront;
    private static AHRS hyro;
    private static Solenoid shifter;
    private static PIDController hyropid;


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
        /*hyropid = new PIDController(1, 0, 0, hyro, this);
        hyropid.setInputRange(-180d, 180d);
        hyropid.setOutputRange(-1.0, 1.0);

        hyropid.setAbsoluteTolerance(2d);
        hyropid.setContinuous(true);*/

        shifter = new Solenoid(Constants.SOLENOID_SHIFTER);
        
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

    public static void shiftUp(){
        shifter.set(true);
    }

    public static void shiftDown(){
        shifter.set(false);
    }

    public static boolean getShifted(){
        return shifter.get();
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
		/*hyropid.setSetpoint(angle);
		if(!hyropid.isEnabled()){
			System.out.println("PID Enabled");
			hyropid.reset();
			hyropid.enable();
		}	*/
    }
    
	//@Override
	/*public void pidWrite(double output) {
		// TODO Auto-generated method stub
		if (Math.abs(hyropid.getError()) < 5d) {
			hyropid.setPID(hyropid.getP(), .001, 0);
		} else {
			// I Zone
			hyropid.setPID(hyropid.getP(), 0, 0);
        }

        
        // if(output != 0){
        DriveTrain.arcadeDrive(output, 0);
        // }
	}*/

   /* public static void pidDisable(){
        System.out.println("PID Disabled");
        //hyropid.disable();
    }*/

    /*public static void pidEnable(){
        //hyropid.enable();
    }*/
    /*public static boolean ispidEnabled(){
        return hyropid.isEnabled();
    }*/



}