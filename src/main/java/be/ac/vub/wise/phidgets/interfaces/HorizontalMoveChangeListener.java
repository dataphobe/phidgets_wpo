package be.ac.vub.wise.phidgets.interfaces;

import be.ac.vub.wise.phidgets.controller.*;
import be.ac.vub.wise.phidgets.controller.HorizontalMoveChangeEvent;

public interface HorizontalMoveChangeListener {
    void onHorizontalChanged(HorizontalMoveChangeEvent v);
}
