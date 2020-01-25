package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Teleop{
    private static XBoxController driver;
    private static XBoxController manip;
    public static Teleop instance;

    private static double kP;
    private static double kI;
    private static double kD;


    

    public static Teleop getInstance(){
        if(instance == null)
            instance = new Teleop();
        return instance;
    }

    private Teleop(){
        
        driver = new XBoxController(Constants.XBOX_DRIVER);
        manip = new XBoxController(Constants.XBOX_MANIP);
        kP = 0;
        kI = 0;
        kD = 0;
        

        

    }

    public static void run(){

        SmartDashboard.putNumber("Gyro Angle", Drivetrain.getAHRS());
        SmartDashboard.putNumber("Left Velocity", Drivetrain.getVelocityLeft());
        SmartDashboard.putNumber("Right Velocity", Drivetrain.getVelocityRight());
        
        
        Drivetrain.setkD(SmartDashboard.getNumber("kP", kP));
        Drivetrain.setkI(SmartDashboard.getNumber("kI", kI));
        Drivetrain.setkP(SmartDashboard.getNumber("kD", kD));
   

        if(driver.getLeftBumper())
        {
            if(Limelight.getTv() == 1){
                Drivetrain.setAngle(Drivetrain.getAHRS() + Limelight.getTx()); 
                Drivetrain.targetedDrive(0); //keep 0
            }
            
        }
        else{
            Drivetrain.arcadeDrive(Utils.expodeadZone(driver.getLeftStickYAxis()), Utils.expodeadZone(driver.getRightStickXAxis()));
        }

        if(driver.getRightBumper())
        {
            Drivetrain.highGear();
        }
        else{
            Drivetrain.lowGear();
        }
        
        
        
    }

}