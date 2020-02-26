package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.Solenoid;

public class Climber extends Subsystems {
    public static Climber instance;
    public TalonFX leftClimber , rightClimber;
    public Solenoid cylinder;

    public static Climber getInstance() {
        
        if (instance == null)
            instance = new Climber();
        return instance;
    }


    public Climber(){
      leftClimber = new TalonFX(Constants.leftClimber); 
      rightClimber = new TalonFX(Constants.rightClimber);
      cylinder = new Solenoid(Constants.CLIMBER_SOLENOID);

      leftClimber.setNeutralMode(NeutralMode.Brake);
      rightClimber.setNeutralMode(NeutralMode.Brake);

    }

    public void climb(double left, double right){
        leftClimber.set(ControlMode.PercentOutput, left);
        rightClimber.set(ControlMode.PercentOutput, right);
    }

    public void compress(){
        cylinder.set(true);
    }

    public void decompress(){
        cylinder.set(false);
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