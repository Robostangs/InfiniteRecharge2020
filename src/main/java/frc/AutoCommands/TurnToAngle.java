package frc.AutoCommands;

import frc.robot.DriveTrain;

public class TurnToAngle extends AutoCommandBase {
	
	private double angle = 0;
	private double power;

	
	
	public TurnToAngle(double timeOut, double angle, double power) {
		super(timeOut);
		this.angle = angle;
        this.power = power;
        
		
	}

	
	public void init() {
		DriveTrain.turnToAngle(angle);
	}

	@Override
	protected void run() {
		DriveTrain.targetedDrive(power);
	}

	@Override
	public void end() {
		DriveTrain.stop();
	}

	@Override
	protected String getCommandName() {
		// TODO Auto-generated method stub
		return "Turn to angle";
	}

}