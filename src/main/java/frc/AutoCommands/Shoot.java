package frc.AutoCommands;

import frc.robot.Shooter;

public class Shoot extends AutoCommandBase {
	

	
	
	public Shoot(double timeOut) {
		super(timeOut);
        
		
	}

	
	public void init() {

	}

	@Override
	protected void run() {
		Shooter.dispense();
	}

	@Override
	public void end() {
		Shooter.stop();
	}

	@Override
	protected String getCommandName() {
		// TODO Auto-generated method stub
		return "Turn to angle";
	}

}