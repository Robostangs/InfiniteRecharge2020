package frc.AutoModes;

import frc.AutoCommands.AutoCommandBase;

public abstract class AutoMode {
	private String autoName;
	
	public  void start(){
		run();
	}
	
    protected abstract void run();

    protected  void driveDistance(double seconds, double power, double distance, double threshold, double elevatorSetPoint, double ingestorPower){
		//runCommand(new DriveDistance(seconds, power, distance, elevatorSetPoint, ingestorPower));
	}
	
    
    protected void turnToAngle(double seconds, double angle, double offset, double power, double setPoint){
    	//runCommand(new TurnToAngle(seconds, angle, offset, power, setPoint));
    }
    
   
    
    protected void  waitTime(double seconds){
    	//runCommand(new Wait(seconds));
    }
    
   
    
    
	private  void runCommand(AutoCommandBase command) {
		// TODO Auto-generated method stub
		command.execute();
	}
	
	
}

