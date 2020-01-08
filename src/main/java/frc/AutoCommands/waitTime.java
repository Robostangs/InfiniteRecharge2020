package main.java.frc.AutoCommands;



public class waitTime extends AutoCommandBase{


        public waitTime(double timeOut){
            super(timeOut);
        }

        @Override
        public void init(){

        }

        @Override
        protected void run(){
            DriveTrain.stop();
        }

        @Override
        public void end(){

        }

        @Override
        protected String getCommandName() {
            return null;
        }
}