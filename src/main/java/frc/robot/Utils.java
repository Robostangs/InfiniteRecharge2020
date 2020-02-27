package frc.robot;

public class Utils{

    //also works for power
    public static double distToActuator(double distance, double slope, double min){

        return slope*distance - min;
    }

    public static double autoPower(){
                    
        if(distance() >= 47.7 && distance() <= 79.5){

            return Utils.autoFormula(47.7, 79.5, 4400, 5000) * (50.0/15);
        }
        else if(distance() >= 79.5 && distance() <= 116.5){
            return Utils.autoFormula(79.5, 116.5, 5000, 6000) * (50.0/15);
        }
        else if(distance() >= 116.5 && distance() <= 145.65){
            return Utils.autoFormula(116.5, 145.65, 6000, 6200) * (50.0/15);
        }
        else{
            return Constants.LAYUP_SPEED * (50.0/15);
        }
    }

    public static double autoHood(){
        
        if(distance() >= 47.7 && distance() <= 79.5){

            return Utils.autoFormula(47.7, 79.5, -0.7, -0.5);
        }
        else if(distance() >= 79.5 && distance() <= 116.5){
            return Utils.autoFormula(79.5, 116.5, -0.5, -0.3);
        }
        else if(distance() >= 116.5 && distance() <= 145.65){
            return Utils.autoFormula(116.5, 145.65, -0.3, -0.45);
        }
        else{
            return Constants.LAYUP_POSITION;
        }
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

    public static double autoFormula(double minDist, double maxDist, double minAct, double maxAct){
        return (((maxDist - dist(Limelight.getTx(), Limelight.getTy())) * minAct) + (((dist(Limelight.getTx(), Limelight.getTy()) - minDist)) * minAct))/(maxDist - minDist);
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

    private static double distance(){
        return Utils.dist(Limelight.getTx(), Limelight.getTy());
    }


    
}