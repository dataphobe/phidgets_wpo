package be.ac.vub.wise.phidgets.interfaces;

import be.ac.vub.wise.phidgets.controller.*;
import be.ac.vub.wise.phidgets.controller.RFIDChangedEvent;

public interface RFIDChangedListener {
    void onRFIDChanged(RFIDChangedEvent r);
}


