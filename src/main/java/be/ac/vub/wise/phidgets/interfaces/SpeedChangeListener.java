package be.ac.vub.wise.phidgets.interfaces;

import be.ac.vub.wise.phidgets.controller.*;
import be.ac.vub.wise.phidgets.controller.SpeedChangedEvent;

public interface SpeedChangeListener {
    void onSpeedChanged(SpeedChangedEvent s);
}
