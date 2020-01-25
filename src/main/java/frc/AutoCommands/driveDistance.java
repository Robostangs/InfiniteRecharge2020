package frc.AutoCommands;

import frc.robot.Drivetrain;
import frc.robot.Utils;

public class driveDistance extends AutoCommandBase {
    private double distance;

	

	public driveDistance(double timeOut, double distance){

        super(timeOut);
                
        this.distance = distance;

        //convert time to distance when encoders finished

		


	}

    @Override
    public void init(){

    } 

    @Override
    protected void run(){ 
        //this will full speed, find out some way to prevent this 
        //while also allowing feet as the input
       Drivetrain.driveRotations(Utils.feetToRotations(distance), Utils.feetToRotations(distance));
       
          

    }

    @Override
    public void end(){
        Drivetrain.stop();
    }

    @Override
    protected String getCommandName() {
        return "Drive Distance";
    }
}
