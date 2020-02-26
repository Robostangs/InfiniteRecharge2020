package frc.AutoModes;

public class ShootfromInit extends AutoMode{


    public ShootfromInit(){
        super();
    }


@Override
protected void run() {
    // TODO Auto-generated method stub
    
    //robot points slightly to left, shotspeed and actuator value calculated automatically
    lookForTarget(3, 0.1, true, 0, 0);

    shootFar(6, 0.7);
    
    driveStraight(2, 32, false, 0, 0);
    


    //ADDITIONAL CELLS IN FUTURE?

    //switch pipelines
    //lookForTarget
    //Drivedistance + extra for intake 
    //
	
}



}