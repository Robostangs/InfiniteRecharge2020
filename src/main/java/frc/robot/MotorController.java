/**
 * 
 * @revision Tue Feb 2 2020
 * 
 * Standardized MotorController class that encapsulates different motor types.
 * Currently implemented types:
 *     * CANSparkMax
 *     * TalonSRX
 *     * TalonFX
 * 
 */
package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MotorController {

    private MotorController master;

    private TalonSRX talon;
    private TalonFX falcon;
    private CANSparkMax neo;

    private int type;
    private String name;

    /**
     * MotorController
     * 
     * @param talon TalonSRX
     */
    public MotorController(TalonSRX talon){
        this.talon = talon;
        this.name = "talon";
        type = 0; //TODO Move to Constants?
    }
    
    /**
     * MotorController
     * 
     * @param falcon TalonFX
     */
    public MotorController(TalonFX falcon){
        this.falcon = falcon;
        this.name = "falcon";
        type = 1; //TODO Move to Constants?
    }

    /**
     * MotorController
     * 
     * @param neo CANSparkMax
     */
    public MotorController(CANSparkMax neo){
        this.neo = neo;
        this.name = "neo";
        type = 2; //TODO Move to Constants?
    }

    /**
     * MotorController
     * 
     * @param talon TalonSRX
     * @param String name
     */
    public MotorController(TalonSRX talon, String name){
        this.talon = talon;
        this.name = name;
        type = 0; //TODO Move to Constants?
    }
    
    /**
     * MotorController
     * 
     * @param falcon TalonFX
     * @param String name
     */
    public MotorController(TalonFX falcon, String name){
        this.falcon = falcon;
        this.name = name;
        type = 1; //TODO Move to Constants?
    }

    /**
     * MotorController
     * 
     * @param neo CANSparkMax
     * @param String name
     */
    public MotorController(CANSparkMax neo, String name){
        this.neo = neo;
        this.name = name;
        type = 2; //TODO Move to Constants?
    }

    /**
     * set
     * 
     * @param power double
     * @param slotID int
     */
    public void set(double power, int slotID){
        switch(type){
            case 0:talon.set(ControlMode.PercentOutput, power); break;
            case 1:falcon.set(ControlMode.PercentOutput, power); break;
            case 2:neo.getPIDController().setReference(power, ControlType.kSmartMotion, slotID); break;
        }
    }

    /**
     * getBusVoltage
     * 
     * @return double
     */
    public double getBusVoltage(){
        switch(type){
            case 0:return talon.getBusVoltage();
            case 1:return falcon.getBusVoltage();
            case 2:return neo.getBusVoltage(); 
            default:return 0;
        }
    }

    /**
     * getTemperature
     * 
     * @return double
     */
    public double getTemperature(){
        switch(type){
            case 0:return talon.getTemperature();
            case 1:return falcon.getTemperature();
            case 2:return neo.getMotorTemperature();
            default:return 0;
        }
    }

    /**
     * getCurrent
     * 
     * @return double
     */
    public double getCurrent(){
        switch(type){
            case 0:return talon.getSupplyCurrent();
            case 1:return falcon.getSupplyCurrent();
            case 2:return neo.getOutputCurrent();
            default:return 0;
        }
    }

    /**
     * configPID
     * 
     * @param p double
     * @param i double
     * @param d double
     * @param f double
     * @param timeout int
     * @param slotId int
     */
    public void configPID(double p, double i, double d, double f, int timeout, int slotId){
        switch(type){
            case 0:talon.config_kP(slotId, p, timeout); talon.config_kI(slotId, i, timeout); talon.config_kD(slotId, d, timeout); talon.config_kF(slotId, f, timeout); break;
            case 1:falcon.config_kP(slotId, p, timeout); falcon.config_kI(slotId, i, timeout); falcon.config_kD(slotId, d, timeout); falcon.config_kF(slotId, f, timeout); break;
            case 2:neo.getPIDController().setP(p, slotId); neo.getPIDController().setI(i, slotId); neo.getPIDController().setD(d, slotId); neo.getPIDController().setFF(f, slotId); break;
        }
    }

    /**
     * config_kP
     * 
     * @param p double
     * @param timeout int
     * @param slotId int
     */
    public void config_kP(double p, int timeout, int slotId){
        switch(type){
            case 0:talon.config_kP(slotId, p, timeout); break;
            case 1:falcon.config_kP(slotId, p, timeout); break;
            case 2:neo.getPIDController().setP(p, slotId);
        }
    }

    /**
     * config_kI
     * 
     * @param i double
     * @param timeout int
     * @param slotId int
     */
    public void config_kI(double i, int timeout, int slotId){
        switch(type){
            case 0:talon.config_kI(slotId, i, timeout); break;
            case 1:falcon.config_kI(slotId, i, timeout); break;
            case 2:neo.getPIDController().setP(i, slotId);
        }
    }

    /**
     * 
     * config_kD
     * 
     * @param d double
     * @param timeout int
     * @param slotId int
     */
    public void config_kD(double d, int timeout, int slotId){
        switch(type){
            case 0:talon.config_kD(slotId, d, timeout); break;
            case 1:falcon.config_kD(slotId, d, timeout); break;
            case 2:neo.getPIDController().setP(d, slotId);
        }
    }

    /**
     * 
     * config_kF
     * 
     * @param f double
     * @param timeout int
     * @param slotId int
     */
    public void config_kF(double f, int timeout, int slotId){
        switch(type){
            case 0:talon.config_kF(slotId, f, timeout); break;
            case 1:falcon.config_kF(slotId, f, timeout); break;
            case 2:neo.getPIDController().setP(f, slotId);
        }
    }

    /**
     * follow
     * 
     * @param master MotorController
     */
    public void follow(MotorController master){
        this.master = master;
        switch(type){
            case 0:talon.follow(master.getTalon()); break;
            case 1:falcon.follow(master.getFalcon()); break;
            case 2:neo.follow(master.getNeo()); break;
        }
    }

    /**
     * setInverted
     * 
     * @param inversion boolean
     */
    public void setInverted(boolean inversion){
        switch(type){
            case 0:talon.setSensorPhase(true); talon.setInverted(inversion); break;
            case 1:falcon.setSensorPhase(true); falcon.setInverted(inversion); break;
            case 2:neo.setInverted(inversion); break;
        }
    }

    /**
     * setPosition
     * 
     * @param position double
     * @param slotID int
     */
    public void setPosition(double position, int slotID){
        switch(type){
            case 0:talon.set(ControlMode.Position, position); break;
            case 1:falcon.set(ControlMode.Position, position); break;
            case 2:neo.getPIDController().setReference(position, ControlType.kPosition, slotID);
        }
    }

    /**
     * setVelocity
     * 
     * @param velocity double
     * @param slotID int
     */
    public void setVelocity(double velocity, int slotID){
        switch(type){
            case 0:talon.set(ControlMode.Velocity, velocity); break;
            case 1:falcon.set(ControlMode.Velocity, velocity); break;
            case 2:neo.getPIDController().setReference(velocity, ControlType.kVelocity, slotID);
        }
    }

    /**
     * getEncoderPosition
     * 
     * @return double
     */
    public double getEncoderPosition(){
        switch(type){
            case 0:return talon.getSelectedSensorPosition();
            case 1:return falcon.getSelectedSensorPosition();
            case 2:return neo.getEncoder().getPosition();
            default:return -1;
        }
    }

    /**
     * getEncoderVelocity
     * 
     * @return double
     */
    public double getEncoderVelocity(){
        switch(type){
            case 0:return talon.getSelectedSensorVelocity();
            case 1:return falcon.getSelectedSensorVelocity();
            case 2:return neo.getEncoder().getVelocity();
            default:return -1;
        }
    }

    /**
     * getMotorOutputPercent
     * 
     * @return double
     */
    public double getMotorOutputPercent(){
        switch(type){
            case 0:return talon.getMotorOutputPercent();
            case 1:return falcon.getMotorOutputPercent();
            case 2:return 0;
            default:return 0;
        }
    }

    /**
     * getType
     * 
     * @return int
     */
    public int getType(){
        return type;
    }

    /**
     * getTalon
     * 
     * @return TalonSRX
     */
    public TalonSRX getTalon(){
        return talon;
    }

    /**
     * getFalcon
     * 
     * @return TalonFX
     */
    public TalonFX getFalcon(){
        return falcon;
    }

    /**
     * getNeo
     * 
     * @return CANSparkMax
     */
    public CANSparkMax getNeo(){
        return neo;
    }

    /**
     * getMaster
     * 
     * @return MotorController
     */
    public MotorController getMaster(){
        return master;
    }

    /**
     * getName
     * 
     * @return String
     */
    public String getName(){
        return name;
    }

    public void pushToDashboard(){
        SmartDashboard.putNumber(name + " Voltage: ",this.getBusVoltage());
        SmartDashboard.putNumber(name + "Temperature: ",this.getTemperature());
        SmartDashboard.putNumber(name + "Motor Output: ",this.getMotorOutputPercent());
        SmartDashboard.putNumber(name + "Motor Velocity: ",this.getEncoderVelocity());
        SmartDashboard.putNumber(name + "Motor Position: ",this.getEncoderPosition());
        SmartDashboard.putNumber(name + "Current: ",this.getCurrent());
        SmartDashboard.putString(name + "Master: ",this.getMaster().getName());
    }
}