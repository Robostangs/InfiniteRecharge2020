package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleOpTester {

    /*
        This class is a clone of the TeleOp class
        that allows for the manual input of various
        values during Teleoperated mode...
    */
    public static Drivetrain dt = Drivetrain.getInstance();
    public static Shooter shooter = Shooter.getInstance();
    public static Intake intake = Intake.getInstance();
    public static Climber climber = Climber.getInstance();

    private static XBoxController driver;
    private static XBoxController manip;
    public static TeleOpTester instance;

    private static boolean previousSensorValue;
    private static boolean previousLowSensorValue;

    private static boolean movingDown;

    // led checks
    public static boolean elevLEDS;
    public static boolean shootingLEDS;

    public static TeleOpTester getInstance() {
        if (instance == null)
            instance = new TeleOpTester();
        return instance;
    }

    private TeleOpTester() {
        driver = new XBoxController(Constants.XBOX_DRIVER);
        manip = new XBoxController(Constants.XBOX_MANIP);

        // shooter defaults


        previousSensorValue = false;

        movingDown = false;

    }

    public static void run() {

        smartDashboard();
        



        Limelight.refresh();

        //P and I set manually in shuffleboard
        shooter.setkI(Constants.SHOOTER_kI);
        shooter.setkF(Constants.SHOOTER_FEED_FWD);



        /* DRIVER CONTROLS */
        
        if (driver.getLeftBumper()) {
            if (Limelight.getTv() == 1) {
                Limelight.ledsOn();
           

                dt.setAngle(dt.getAHRS() + Limelight.getTx());
                dt.targetedDrive(Utils.expodeadZone(-driver.getRightStickYAxis())); // allows driver to move back and forth during lineup


                if(manip.getYButton() == false){
                    shooter.hoodForward();
                }
           
                
               

            } else {
                Limelight.ledsFlash();
                //dt.pidDisable();
                dt.arcadeDrive(Utils.expodeadZone(-driver.getRightStickYAxis()), Utils.expodeadZone(-driver.getLeftStickXAxis()));
            }
        } else {

            if(manip.getRightTriggerButton() == false)
            {
                shooter.hoodBack();
            }


            Limelight.ledsOff();
            //dt.pidDisable();
            dt.arcadeDrive(Utils.expodeadZone(-driver.getRightStickYAxis()), Utils.expodeadZone(-driver.getLeftStickXAxis()));
        }


        //Climber
        if(Utils.expodeadZone(driver.getRightTriggerAxis()) > 0 && climber.getStopper() == true){

            if(driver.getRightTriggerAxis() > 0.3){
                climber.disengageRatchet();
                LEDs.setColor(-0.29); //blue light chase
            }
            else{
                climber.engageRatchet();
            }
            
            
            
            climber.climb(Utils.expodeadZone(-driver.getRightTriggerAxis()), Utils.expodeadZone(-driver.getRightTriggerAxis()));
        }
        else if(Utils.expodeadZone(driver.getLeftTriggerAxis()) > 0){
            LEDs.setColor(-0.39);
            climber.disengageRatchet();
            climber.climb(Utils.expodeadZone(driver.getLeftTriggerAxis()), Utils.expodeadZone(driver.getLeftTriggerAxis()));
        }
        else{
            LEDs.setColor(-0.39);
            climber.engageRatchet();
            climber.climb(0, 0);
        }


        /* MANIP CONTROLS */

        // Shooter
        if (manip.getRightTriggerButton()) {
            dt.compressorOff();

            shooter.hoodForward();
            Limelight.ledsOn();

            shooter.launch(SmartDashboard.getNumber("Jeff", 0));

            if(manip.getLeftStickYAxis() < -0.2){
                intake.beltMove(1.0);                   
            }
            else if(manip.getLeftStickYAxis() > 0.2){
                intake.beltMove(-manip.getLeftStickYAxis());
            }
            else{
                intake.beltMove(0);
            }

            /*
            if (shooter.getVelo() < speed - 5) {
                shootingLEDS = true;
                shooterLEDS = false;
            } else {
                shootingLEDS = false;
                shooterLEDS = true;
            }
            */

        } 
        else if (manip.getYButton()) {
            dt.compressorOff();

            //layups / close range
            shooter.hoodBack();
            shooter.launch((50.0 / 15.0) * Constants.LAYUP_SPEED);

            if(Utils.expodeadZone(manip.getLeftStickYAxis()) < -0.1){
                intake.beltMove(1.0);

            }
            else{
                intake.beltMove(0);
            }

        } else {
           
           
            dt.compressorOn();
            // automatic belt

            if (highSensorState().equals("new ball in") && manip.getRightTriggerButton() != true && lowSensorState().equals("no ball in")) {
                intake.resetBeltEncoder();
                LEDs.setColor(-0.29); // blue light chase
                intake.beltMove(0.6);
                shooter.launchNoPID(0.2);

            }
            else if(highSensorState().equals("no ball in") && lowSensorState().equals("same ball in")){
                intake.beltMove(-0.2);
            }
             else {
                shooter.launchNoPID(0);
                if (intake.getBeltEncoder() < Constants.BELT_BALL_MOVED && manip.getRightTriggerButton() != true || lowSensorState().equals("new ball in") && manip.getRightTriggerButton() != true) {
                    intake.setFeederBrake();
                    intake.beltMove(0);
                }
                else{
                    intake.setFeederCoast();
                }

            }

        }

        // intake bar and belt
        if (manip.getLeftBumper()) {
            intake.barDown(true);

           
            intake.ingest(Utils.expodeadZone(manip.getRightStickYAxis()));
            
        } else {
            intake.barDown(false);
            intake.ingest(0);
        }
        

    }

    public static void smartDashboard() {


        

        shooter.setkP(SmartDashboard.getNumber("Shooter kP", Constants.SHOOTER_kP));
        shooter.setkD(SmartDashboard.getNumber("Shooter kD", Constants.SHOOTER_kD));

        dt.setGkP(SmartDashboard.getNumber("Gyro kP", Constants.GYROkP));
        dt.setGkI(SmartDashboard.getNumber("Gyro kI", Constants.GYROkI));
        dt.setGkD(SmartDashboard.getNumber("Gyro kD", Constants.GYROkD));

        SmartDashboard.putNumber("Gyro #", dt.getAHRS());

        

        double distance = Utils.dist(Limelight.getTx(), Limelight.getTy());

        // Shooter values
        SmartDashboard.putNumber("distance", distance);
        SmartDashboard.putNumber("Velocity", (15.0 / 50.0) * shooter.getVelo());
    }


    
    public static String highSensorState() {

        if (previousSensorValue == false && intake.getHighSensor() == true) {
            System.out.println("new ball in");
            return "new ball in";
        }

        if (previousSensorValue == false && intake.getHighSensor() == false) {
            return "no ball in";

        }

        previousSensorValue = intake.getHighSensor();
        previousLowSensorValue = intake.getLowSensor();

        return "same ball in";

    }

        
    public static String lowSensorState() {

        if (previousLowSensorValue == false && intake.getLowSensor() == true) {
            System.out.println("new ball in");
            return "new ball in";
        }

        if (previousLowSensorValue == false && intake.getLowSensor() == false) {
            return "no ball in";

        }

        previousLowSensorValue = intake.getLowSensor();

        return "same ball in";

    }



    
}