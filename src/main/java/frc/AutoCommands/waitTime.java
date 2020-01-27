package frc.AutoCommands;

import frc.robot.Drivetrain;

public class waitTime extends AutoCommandBase {

        private Drivetrain Drivetrain = new Drivetrain();

        public waitTime(double timeOut){
            super(timeOut);
        }

        @Override
        public void init(){

        }

        @Override
        protected void run(){
            Drivetrain.stop();
        }

        @Override
        public void end(){

        }

        @Override
        protected String getCommandName() {
            return null;
        }
}