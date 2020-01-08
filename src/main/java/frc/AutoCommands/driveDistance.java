package main.java.frc.AutoCommands;


public class driveDistance extends AutoCommandBase{
    private double power, distance, threshold, elevatorSetPoint, ingestorPower;

	

	public driveDistance(double timeOut, double power, double distance){

		super(timeOut);

        this.power = power;
        
        this.distance = distance;

        //convert time to distance when encoders finished

		


	}

    @Override
    public void init(){

    }

    @Override
    protected void run(){
        DriveTrain.drive(power, power);
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
