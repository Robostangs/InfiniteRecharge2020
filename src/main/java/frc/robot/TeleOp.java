package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleOp{
    public static XBoxController driver, manip;
    public static TeleOp instance;

    //for tuning
    public static boolean[] press = new boolean[8];

    public static double power;
    public static double angle;


    public static TeleOp getInstance() {
		if (instance == null)
			instance = new TeleOp();
		return instance;
    }

    private TeleOp(){
		driver = new XBoxController(Constants.XBOX_DRIVER);
        manip = new XBoxController(Constants.XBOX_MANIP);
        power = 0;
        angle = 0;
    }
    
    public static void run(){

        if(DriveTrain.ispidEnabled()){
            DriveTrain.targetedDrive(driver.getLeftStickYAxis());

        }
        else{
            DriveTrain.arcadeDrive(driver.getLeftStickYAxis(),driver.getRightStickXAxis());
        }




        if(manip.getYButton()){
            if(!press[0]){
                power+=.01;
                press[0] = true;
            }
        }
        else{
            press[0] = false;
        }
        if(manip.getAButton()){
            if(!press[1]){
                power-=.01;
                press[1] = true;
            }
        }
        else{
            press[1] = false;
        }
        if(manip.getBButton()){
            if(!press[2]){
                power+=.1;
                press[2] = true;
            }
        }
        else{
            press[2] = false;
        }
        if(manip.getXButton()){
            if(!press[3]){
                power-=.1;
                press[3] = true;
            }
        }
        else{
            press[3] = false;
        }
        if(manip.getRightBumper()){
            if(!press[4]){
                angle+=.5;
                press[4] = true;
            }
        }
        else{
            press[4] = false;
        }
        if(manip.getRightTriggerButton()){
            if(!press[5]){
                angle+=5;
                press[5] = true;
            }
        }
        else{
            press[5] = false;
        }
        if(manip.getLeftBumper()){
            if(!press[6]){
                angle-=.5;
                press[6] = true;
            }
        }
        else{
            press[6] = false;
        }
        if(manip.getLeftTriggerButton()){
            if(!press[7]){
                angle-=5;
                press[7] = true;
            }
        }
        else{
            press[7] = false;
        }

        SmartDashboard.putNumber("Power",power);
        SmartDashboard.putNumber("Angle",angle);

    }

    

}