/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.ArrayList;
import java.util.List;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.RobotTests.Drivetraintester;
import frc.RobotTests.IntakeTester;
import frc.RobotTests.ShooterTester;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  public static Drivetrain dt;
  public static Shooter sh;
  public static Intake it;
  public static TeleOp tp;

  private List<Subsystems> subsystems = new ArrayList<Subsystems>();



  //add subsystems to list

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    dt = Drivetrain.getInstance();
    sh = Shooter.getInstance();
    LEDs.getInstance();
    //Climber.getInstance();
    it = Intake.getInstance();
    tp = TeleOp.getInstance();
    Limelight.getInstance();
    
        

  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }


  @Override
  public void teleopInit() {

    SmartDashboard.putNumber("Gyro kP", 0);
    SmartDashboard.putNumber("Gyro kI", 0);
    SmartDashboard.putNumber("Gyro kD", 0); 

    SmartDashboard.putNumber("Jeff", 0);
    SmartDashboard.putNumber("elevspeed", 0);
    SmartDashboard.putNumber("Hood Position", 0);
    
    SmartDashboard.putNumber("Shooter kP", 0);
    SmartDashboard.putNumber("Shooter kI", 0);
    SmartDashboard.putNumber("Shooter kD", 0);
    SmartDashboard.putNumber("Shooter kF", 0);
  }
  
  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    TeleOp.run();
  }

 @Override
  public void testInit() {
    //set music selection and LEDS to indicate testing
    Music.loadMusicSelection(new TalonFX(Constants.SHOOTER_TALON_LEFT), new TalonFX(Constants.SHOOTER_TALON_RIGHT), "low_rider.chrp");
    LEDs.setColor(0.61); //change to flashing red

    //adds subsystems to list to iterate through
    subsystems.add(dt); 
    subsystems.add(sh);
    subsystems.add(it);

    System.out.println(" Running Subsystem checks... ");
    System.out.println("----------------------------");

    //iterate through each subsystem check
    for (Subsystems sub : subsystems)
    {
      System.out.println();
      sub.checkStart();
      System.out.println("------------------------------");
    }
    
  }
  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    //play low rider if test suceeds and change LEDS to green
    if(ShooterTester.allMotorsFunctional() && IntakeTester.allMotorsFunctional() && Drivetraintester.allMotorsFunctional()){
      LEDs.setColor(0.77);
      Music.play();
    }
    //leds keep flashing red and erros display
    else{
      LEDs.setColor(0.61);
    }
  }
}
