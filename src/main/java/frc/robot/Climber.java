package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Climber extends Subsystems {
    public static Climber instance;
    public TalonSRX leftClimber , rightClimber;

    public static Climber getInstance() {
        
        if (instance == null)
            instance = new Climber();
        return instance;
    }


    public Climber(){
      leftClimber = new TalonSRX(Constants.leftClimber); 
      rightClimber = new TalonSRX(Constants.rightClimber);
    }

    public void climb(double left, double right){
        leftClimber.set(ControlMode.PercentOutput, left);
        rightClimber.set(ControlMode.PercentOutput, right);
    }
    
    


    @Override
    public void checkStart() {
        // TODO Auto-generated method stub

    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub

    }
    
}