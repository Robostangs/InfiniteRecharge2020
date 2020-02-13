package frc.robot;

public class Utils{


    //also works for power
    public static double distToActuator(double distance, double slope, double min){

        return slope*distance - min;
    }


    public static double dist(double tx,double ty){
        return Constants.TARGET_HEIGHT/(Math.tan(degToRad(ty+Constants.LIMELIGHT_ANGLE)));
    }

    public static double degToRad(double x){
        return (Math.PI/180.0)*x;
    }

    public static double ensureRange(double v, double min, double max) {
        return Math.min(Math.max(v, min), max);
    }

    public static double feetToRotations(double feet)
    {
        return feet*1.57;

        //if you want to travel 4 feet in DriveDistance
        //use this formula in drive distance and set double feet to 4
    }

    public static double expodeadZone(double input){
        if(-0.1>input){
            return -1.108*(input+.05)*(input+.05);
        }
        else if(0.1<input){
            return 1.108*(input+.05)*(input+.05);
        }
        else{
            return 0;
        }
    }


    
}