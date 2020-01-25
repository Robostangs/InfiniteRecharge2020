package frc.robot;

public class Utils{


    public static double angleToActuator(double angle)
    {
        //test for angles, make graph
        return 0;
    }

    public static double dist(double tx,double ty){
        return Constants.TARGET_HEIGHT/(Math.tan(degToRad(ty+Constants.LIMELIGHT_ANGLE)));
    }

    public static double degToRad(double x){
        return (Math.PI/180.0)*x;
    }

    
}