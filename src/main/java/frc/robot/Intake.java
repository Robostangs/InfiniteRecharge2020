package frc.robot;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import frc.RobotTests.IntakeTester;

public class Intake extends Subsystems {

    public static Intake instance;

    public TalonSRX belt;
    public Solenoid ingestorBar;
    public TalonSRX ingestor;

    public static Intake getInstance() {
        if (instance == null)
            instance = new Intake();
        return instance;
    }

    public Intake() {
        belt = new TalonSRX(Constants.INTAKE_BELT);
        ingestorBar = new Solenoid(Constants.INTAKE_INGESTORBAR);
        ingestor = new TalonSRX(Constants.INTAKE_INGESTOR);
    }

    public void ingest(double power) {
        ingestor.set(ControlMode.PercentOutput, power);
    }

    public void beltMove(double power) {
        belt.set(ControlMode.PercentOutput, power);
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