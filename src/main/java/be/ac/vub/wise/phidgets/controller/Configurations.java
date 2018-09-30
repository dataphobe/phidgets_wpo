package be.ac.vub.wise.phidgets.controller;

public enum Configurations {

    SpeedControllerPort(4),
    JoyHorizontaltalPort(6),
    JoyVerticalPort(7);

    private int _val;

    private Configurations(int val) {
        this._val = val;
    }

    public int getConfig() {
        return this._val;
    }
}
