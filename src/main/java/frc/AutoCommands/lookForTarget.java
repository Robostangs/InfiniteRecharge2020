package main.java.frc.AutoCommands;

public class lookForTarget extends AutoCommandBase{
    private double power, direction;

	

	public lookForTarget(double timeOut, double power, Boolean isLeft){

		super(timeOut);

        this.power = power;

        this.direction = direction;
        
		


	}

    @Override
    public void init(){

    }

    @Override
    protected void run(){
        if(Limelight.getTv() == true){
            Limelight.lineUp();

            System.out.println("Target Found");
        }
        else{
            if(isLeft == true){
                DriveTrain.drive(-0.25, 0.25);  //turns left
            }
            else{
                DriveTrain.drive(0.25, -0.25); //turns right
            }

            System.out.println("Searching for Target...");
           
        }
    }

    @Override
    public void end(){
        DriveTrain.stop();
    }

    @Override
    protected String getCommandName() {
        return null;
    }
}
