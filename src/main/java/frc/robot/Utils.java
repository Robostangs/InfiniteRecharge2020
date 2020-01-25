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

    }

    public static double degToRad(double x){
        return (Math.PI/180.0)*x;
    }

    public static double distToAngle(double dist){
        return 0;

    }

    public static double distToVelocity(double dist){
        return 0;

    }
	public static double angleToActuator(double angle) {
		return 0;
	}
}

