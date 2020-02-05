package frc.robot;

public class Climber{

    private Climber instance;

    public Climber getInstance(){
        if(instance == null){
            instance = new Climber();
        }
        return instance;
    }

    public Climber(){
        
    }
}