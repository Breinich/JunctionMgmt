package main.junctionframework;

import main.world.Simulator;

public class TimeStepper implements Runnable{

    private boolean stopped;

    private int speed;

    public TimeStepper(){
        stopped = false;
        speed = 1000;
    }

    public void run() {

        while(!stopped)
        {
            JunctionFramework.stepTime();

            try {
                Thread.sleep(speed);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        stopped = true;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
