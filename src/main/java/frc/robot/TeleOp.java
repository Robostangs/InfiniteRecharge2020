package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleOp{

    public static Drivetrain dt = new Drivetrain();

    private static XBoxController driver;
    private static XBoxController manip;
    public static TeleOp instance;

    private static double speed;
    private static double elevspeed;
    private static double hoodPosition;

    private static double kP;
    private static double kI;
    private static double kD;
    private static double kF;

    
    private static double dkP;
    private static double dkI;
    private static double dkD;
    
    private static double gkP;
    private static double gkI;
    private static double gkD;




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
        kP = 0.2;
        kI = 0.000001;
        kD = 5.1;
        kF = 0.048;

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
                    dt.targetedDrive(0); // keep 0
                    dt.setAngle(dt.getAHRS() + Limelight.getTx());
                }
            }
            else{
                dt.arcadeDrive(Utils.expodeadZone(driver.getLeftStickYAxis()), Utils.expodeadZone(driver.getRightStickXAxis()));
            }

            if(driver.getRightBumper()){
                dt.highGear();
            }
            else{
                dt.lowGear();
            }
        



        /* MANIP CONTROLS */
        Limelight.refresh();

        Shooter.setkD(kD);
        Shooter.setkI(kI);
        Shooter.setkP(kP);
        Shooter.setkF(kF);

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
            Shooter.launch((50.0 / 15.0) * speed);
            if (Shooter.getVelo() < speed - 10) {
                shootingLEDS = true;
                shooterLEDS = false;
            } else {
                shootingLEDS = false;
                shooterLEDS = true;
            }
        } else {
            Shooter.pidDisable(0);
            shooterLEDS = false;
        }

        // Elevator
        if (manip.getBButton()) {
            Shooter.elevate(elevspeed);
            elevLEDS = true;
        }
        if (manip.getXButton()) {
            Shooter.elevate(0);
            elevLEDS = false;
        }

        // Hood
        if (manip.getYButton()) {
            Shooter.hoodPosition(Constants.LAYUP_POSITION);
            Shooter.launch((50.0 / 15.0) * Constants.LAYUP_SPEED);

        } else {
            Shooter.hoodPosition(hoodPosition);
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


        SmartDashboard.putNumber("Shooter kP", kP);
        SmartDashboard.putNumber("Shooter kI", kI);
        SmartDashboard.putNumber("Shooter kD", kD);
        SmartDashboard.putNumber("Shooter kF", kF);
        SmartDashboard.putNumber("distance", distance);
        SmartDashboard.putNumber("LEDS", LEDs.getColor());

        SmartDashboard.putNumber("Shooter temperature temp", Shooter.getTemp());
        SmartDashboard.putNumber("% Output", Shooter.getPercentOutput());
        SmartDashboard.putNumber("Velocity", (15.0 / 50.0) * Shooter.getVelo());
    }

}