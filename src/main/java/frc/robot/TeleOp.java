package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleOp{
    public static XBoxController driver, manip;
    public static TeleOp instance;

    //for tuning
    public static boolean[] press = new boolean[8];

    public static int velocity;
    public static double angle;


    public static TeleOp getInstance() {
		if (instance == null)
			instance = new TeleOp();
		return instance;
    }

    private TeleOp(){
		driver = new XBoxController(Constants.XBOX_DRIVER);
        manip = new XBoxController(Constants.XBOX_MANIP);
        velocity = 0;
        angle = 0;
    }
    
    public static void run(){

        /*if(false ){
            Limelight.lineUp();
            DriveTrain.targetedDrive(driver.getLeftStickYAxis());

        }
        else{
            DriveTrain.arcadeDrive(driver.getLeftStickYAxis(),driver.getRightStickXAxis());
        }*/

        if(false /*add button control here*/){
            Shooter.elevate(Constants.ELEVATOR_SPEED);
        }
        if(false /*add button here*/){
            Shooter.elevate(Constants.ELEVATOR_REVERSE);
        }


        Limelight.refresh();
        SmartDashboard.putNumber("Velocity",velocity);
        SmartDashboard.putNumber("Angle",angle);
        SmartDashboard.putNumber("tv",Limelight.getTv());
        SmartDashboard.putNumber("dist",Utils.dist(Limelight.getTx(),Limelight.getTy()));
    }

    

}