package frc.AutoModes;

import frc.AutoCommands.AutoCommandBase;
import frc.AutoCommands.driveDistance;
import frc.AutoCommands.lookForTarget;
import frc.AutoCommands.waitTime;

public abstract class AutoMode {
	private String autoName;
	
	public  void start(){
		run();
	}
	
    protected abstract void run();

    protected  void driveStraight(double seconds, double distance){
		runCommand(new driveDistance(seconds, distance));
	}
	
    
    protected void lookForTarget(double seconds, double power, boolean isLeft){
    	runCommand(new lookForTarget(seconds, power, isLeft));
    }
    
   
    
    protected void  waitTime(double seconds){
    	runCommand(new waitTime(seconds));
	}
	

	protected void shootFar(double power, double distance, double angle){
		//runCommand(new shootFar(power, distance, angle));
	}

	protected void layup(double power, double distance, double angle){
		//runCommand(new layup(power, distance, angle));
	}
    
   
    
    
	private  void runCommand(AutoCommandBase command) {
		// TODO Auto-generated method stub
		command.execute();
	}
	
	
}

