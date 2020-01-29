package frc.robot;

import org.opencv.core.RotatedRect;

public class Utils{

    public static double expodeadZone(double input){
        if(-0.5>input){
            return -1.108*(input+.05)*(input+.05);
        }
        else if(0.5<input){
            return 1.108*(input+.05)*(input+.05);
        }
        else{
            return 0;
        }
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




   
}


