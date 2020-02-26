package frc.AutoCommands;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.Intake;
import frc.robot.Limelight;
import frc.robot.Shooter;
import frc.robot.Utils;

public class shootFar extends AutoCommandBase {
    private Shooter shooter = Shooter.getInstance();
    private Intake intake = Intake.getInstance();

    private double beltSpeed;
	

	public shootFar(double timeout, double beltSpeed){

        super(timeout);
        this.beltSpeed = beltSpeed;
            

	}

    @Override
    public void init() {
        // TODO Auto-generated method stub
        
        
        
    }

    @Override
    protected void run() {
        //move stopper down here ???
        //move actuators
        Limelight.ledsOn();

        shooter.autoHoodPosition(distance());
        shooter.autoLaunchSpeed(distance());
       

        Timer.delay(2);

        intake.beltMove(beltSpeed);
        
    }

    @Override
    public void end() {

    }

    @Override
	protected String getCommandName() {
		// TODO Auto-generated method stub
		return "shootFar";
    }

    private static double distance(){
        return Utils.dist(Limelight.getTx(), Limelight.getTy());
    }
    
}