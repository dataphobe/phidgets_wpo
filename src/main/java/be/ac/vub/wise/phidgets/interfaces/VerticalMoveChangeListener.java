package be.ac.vub.wise.phidgets.interfaces;

import be.ac.vub.wise.phidgets.controller.*;
import be.ac.vub.wise.phidgets.controller.VerticalMoveChangeEvent;

public interface VerticalMoveChangeListener {
    void onVerticalChanged(VerticalMoveChangeEvent v);
}
