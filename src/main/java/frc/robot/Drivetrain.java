package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.controller.PIDController;

public class Drivetrain
{
    public static Drivetrain instance;
    static CANSparkMax rightFront, leftFront, leftBack, rightBack, leftMiddle, rightMiddle;

    private static CANPIDController pidControllerLeftFront, pidControllerRightFront;
    private static CANEncoder encoderLeftFront, encoderRightFront;
    private static AHRS gyro;
    public static boolean isPidEnabled = false;
    private static PIDController gyropid;
    private static Solenoid shifter;
    

    public static Drivetrain getInstance() {
        if (instance == null)
            instance = new Drivetrain();
        return instance;
    }

    public Drivetrain(){
        rightFront = new CANSparkMax(Constants.TALON_RIGHTFRONT, MotorType.kBrushless);
        leftFront = new CANSparkMax(Constants.TALON_LEFTFRONT, MotorType.kBrushless);
        leftBack = new CANSparkMax(Constants.TALON_LEFTBACK, MotorType.kBrushless);
        rightBack = new CANSparkMax(Constants.TALON_RIGHTBACK, MotorType.kBrushless);
        leftMiddle = new CANSparkMax(Constants.TALON_LEFTMIDDLE, MotorType.kBrushless);
        leftMiddle = new CANSparkMax(Constants.TALON_RIGHTMIDDLE, MotorType.kBrushless);


        shifter = new Solenoid(Constants.SOLENOID_SHIFTER);
      
        gyro = new AHRS(SPI.Port.kMXP);
        gyropid = new PIDController(1, 0, 0);

        gyropid.enableContinuousInput(-180, 180);
        gyropid.setTolerance(1d);

        pidControllerRightFront = rightFront.getPIDController();
        pidControllerLeftFront = leftFront.getPIDController();

        

        encoderRightFront = rightFront.getEncoder();
        encoderLeftFront = leftFront.getEncoder();
        
        /******************************************************* */
        //Slot 1
        pidControllerRightFront.setP(Constants.kP, 1);
        pidControllerRightFront.setI(Constants.kI, 1);
        pidControllerRightFront.setD(Constants.kD, 1);
        pidControllerRightFront.setIZone(Constants.kIz, 1);

        pidControllerLeftFront.setP(Constants.kP, 1);
        pidControllerLeftFront.setI(Constants.kI, 1);
        pidControllerLeftFront.setD(Constants.kD, 1);
        pidControllerLeftFront.setIZone(Constants.kIz, 1);

        pidControllerLeftFront.setOutputRange(Constants.kMinOutput, Constants.kMaxOutput, 1);
        pidControllerRightFront.setOutputRange(Constants.kMinOutput, Constants.kMaxOutput, 1);

        //Slot 2
        pidControllerRightFront.setP(Constants.kP, 2);
        pidControllerRightFront.setI(Constants.kI, 2);
        pidControllerRightFront.setD(Constants.kD, 2);
        pidControllerRightFront.setIZone(Constants.kIz, 2);

        pidControllerLeftFront.setP(Constants.kP, 2);
        pidControllerLeftFront.setI(Constants.kI, 2);
        pidControllerLeftFront.setD(Constants.kD, 2);
        pidControllerLeftFront.setIZone(Constants.kIz, 2);

        pidControllerLeftFront.setOutputRange(Constants.kMinOutput, Constants.kMaxOutput, 2);
        pidControllerRightFront.setOutputRange(Constants.kMinOutput, Constants.kMaxOutput, 2);

        /*************************************************************** */
        rightBack.follow(rightFront);
        rightMiddle.follow(rightFront);

        leftBack.follow(leftBack);
        leftMiddle.follow(leftFront);
    }

    public static void drive(double powerLeft, double powerRight){
        pidControllerRightFront.setReference(powerRight, ControlType.kDutyCycle, 1);
        pidControllerLeftFront.setReference(powerLeft, ControlType.kDutyCycle, 1);
    }

    public static void driveRotations(double rotLeft, double rotRight){
        //full speed?
        pidControllerRightFront.setReference(rotLeft, ControlType.kPosition, 2);
        pidControllerLeftFront.setReference(rotLeft, ControlType.kPosition, 2);
    }

    public static void stop(){
        rightFront.set(0);
        leftFront.set(0);
    }

    public static void arcadeDrive(double fwd, double tur){
        drive(Utils.ensureRange(fwd + tur, -1d, 1d), Utils.ensureRange(fwd - tur, -1, 1));
    }

    public static void highGear(){
        shifter.set(true);
    }

    public static void lowGear(){
        shifter.set(false);
    }

    public static double getAHRS(){
        return gyro.getAngle();
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

    //PID / auto
    public static void setAngle(double angle){
        gyropid.setSetpoint(angle);
        if(!ispidEnabled()){
            System.out.println("PID Enabled");
            gyropid.reset();
            pidEnable();
        }
        
    }

    public static void targetedDrive(double power){
        if(isPidEnabled){
            Drivetrain.arcadeDrive(power, gyropid.calculate(Drivetrain.getAHRS()));
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

	public static void setkD(double kD) {
        pidControllerLeftFront.setD(kD);
        pidControllerRightFront.setD(kD);
	}

	public static void setkI(double kI) {
        pidControllerLeftFront.setI(kI);
        pidControllerRightFront.setI(kI);
	}

	public static void setkP(double kP) {
        pidControllerLeftFront.setP(kP);
        pidControllerRightFront.setP(kP);
    }


    //pid slot 2
    public static void setkP2(double kP) {
        pidControllerLeftFront.setP(kP, 2);
        pidControllerRightFront.setP(kP, 2);
    }

    public static void setkI2(double kI){
        pidControllerLeftFront.setI(kI, 2);
        pidControllerRightFront.setI(kI, 2);
    }

    public static void setkD2(double kD){
        pidControllerLeftFront.setD(kD, 2);
        pidControllerRightFront.setD(kD, 2);
    }
    
    
 


}
