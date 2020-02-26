package frc.AutoCommands;

import frc.robot.Drivetrain;

public class waitTime extends AutoCommandBase {

        private Drivetrain drivetrain = Drivetrain.getInstance();

        public waitTime(double timeOut){
            super(timeOut);
        }

        @Override
        public void init(){

        }

        @Override
        protected void run(){
            drivetrain.stop();
        }

        @Override
        public void end(){

        }

        @Override
        protected String getCommandName() {
            return null;
        }
}