package frc.robot;
import edu.wpi.first.wpilibj.Spark;

public class LEDs {
    private static Spark leds;
    public static LEDs instance;

    public static LEDs getInstance(){
        if(leds == null){
            instance = new LEDs();
        }

        return instance;
    }

    private LEDs(){
        leds = new Spark(Constants.LED_CHANNEL); 
       
    }

    public static void setColor(double color){
        leds.set(color);
    }

    public static double getColor(){
        return leds.get();
    }
}