package frc.AutoCommands;

import frc.robot.Drivetrain;
import frc.robot.Limelight;


public class lookForTarget extends AutoCommandBase{
    private double power;
    private Boolean isLeft;

	

	public lookForTarget(double timeOut, double power, Boolean isLeft){

		super(timeOut);

        this.power = power;

        this.isLeft = isLeft;
        
		


	}

    @Override
    public void init(){

    }

    @Override
    protected void run(){
        if(Limelight.getTv() == 1){
            Drivetrain.setAngle(Drivetrain.getAHRS() + Limelight.getTx());
            Drivetrain.targetedDrive(0);

            System.out.println("Target Found");
        }
        else{
            if(isLeft == true){
                Drivetrain.drive(-power, power);  //turns left
            }
            else{
                Drivetrain.drive(power, -power); //turns rigt
            }

            System.out.println("Searching for Target....");
           
        }
    }

    @Override
    public void end(){
        Drivetrain.stop();
    }

    @Override
    protected String getCommandName() {
        return null;
    }
}
