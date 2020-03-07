package frc.AutoModes;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Shooter;

public class ShootfromInit extends AutoMode {

    private Shooter shooter = Shooter.getInstance();
   
    public ShootfromInit(){
        super();
      

    }


@Override
protected void run() {
    // TODO Auto-generated method stub
    
    SmartDashboard.putNumber("velocity of shooter", shooter.getVelo());
    //robot points slightly to left, shotspeed and actuator value calculated automatically
    lookForTarget(3, 0.1, true, 0, -0.5);

    shootFar(6, 1);
    
    driveStraight(2, 32, false, 0, 0);
    


    //ADDITIONAL CELLS IN FUTURE?

    //switch pipelines
    //lookForTarget
    //Drivedistance + extra for intake 
    //
	
}



}