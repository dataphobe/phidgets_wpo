package be.ac.vub.wise.phidgets.controller;

import java.math.*;

public class SpeedChangedEvent {

    private double _speed;

    public SpeedChangedEvent(double speed) {
        _speed = speed;
    }

    public int getspeed() {
        int tmp = (int) Math.floor(_speed);
        tmp++;
        return tmp;
    }

}
