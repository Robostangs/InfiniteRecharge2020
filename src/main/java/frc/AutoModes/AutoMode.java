package frc.AutoModes;

import frc.AutoCommands.AutoCommandBase;

public abstract class AutoMode {
	private String autoName;
	
	public  void start(){
		run();
	}
	
    protected abstract void run();

    protected  void driveStraight(double seconds, double power, double distance){
		runCommand(new DriveDistance(seconds, power, distance));
	}
	
    
    protected void lookForTarget(double seconds, double angle, double offset, double power, double setPoint){
    	//runCommand(new lookForTarget(seconds, angle, offset, power, setPoint));
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

