package be.ac.vub.wise.phidgets.controller;

public class HorizontalMoveChangeEvent {

    private boolean _right;

    public HorizontalMoveChangeEvent(boolean right) {
        _right = right;
    }

    public int getDirection() {
        if (_right) {
            return +1;
        } else {
            return -1;
        }
    }

}
