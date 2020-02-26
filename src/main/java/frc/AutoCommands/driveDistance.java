package frc.AutoCommands;

import frc.robot.Drivetrain;
import frc.robot.Intake;

public class driveDistance extends AutoCommandBase {
    private double distance;
    private boolean rollerDown;
    private double rollerSpeed;
    private double intakeSpeed;
    private Drivetrain drivetrain = Drivetrain.getInstance();
    private Intake intake = Intake.getInstance();

	

	public driveDistance(double timeout, double distance, boolean rollerDown, double rollerSpeed, double intakeSpeed){

        super(timeout);
                
        this.distance = distance;
        this.rollerDown = rollerDown;
        this.rollerSpeed = rollerSpeed;
        this.intakeSpeed = intakeSpeed;

        //convert time to distance when encoders finished

		


	}

    @Override
    public void init(){
        drivetrain.driveRotations(-distance,-distance);
    } 

    @Override
    protected void run(){ 
        //this will full speed, find out some way to prevent this 
        //while also allowing feet as the input
       intake.barDown(rollerDown);
       intake.beltMove(intakeSpeed);
       intake.ingest(rollerSpeed);
       
       
          

    }

    @Override
    public void end(){
        drivetrain.drive(0, 0);
    }

    @Override
    protected String getCommandName() {
        return "Drive Distance";
    }
}
