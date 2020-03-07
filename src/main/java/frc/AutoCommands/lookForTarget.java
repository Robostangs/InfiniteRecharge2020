package frc.AutoCommands;

import frc.robot.Drivetrain;
import frc.robot.Limelight;
import frc.robot.Shooter;
import frc.robot.Utils;


public class lookForTarget extends AutoCommandBase{
    private double power;
    private Boolean isLeft;
    private double shootPower;
    private double actPosition;
    private Drivetrain drivetrain = Drivetrain.getInstance();
    private Shooter shooter = Shooter.getInstance();

	

	public lookForTarget(double timeOut, double power, Boolean isLeft, double shootSpeed,double act){

		super(timeOut);

        this.power = power;

        this.isLeft = isLeft;
        this.shootPower = shootSpeed;
		this.actPosition = act;


	}

    @Override
    public void init(){

        shooter.hoodPosition(actPosition);
    }

    @Override
    protected void run(){


        Limelight.refresh();
        if(Limelight.getTv() == 1){
            Limelight.ledsOn();
            drivetrain.setAngle(drivetrain.getAHRS() + Limelight.getTx());
            drivetrain.AutoTargetedDrive(0);


            System.out.println("Target Found");
        }
        else{
            Limelight.ledsOn();
            //drivetrain.pidDisable();
            if(isLeft == true){
                drivetrain.drive(-power, power);  //turns left
            }
            else{
                drivetrain.drive(power, -power); //turns rigt
            }

            System.out.println("Searching for Target....");
           
        }
    }

    @Override
    public void end(){
        drivetrain.stop();
    }

    @Override
    protected String getCommandName() {
        return null;
    }
}
