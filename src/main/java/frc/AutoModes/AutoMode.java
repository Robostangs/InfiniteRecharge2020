package frc.AutoModes;

import frc.AutoCommands.AutoCommandBase;
import frc.AutoCommands.driveDistance;
import frc.AutoCommands.lookForTarget;
import frc.AutoCommands.waitTime;
import frc.AutoCommands.shootFar;

public abstract class AutoMode {
	private String autoName;
	
	public  void start(){
		run();
	}
	
    protected abstract void run();

    protected  void driveStraight(double seconds, double distance, boolean rollerDown, double rollerSpeed, double intakeSpeed){
		runCommand(new driveDistance(seconds, distance, rollerDown, rollerSpeed, intakeSpeed));
	}
	
    
    protected void lookForTarget(double seconds, double power, boolean isLeft, double shootSpeed, double act){
    	runCommand(new lookForTarget(seconds, power, isLeft, shootSpeed,act));
    }
    
   
    
    protected void  waitTime(double seconds){
    	runCommand(new waitTime(seconds));
	}
	

	protected void shootFar(double timeOut, double beltSpeed){
		runCommand(new shootFar(timeOut, beltSpeed));
	}

	protected void layup(double power, double distance, double angle){
		//runCommand(new layup(power, distance, angle));
	}
    
   
    
    
	private  void runCommand(AutoCommandBase command) {
		// TODO Auto-generated method stub
		command.execute();
	}
	
	
}

