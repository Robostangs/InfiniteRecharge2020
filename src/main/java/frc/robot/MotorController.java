package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;

public class MotorController {
    TalonSRX a;
    TalonFX b;
    CANSparkMax c;

    int which;

    public MotorController(TalonSRX x){
        a=x;
        which = 0;
    }

    public MotorController(TalonFX x){
        b=x;
        which =1;
    }
 
    public MotorController(CANSparkMax x){
        c=x;
        which = 2;
    }



}