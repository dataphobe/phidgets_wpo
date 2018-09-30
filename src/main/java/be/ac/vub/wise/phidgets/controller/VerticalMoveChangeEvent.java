package be.ac.vub.wise.phidgets.controller;

public class VerticalMoveChangeEvent {
    private boolean _up;

    public VerticalMoveChangeEvent(boolean up) {
        _up = up;
    }

    public int getDirection() {
        if (_up) {
            return +1;
        } else {
            return -1;
        }
    }

}
