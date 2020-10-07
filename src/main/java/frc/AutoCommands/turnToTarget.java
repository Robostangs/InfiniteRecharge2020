package frc.AutoCommands;

import frc.robot.Drivetrain;
import frc.robot.Intake;
import frc.robot.Shooter;


public class turnToTarget extends AutoCommandBase {
    private Shooter shooter = Shooter.getInstance();
    private Intake intake = Intake.getInstance();
    private Drivetrain drivetrain = Drivetrain.getInstance();

    private double angle;
    private double currentAngle;
	

	public turnToTarget(double timeout, double power, double distance, double angle){

        super(timeout);
        this.angle = angle;
            

	}

    @Override
    public void init() {
        // TODO Auto-generated method stub
        double realAngle = 0;

        currentAngle = drivetrain.getRealAngle();

        if(currentAngle >= 360){
           realAngle = currentAngle % 360;
        }
        else if(currentAngle < 0){
            realAngle = 360 - Math.abs(currentAngle % 360);
        }
        else{
            realAngle = currentAngle;
        }
        
        double targetAngle = angle - realAngle;
        if(Math.abs(targetAngle) <= 180){
            drivetrain.setAngle(currentAngle + targetAngle);
            System.out.println(currentAngle + targetAngle);
        }
        if(Math.abs(targetAngle) > 180){
            if(targetAngle > 0){
                drivetrain.setAngle(currentAngle + (targetAngle - 360));
                System.out.println(currentAngle + (targetAngle - 360));
            }
            if(targetAngle < 0){
                drivetrain.setAngle(currentAngle + (targetAngle + 360));
                System.out.println(currentAngle + (targetAngle + 360));
            }

        }
        
        
    }

    @Override
    protected void run() {
        
  
        drivetrain.AutoTargetedDrive(0);
    }

    @Override
    public void end() {

    }

    @Override
	protected String getCommandName() {
		// TODO Auto-generated method stub
		return "Turn to target";
    }


    
}