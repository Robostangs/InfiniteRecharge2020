package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleOp {

    public static Drivetrain dt = Drivetrain.getInstance();
    public static Shooter shooter = Shooter.getInstance();
    public static Intake intake = Intake.getInstance();
    public static Climber climber = Climber.getInstance();

    private static XBoxController driver;
    private static XBoxController manip;
    public static TeleOp instance;

    private static boolean previousSensorValue;
    private static boolean previousLowSensorValue;

    private static boolean movingDown;

    // led checks
    public static boolean elevLEDS;
    public static boolean shootingLEDS;

    public static TeleOp getInstance() {
        if (instance == null)
            instance = new TeleOp();
        return instance;
    }

    private TeleOp() {
        driver = new XBoxController(Constants.XBOX_DRIVER);
        manip = new XBoxController(Constants.XBOX_MANIP);

        // shooter defaults


        previousSensorValue = false;

        movingDown = false;

    }

    public static void run() {

        smartDashboard();
        Limelight.refresh();

        //PID VALUES
        shooter.setkP(Constants.SHOOTER_kP);
        shooter.setkI(Constants.SHOOTER_kI);
        shooter.setkD(Constants.SHOOTER_kD);
        shooter.setkF(Constants.SHOOTER_FEED_FWD);



        /* DRIVER CONTROLS */
        

        //limelight tracking
        if (driver.getLeftBumper()) {
            if (Limelight.getTv() == 1) {
                Limelight.ledsOn();
           
                dt.setAngle(dt.getAHRS() + Limelight.getTx());
         
                dt.targetedDrive(Utils.expodeadZone(-driver.getRightStickYAxis())); // allows driver to move back and forth during lineup

                if(manip.getYButton() == false){
                    shooter.hoodForward();  //manip can override hood position with Y if shooting close
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

            //Unless driver is holding leftbumper manual drive is always active
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


            shooter.launch(Utils.autoPower());

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
            //shooter.launchNoPID(SmartDashboard.getNumber("Jeff", 0));
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


        

        SmartDashboard.putNumber("Gyro #", dt.getAHRS());

        
      

        // drivetrain set values


        /*dt.setkP2(SmartDashboard.getNumber("kP 2", 0));
        dt.setkI2(SmartDashboard.getNumber("kI 2", 0));
        dt.setkD2(SmartDashboard.getNumber("kD 2", 0));*/


        //speed = SmartDashboard.getNumber("Jeff", speed);

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