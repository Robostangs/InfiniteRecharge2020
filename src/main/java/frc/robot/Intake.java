package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Solenoid;
import frc.RobotTests.IntakeTester;
import edu.wpi.first.wpilibj.DigitalInput;

public class Intake extends Subsystems {

    public static Intake instance;
    public AnalogInput lowSensor; //ultrasonic
    public DigitalInput lowBeltSensor; //color
    public TalonSRX beltTalon;
    public Solenoid ingestorBarSolenoid;
    public TalonSRX ingestorTalon;

    public static Intake getInstance() {
        if (instance == null)
            instance = new Intake();
        return instance;
    }

    private Intake() {
        beltTalon = new TalonSRX(Constants.INTAKE_BELT_TALON);
        ingestorBarSolenoid = new Solenoid(Constants.INTAKE_INGESTOR_BAR_SOLENOID);
        ingestorTalon = new TalonSRX(Constants.INTAKE_INGESTOR_TALON);
        //lowSensor = new AnalogInput(Constants.ULTRASONIC_LOW);
        lowBeltSensor = new DigitalInput(Constants.COLOR_BELT_LOW);
        beltTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        
    }

    // public double getLowSensor(){
    //     return lowSensor.getVoltage();
    // }

    public boolean getLowBeltSensor() {
		return lowBeltSensor.get();
	}

    public void ingest(double power) {
        ingestorTalon.set(ControlMode.PercentOutput, power);
    }

    public void beltMove(double power) {
        beltTalon.set(ControlMode.PercentOutput, power);
    }

    public double getBeltEncoder() {
        return beltTalon.getSelectedSensorPosition();
    }

    public void resetBeltEncoder() {
        beltTalon.setSelectedSensorPosition(0);
    }

    public void beltMovePosition(double position){
        beltTalon.set(ControlMode.Position, position);
    }

    public void barDown(boolean isDown) {
        ingestorBarSolenoid.set(isDown);
    }

    public boolean isBarDown() {
        return ingestorBarSolenoid.get();
    }

    @Override
    public void checkStart() {
        IntakeTester.itTester(beltTalon, ingestorTalon);
    }

    @Override
    public void stop() {

        ingestorBarSolenoid.set(false); // possibly change
        ingestorTalon.set(ControlMode.PercentOutput, 0);
        beltTalon.set(ControlMode.PercentOutput, 0);

    }


    
}