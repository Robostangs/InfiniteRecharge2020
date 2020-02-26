package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.Solenoid;

public class Climber extends Subsystems {
    public static Climber instance;
    public TalonFX leftClimber , rightClimber;
    public Solenoid ratchetSolenoid;

    public static Climber getInstance() {
        
        if (instance == null)
            instance = new Climber();
        return instance;
    }


    private Climber(){
      leftClimber = new TalonFX(Constants.CLIMBER_TALON_LEFT); 
      rightClimber = new TalonFX(Constants.CLIMBER_TALON_RIGHT);
      ratchetSolenoid = new Solenoid(Constants.CLIMBER_RATCHET_SOLENOID);

      leftClimber.setNeutralMode(NeutralMode.Brake);
      rightClimber.setNeutralMode(NeutralMode.Brake);

    }

    public void climb(double power){
        leftClimber.set(ControlMode.PercentOutput, power);
        rightClimber.set(ControlMode.PercentOutput, power);
    }

    public void engageRatchet(){
        ratchetSolenoid.set(false);
    }

    public void disengageRatchet(){
        ratchetSolenoid.set(true);
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