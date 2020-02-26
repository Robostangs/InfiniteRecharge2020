package frc.AutoModes;


public class DriveStraight extends AutoMode{


    public DriveStraight(){
        super();
    }


@Override
protected void run() {
    // TODO Auto-generated method stub
    
    //lookForTarget(2, 0.2, true, 5000);
    driveStraight(2, 20, false, 0, 0);
    
	
}

}