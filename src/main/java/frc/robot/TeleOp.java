package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleOp{

    public static Drivetrain dt = Drivetrain.getInstance();
    public static Shooter shooter = Shooter.getInstance();
    public static Intake intake = Intake.getInstance();

    private static XBoxController driver;
    private static XBoxController manip;
    public static TeleOp instance;

    private static double speed;
    private static double elevspeed;

    private static double dkP;
    private static double dkI;
    private static double dkD;
    
    private static double gkP;
    private static double gkI;
    private static double gkD;

    private static double hoodPosition;




    //led checks
    public static boolean elevLEDS;
    public static boolean shooterLEDS;
    public static boolean shootingLEDS;

    

    public static TeleOp getInstance(){
        if(instance == null)
            instance = new TeleOp();
        return instance;
    }

    private TeleOp(){
        
        driver = new XBoxController(Constants.XBOX_DRIVER);
        manip = new XBoxController(Constants.XBOX_MANIP);

        //shooter defaults
        speed = 0;
        elevspeed = 0.3;


        elevLEDS = false;
        shooterLEDS = false;
        shootingLEDS = false;
        
        //driver defaults
        dkP = 0;
        dkI = 0;
        dkD = 0;
        
        gkP = 0;
        gkI = 0;
        gkD = 0;
    
        
        

    }

    public static void run(){

        smartDashboard();
        

        /* DRIVER CONTROLS */

            if(driver.getLeftBumper())
            {
                if(Limelight.getTv() == 1){
                    LEDs.setColor(-0.31); //red light chase
                    //set speed limit on driver during lineup?
                    dt.setAngle(dt.getAHRS() + Limelight.getTx());

                    dt.targetedDrive(Utils.expodeadZone(driver.getRightStickYAxis() * 0.75)); // allows driver to move back and forth
                   

                
                    //shooter.hoodPosition(Utils.distToActuator(Utils.dist(Limelight.getTx(), Limelight.getTy()), Constants.ACTUATOR_SLOPE, Constants.LAYUP_POSITION));


                }
                else{
                    //shooter.hoodPosition(Constants.LAYUP_POSITION);
                }
            }
            else{
                dt.arcadeDrive(Utils.expodeadZone(-driver.getRightStickYAxis()), Utils.expodeadZone(-driver.getLeftStickXAxis()));
            }

            if(driver.getRightBumper()){
                dt.highGear();
            }
            else{
                dt.lowGear();
            }
        




        
    /* MANIP CONTROLS */

        Limelight.refresh();

        //SHOOTER PID VALUES   
        shooter.setkD(SmartDashboard.getNumber("Shooter kD", 0));
        shooter.setkI(SmartDashboard.getNumber("Shooter kI", 0));
        shooter.setkP(SmartDashboard.getNumber("Shooter kP", 0));
        shooter.setkF(SmartDashboard.getNumber("Shooter kF", 0));


        //LEDS
        if (shooterLEDS == true) {
            LEDs.setColor(0.65);
        } else if (shootingLEDS == true) {
            LEDs.setColor(0.63);
        } else if (elevLEDS == true) {
            LEDs.setColor(-0.81);
        } else {
            LEDs.setColor(-0.99);
        }

        // Shooter
        if (manip.getAButton()) {

            shooter.launch((50.0/15.0)*speed);

            //LEDS to indicate if up to speed
            if(shooter.getVelo() < speed - 5){
                shootingLEDS = true;
                shooterLEDS = false;   
            }
            else{
                shootingLEDS = false;
                shooterLEDS = true;
            }


            /* AFTER TESTING ////
            if(Limelight.getTv() == 1) {
                shooter.launch((50.0/15.0)*Utils.distToActuator(Utils.dist(Limelight.getTx(), Limelight.getTy()), Constants.POWER_SLOPE, Constants.POWER_LAYUP));

            }
            /* */

        }
        else{
            //when intaking, shooter does not launch balls
            shooter.launch(-manip.getLeftStickYAxis()*0.25);
        }
        

  
        
        //intake bar
        if(manip.getLeftBumper()) {
            intake.barDown(true);
            intake.ingest(manip.getRightStickYAxis()); 
        }
        else{
            intake.barDown(false);
        }



        // Layup
        if (manip.getYButton()) {
            shooter.hoodPosition(Constants.LAYUP_POSITION);
            intake.beltMove(0.8);
            shooter.launch((50.0 / 15.0) * Constants.LAYUP_SPEED);

        } 
        else{
            //elevator
            shooter.hoodPosition(hoodPosition); //for testing
            intake.beltMove(manip.getLeftStickYAxis());

        }
        
    }




    public static void smartDashboard() {
        dkP = SmartDashboard.getNumber("Driver kP", dkP);
        dkI = SmartDashboard.getNumber("Driver kI", dkI);
        dkD = SmartDashboard.getNumber("Driver kD", dkD);

        gkP = SmartDashboard.getNumber("Gyro kD", gkP);
        gkI = SmartDashboard.getNumber("Gyro kI", gkI);
        gkD = SmartDashboard.getNumber("Gyro kD", gkD);

        //drivetrain set values
        dt.setkP(dkP);
        dt.setkI(dkI);
        dt.setkD(dkD);

        dt.setgkP(gkP);
        dt.setgkI(gkI);
        dt.setgkD(gkD);
        


        speed = SmartDashboard.getNumber("Jeff", speed);
        elevspeed = SmartDashboard.getNumber("elevspeed", elevspeed);
        hoodPosition = SmartDashboard.getNumber("Hood Position", 0);

        SmartDashboard.putNumber("Tv", Limelight.getTv());
        double distance = Utils.dist(Limelight.getTx(), Limelight.getTy());


        //Shooter values
        SmartDashboard.putNumber("distance", distance);
        SmartDashboard.putNumber("% Output", shooter.getPercentOutput());
        SmartDashboard.putNumber("Velocity", (15.0 / 50.0) * shooter.getVelo());
    }

}