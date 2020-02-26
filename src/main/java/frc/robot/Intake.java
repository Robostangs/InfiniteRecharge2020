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
    public DigitalInput highSensor; //color
    public TalonSRX belt;
    public Solenoid ingestorBar;
    public TalonSRX ingestor;

    public static Intake getInstance() {
        if (instance == null)
            instance = new Intake();
        return instance;
    }

    private Intake() {
        belt = new TalonSRX(Constants.INTAKE_BELT);
        ingestorBar = new Solenoid(Constants.INTAKE_INGESTORBAR);
        ingestor = new TalonSRX(Constants.INTAKE_INGESTOR);
        lowSensor = new AnalogInput(Constants.ULTRASONIC_LOW);
        highSensor = new DigitalInput(Constants.COLOR_HIGH);
        belt.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        
    }

    public double getLowSensor(){
        return lowSensor.getVoltage();
    }

    public boolean getHighSensor() {
		return highSensor.get();
	}

    public void ingest(double power) {
        ingestor.set(ControlMode.PercentOutput, power);
    }

    public void beltMove(double power) {
        belt.set(ControlMode.PercentOutput, power);
    }

    public double getBeltEncoder() {
        return belt.getSelectedSensorPosition();
    }

    public void resetBeltEncoder() {
        belt.setSelectedSensorPosition(0);
    }

    public void beltMovePosition(double position){
        belt.set(ControlMode.Position, position);
    }

    public void barDown(boolean isDown) {
        ingestorBar.set(isDown);
    }

    public boolean isBarDown() {
        return ingestorBar.get();
    }

    @Override
    public void checkStart() {
        IntakeTester.itTester(belt, ingestor);
    }

    @Override
    public void stop() {

        ingestorBar.set(false); // possibly change
        ingestor.set(ControlMode.PercentOutput, 0);
        belt.set(ControlMode.PercentOutput, 0);

    }


    
}