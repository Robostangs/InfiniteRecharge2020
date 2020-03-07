package frc.robot;



import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.RobotTests.Drivetraintester;

public class Drivetrain extends Subsystems {
    public static Drivetrain instance;
    public CANSparkMax rightFront, leftFront, leftBack, rightBack, leftMiddle, rightMiddle;
    private CANEncoder encoderLeftFront, encoderRightFront;
    CANPIDController pidControllerLeftFront, pidControllerRightFront;
    private AHRS gyro;
    public boolean isPidEnabled = false;
    private PIDController gyropid;
    private Solenoid shifter;
    private Compressor airBoi;

    public double prevTime;
    public double prevError;
    public double setPoint;


    public static Drivetrain getInstance() {
        if (instance == null)
            instance = new Drivetrain();
        return instance;
    }

    private Drivetrain() {

        rightFront = new CANSparkMax(Constants.TALON_RIGHTFRONT, MotorType.kBrushless);
        leftFront = new CANSparkMax(Constants.TALON_LEFTFRONT, MotorType.kBrushless);
        leftBack = new CANSparkMax(Constants.TALON_LEFTBACK, MotorType.kBrushless);
        rightBack = new CANSparkMax(Constants.TALON_RIGHTBACK, MotorType.kBrushless);
        leftMiddle = new CANSparkMax(Constants.TALON_LEFTMIDDLE, MotorType.kBrushless);
        rightMiddle = new CANSparkMax(Constants.TALON_RIGHTMIDDLE, MotorType.kBrushless);

        encoderLeftFront = leftFront.getEncoder();
        encoderRightFront = rightFront.getEncoder();

        pidControllerLeftFront = leftFront.getPIDController();
        pidControllerRightFront = rightFront.getPIDController();
        shifter = new Solenoid(Constants.SOLENOID_SHIFTER);
        airBoi = new Compressor(0);

        gyro = new AHRS(SPI.Port.kMXP);
        gyropid = new PIDController(1, 0, 0);

        gyropid.enableContinuousInput(-180, 180);
        gyropid.setTolerance(1d);

        gyropid.setP(Constants.GYROkP);
        gyropid.setI(Constants.GYROkI);
        gyropid.setD(Constants.GYROkD);

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
        pidControllerRightFront.setP(Constants.kP2, 2);
        pidControllerRightFront.setI(Constants.kI2, 2);
        pidControllerRightFront.setD(Constants.kD2, 2);
        pidControllerRightFront.setIZone(Constants.kIz2, 2);

        pidControllerLeftFront.setP(Constants.kP2, 2);
        pidControllerLeftFront.setI(Constants.kI2, 2);
        pidControllerLeftFront.setD(Constants.kD2, 2);
        pidControllerLeftFront.setIZone(Constants.kIz2, 2);

        pidControllerLeftFront.setOutputRange(Constants.kMinOutput, Constants.kMaxOutput, 2);
        pidControllerRightFront.setOutputRange(Constants.kMinOutput, Constants.kMaxOutput, 2);

        /*************************************************************** */
        rightBack.follow(rightFront);
        rightMiddle.follow(rightFront);

        leftBack.follow(leftFront);
        leftMiddle.follow(leftFront);

        capSpeed(Constants.DTMAX_AUTO_SPEED);
    }

    public void drive(double powerLeft, double powerRight) {
        pidControllerRightFront.setReference(-powerRight, ControlType.kDutyCycle, 1);
        pidControllerLeftFront.setReference(powerLeft, ControlType.kDutyCycle, 1);
    }

    public void driveRotations(double rotLeft, double rotRight) {
        // on second pid slot   //change
        resetEncoders();
        pidControllerRightFront.setReference(rotLeft, ControlType.kPosition, 2);
        pidControllerLeftFront.setReference(-rotRight, ControlType.kPosition, 2);
    }

    public void driveNoPID(double left, double right) {
        leftFront.set(left);    //change
        rightFront.set(right);
    }

    public void arcadeDrive(double fwd, double tur) {
        drive(Utils.ensureRange(fwd + tur, -1d, 1d), Utils.ensureRange(fwd - tur, -1d, 1d));
    }

    public void capSpeed(double cap){
        pidControllerLeftFront.setOutputRange(-cap, cap, 2);
        pidControllerRightFront.setOutputRange(-cap, cap,2);
    }

    public double getSpeed(){
        return leftFront.getAppliedOutput();
    }
    
    public void compressorOn(){
        airBoi.start();
    }

    public void compressorOff(){
        airBoi.stop();
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

    public void resetEncoders(){
        encoderLeftFront.setPosition(0);
        encoderRightFront.setPosition(0);
    }

    // PID / auto
    public void setAngle(double angle) {
        setPoint = angle;

    }

    public void targetedDrive(double power) {
        arcadeDrive(power, pidCalculate(getAHRS()));
    }

    public void AutoTargetedDrive(double power) {
        arcadeDrive(power, autoPidCalculate(getAHRS()));
    }

    public void feedValues(){
        prevTime = System.nanoTime()/1000000000.0;

        prevError = getAHRS();
    }

    public double pidCalculate(double error){
        double timeChange = (System.nanoTime()/1000000000.0)-prevTime;
        if(timeChange == 0){
            timeChange = 100;
        }
        double changeError = error-prevError;
        
        return ((Constants.GYROkD*(changeError/timeChange))+(Constants.GYROkP*(error-setPoint)))*(1.0/3.0);

        //return (SmartDashboard.getNumber("Gyro kD",0)*(changeError/timeChange))+(SmartDashboard.getNumber("Gyro kP", 0)*(error-setPoint));
        
    }

    public double autoPidCalculate(double error){
        double timeChange = (System.nanoTime()/1000000000.0)-prevTime;
        if(timeChange == 0){
            timeChange = 100;
        }
        double changeError = error-prevError;
        
        return ((Constants.GYROkD*(changeError/timeChange))+(Constants.GYROkP*(error-setPoint)))*(1.0/3.0);

        //return (SmartDashboard.getNumber("Gyro kD",0)*(changeError/timeChange))+(SmartDashboard.getNumber("Gyro kP", 0)*(error-setPoint));
        
    }




   /* public void pidDisable() {
        System.out.println("PID Disabled");
        isPidEnabled = false;
    }

    public void pidEnable() {
        isPidEnabled = true;
    }

    public boolean ispidEnabled() {
        return isPidEnabled;
    }*/

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

    // gyro PID
    public void setgkP(double gkP) {
        gyropid.setP(gkP);
    }

    public void setgkI(double gkI) {
        gyropid.setI(gkI);
    }

    public void setgkD(double gkD) {
        gyropid.setD(gkD);
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

    public void stop() {
        rightFront.set(0);
        leftFront.set(0);
    }

    
 


}
