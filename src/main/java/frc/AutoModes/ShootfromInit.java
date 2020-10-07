package frc.AutoModes;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Drivetrain;
import frc.robot.Shooter;

public class ShootfromInit extends AutoMode {

    private Shooter shooter = Shooter.getInstance();
    private Drivetrain drivetrain = Drivetrain.getInstance();
    private static double gyroValue = 0;

   
    public ShootfromInit(){
        super();
      

    }


@Override
protected void run() {
    
    
    SmartDashboard.putNumber("velocity of shooter", shooter.getVelo());
    //robot points slightly to left, shotspeed and actuator value calculated automatically
    lookForTarget(3, 0.2, false, 0, -0.5);


   //testing shootFar(6, 1);

    turnToTarget(3.5, 0, 0, 180);



    driveStraight(2, -32, true, -0.9, 0);

    turnToTarget(3.5, 0, 0, 0);

    lookForTarget(3, 0.1, false, 0, -0.5);

	
}

public static double getGyro(){
    return gyroValue;
}



}