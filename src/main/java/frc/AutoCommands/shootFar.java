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

        if(distance() >= 47.7 && distance() <= 79.5){

            shooter.hoodPosition(Utils.autoFormula(47.7, 79.5, -0.7, -0.5));
        }
        else if(distance() >= 79.5 && distance() <= 116.5){
            shooter.hoodPosition(Utils.autoFormula(79.5, 116.5, -0.5, -0.3));
        }
        else if(distance() >= 116.5 && distance() <= 145.65){
            shooter.hoodPosition(Utils.autoFormula(116.5, 145.65, -0.3, -0.45));
        }
        else{
            shooter.hoodPosition(Constants.LAYUP_POSITION);
        }


        if(distance() >= 47.7 && distance() <= 79.5){

            shooter.launch(Utils.autoFormula(47.7, 79.5, 4400, 5000) * (50.0/15));
        }
        else if(distance() >= 79.5 && distance() <= 116.5){
            shooter.launch(Utils.autoFormula(79.5, 116.5, 5000, 6000) * (50.0/15));
        }
        else if(distance() >= 116.5 && distance() <= 145.65){
            shooter.launch(Utils.autoFormula(116.5, 145.65, 6000, 6200) * (50.0/15));
        }
        else{
            shooter.launch(Constants.LAYUP_SPEED * (50.0/15));
        }



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