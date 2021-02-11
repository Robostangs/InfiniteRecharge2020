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
    lookForTarget(2, 0.1, false, 0);


    shootFar(3, 1);

    turnToTarget(1, 0, 0, 180);



    driveStraight(3, -100, true, -0.9, 0);

    turnToTarget(0.8, 0, 0, 0);

    driveStraight(2, -40, false, 0, 0);

    lookForTarget(1, 0.1, false, 0);

    shootFar(4, 1);

	
}

public static double getGyro(){
    return gyroValue;
}



}