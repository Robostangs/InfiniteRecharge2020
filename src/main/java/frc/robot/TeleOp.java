package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleOp{
    public static XBoxController driver, manip;
    public static TeleOp instance;

    //for tuning
    public static boolean[] press = new boolean[8];

    public static int velocity;
    public static double angle;


    //led checks
    public static boolean elevLEDS;
    public static boolean shooterLEDS;
    public static boolean shootingLEDS;
    
    public static double speed;
    public static double elevspeed;
    public static double kP;
    public static double kI;
    public static double kD;
    public static double kF;
    

    public static TeleOp getInstance() {
		if (instance == null)
			instance = new TeleOp();
		return instance;
    }

    private TeleOp(){
		driver = new XBoxController(Constants.XBOX_DRIVER);
        manip = new XBoxController(Constants.XBOX_MANIP);
        speed = 0;
        elevspeed = 0.3;
        kP = 0.2;
        kI = 0.000001;
        kD = 5.1;
        kF = 0.048;

        elevLEDS = false;
        shooterLEDS = false;
        shootingLEDS = false;
        

    }
    
    public static void run(){

        /*if(false ){
            Limelight.lineUp();
            DriveTrain.targetedDrive(driver.getLeftStickYAxis());

        
        speed = SmartDashboard.getNumber("Jeff", speed);
        elevspeed = SmartDashboard.getNumber("elevspeed",elevspeed);
        hoodPosition = SmartDashboard.getNumber("Hood Position", 0);
        
       
        
        Limelight.refresh();

        SmartDashboard.putNumber("Tv",Limelight.getTv());
        double distance = Utils.dist(Limelight.getTx(),Limelight.getTy());

        SmartDashboard.putNumber("kP",kP);
        SmartDashboard.putNumber("kI",kI);
        SmartDashboard.putNumber("kD",kD);
        SmartDashboard.putNumber("kF",kF);
        SmartDashboard.putNumber("distance",distance);
        SmartDashboard.putNumber("LEDS",LEDs.getColor());


        SmartDashboard.putNumber("temp", Shooter.getTemp());
        SmartDashboard.putNumber("% Output", Shooter.getPercentOutput());
        SmartDashboard.putNumber("Velocity",(15.0/50.0)*Shooter.getVelo());
        Shooter.setkD(kD);
        Shooter.setkI(kI);
        Shooter.setkP(kP);
        Shooter.setkF(kF);

        
        if(shooterLEDS == true)
        {   
            LEDs.setColor(0.65);
        }
        else if(shootingLEDS == true){
            LEDs.setColor(0.63);
        }
        else if(elevLEDS == true){
            LEDs.setColor(-0.81);
        }
        else
        {
            LEDs.setColor(-0.99);
        }

        

        //Shooter
        if(driver.getAButton()){
            Shooter.launch((50.0/15.0)*speed);
            if(Shooter.getVelo() < speed - 10)
                {
                    shootingLEDS = true;
                    shooterLEDS = false;   
                }
                else{
                    shootingLEDS = false;
                    shooterLEDS = true;
                }
        }
        else{
            Shooter.pidDisable(0);
            shooterLEDS = false;
        }        
        
        //Elevator
        if(driver.getBButton()){
            Shooter.elevate(elevspeed);
            elevLEDS = true;
        }
        if(driver.getXButton()){
            Shooter.elevate(0);
            elevLEDS = false;
        }
     

        //Hood
        if(driver.getYButton())
        {
            Shooter.hoodPosition(Constants.LAYUP_POSITION);
            Shooter.launch((50.0/15.0)*Constants.LAYUP_SPEED);
                
        }
        if(false /*add button here){
            Shooter.elevate(Constants.ELEVATOR_REVERSE);
        }*/


        Limelight.refresh();
        SmartDashboard.putNumber("Velocity",velocity);
        SmartDashboard.putNumber("Angle",angle);
        SmartDashboard.putNumber("tv",Limelight.getTv());
        SmartDashboard.putNumber("dist",Utils.dist(Limelight.getTx(),Limelight.getTy()));
    }

    

}