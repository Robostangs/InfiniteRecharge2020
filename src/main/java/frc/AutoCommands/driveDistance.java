package frc.AutoCommands;

import frc.robot.Constants;
import frc.robot.Drivetrain;
import frc.robot.Intake;
import frc.robot.LEDs;
import frc.robot.Shooter;

public class driveDistance extends AutoCommandBase {
    private double distance;
    private boolean rollerDown;
    private double rollerSpeed;
    private double intakeSpeed;
    private Drivetrain drivetrain = Drivetrain.getInstance();
    private Intake intake = Intake.getInstance();
    private Shooter shooter = Shooter.getInstance();
    private boolean previousSensorValue;

	

	public driveDistance(double timeout, double distance, boolean rollerDown, double rollerSpeed, double intakeSpeed){

        super(timeout);
                
        this.distance = distance;
        this.rollerDown = rollerDown;
        this.rollerSpeed = rollerSpeed;
        this.intakeSpeed = intakeSpeed;

        //convert time to distance when encoders finished

		previousSensorValue = false;


	}

    @Override
    public void init(){
        drivetrain.driveRotations(-distance,-distance);
    } 

    @Override
    protected void run(){ 

       intake.barDown(rollerDown);
       intake.beltMove(intakeSpeed);
       intake.ingest(rollerSpeed);

       
       if (highSensorState().equals("new ball in")) {
        intake.resetBeltEncoder();
        LEDs.setColor(-0.29); // blue light chase
        intake.beltMove(0.5);
        shooter.launchNoPID(0.3);

      } else {
        shooter.launchNoPID(0);
        if (intake.getBeltEncoder() < Constants.BELT_BALL_MOVED) {
            intake.setFeederBrake();
            intake.beltMove(0);
        }
        else{
            intake.setFeederCoast();
        }

    }   
       
    }

    @Override
    public void end(){
        drivetrain.drive(0, 0);
    }

    @Override
    protected String getCommandName() {
        return "Drive Distance";
    }


    public String highSensorState() {

        
        if (previousSensorValue == false && intake.getHighSensor() == true) {
            System.out.println("new ball in");
            return "new ball in";
        }

        if (previousSensorValue == false && intake.getHighSensor() == false) {
            return "no ball in";

        }

        previousSensorValue = intake.getHighSensor();

        return "same ball in";

    }

}
