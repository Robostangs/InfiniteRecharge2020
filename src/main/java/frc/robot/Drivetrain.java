package frc.robot;


import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.RobotTests.Drivetraintester;

public class Drivetrain extends Subsystems {
    public static Drivetrain instance;
    public CANSparkMax rightFront, leftFront, leftBack, rightBack, leftMiddle, rightMiddle;

    private CANPIDController pidControllerLeftFront, pidControllerRightFront;
    private CANEncoder encoderLeftFront, encoderRightFront;
    private AHRS gyro;
    public boolean isPidEnabled = false;
    private PIDController gyropid;
    private Solenoid shifter;


    public static Drivetrain getInstance() {
        if (instance == null)
            instance = new Drivetrain();
        return instance;
    }

    public Drivetrain() {
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
        // Slot 1
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

        // Slot 2
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

    public void drive(double powerLeft, double powerRight) {
        pidControllerRightFront.setReference(powerRight, ControlType.kDutyCycle, 1);
        pidControllerLeftFront.setReference(powerLeft, ControlType.kDutyCycle, 1);
    }

    public void driveRotations(double rotLeft, double rotRight) {
        // on second pid slot
        pidControllerRightFront.setReference(rotLeft, ControlType.kPosition, 2);
        pidControllerLeftFront.setReference(rotLeft, ControlType.kPosition, 2);
    }

    public void driveNoPID(double left, double right) {
        leftFront.set(left);
        rightFront.set(right);
    }

    public void stop() {
        rightFront.set(0);
        leftFront.set(0);
    }

    public void arcadeDrive(double fwd, double tur) {
        drive(Utils.ensureRange(fwd + tur, -1d, 1d), Utils.ensureRange(fwd - tur, -1, 1));
    }

    public void highGear() {
        shifter.set(true);
    }

    public void lowGear() {
        shifter.set(false);
    }

    public double getAHRS() {
        return gyro.getAngle();
    }

    public double getEncoderRight() {
        return encoderRightFront.getPosition();
    }

    public double getEncoderLeft() {

        return encoderLeftFront.getPosition();

    }

    public double getVelocityRight() {
        return encoderLeftFront.getVelocity();
    }

    public double getVelocityLeft() {
        return encoderLeftFront.getVelocity();
    }

    // PID / auto
    public void setAngle(double angle) {
        gyropid.setSetpoint(angle);
        if (!ispidEnabled()) {
            System.out.println("PID Enabled");
            gyropid.reset();
            pidEnable();
        }

    }

    public void targetedDrive(double power) {
        Drivetrain Drivetrain = new Drivetrain();
        if (isPidEnabled) {
            Drivetrain.arcadeDrive(power, gyropid.calculate(Drivetrain.getAHRS()));
        }
    }

    public void pidDisable() {
        System.out.println("PID Disabled");
        isPidEnabled = false;
    }

    public void pidEnable() {
        isPidEnabled = true;
    }

    public boolean ispidEnabled() {
        return isPidEnabled;
    }

    public void setkD(double kD) {
        pidControllerLeftFront.setD(kD);
        pidControllerRightFront.setD(kD);
    }

    public void setkI(double kI) {
        pidControllerLeftFront.setI(kI);
        pidControllerRightFront.setI(kI);
    }

    public void setkP(double kP) {
        pidControllerLeftFront.setP(kP);
        pidControllerRightFront.setP(kP);
    }

    // pid slot 2
    public void setkP2(double kP) {
        pidControllerLeftFront.setP(kP, 2);
        pidControllerRightFront.setP(kP, 2);
    }

    public void setkI2(double kI) {
        pidControllerLeftFront.setI(kI, 2);
        pidControllerRightFront.setI(kI, 2);
    }

    public void setkD2(double kD) {
        pidControllerLeftFront.setD(kD, 2);
        pidControllerRightFront.setD(kD, 2);
       
    }


    
    @Override
    public void checkStart() {
        Drivetraintester.dtTester(leftFront, leftMiddle, leftBack, rightFront, rightMiddle, rightBack);
        
        

    }
    
    
 


}
