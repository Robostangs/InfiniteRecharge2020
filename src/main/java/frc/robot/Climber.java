package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

public class Climber extends Subsystems {

    public static Climber instance;
    public TalonFX leftClimber, rightClimber;   //two climber talons
    public Solenoid cylinder;
    public DigitalInput stopper;

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

      stopper = new DigitalInput(Constants.CLIMBER_STOPPER);

    }

    public void climb(double left, double right){
        leftClimber.set(ControlMode.PercentOutput, left);
        rightClimber.set(ControlMode.PercentOutput, right);
    }

    public void disengageRatchet(){ //formerly compress
        cylinder.set(true);
    }

    public void engageRatchet(){
        cylinder.set(false);
    }

    public boolean getStopper(){
        return stopper.get();
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