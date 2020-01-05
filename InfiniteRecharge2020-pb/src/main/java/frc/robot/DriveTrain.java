package frc.robot;

import com.revrobotics.CANSparkMax;
//import com.ctre.pheonix.motorcontrol.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveTrain
{
    public static DriveTrain instance;
    static CANSparkMax rightFront, leftFront, rightBack, leftBack;


    public static DriveTrain getInstance() {
        if (instance == null)
            instance = new DriveTrain();
        return instance;
    }

    public DriveTrain(){
        rightFront = new CANSparkMax(Constants.TALON_RIGHTFRONT, MotorType.kBrushless);
        leftFront = new CANSparkMax(Constants.TALON_LEFTFRONT, MotorType.kBrushless);
        leftBack = new CANSparkMax(Constants.TALON_LEFTBACK, MotorType.kBrushless);
        rightBack = new CANSparkMax(Constants.TALON_RIGHTBACK, MotorType.kBrushless);
    }




}