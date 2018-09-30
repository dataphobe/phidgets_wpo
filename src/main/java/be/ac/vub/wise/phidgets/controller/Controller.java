
package be.ac.vub.wise.phidgets.controller;

import be.ac.vub.wise.phidgets.interfaces.*;
import be.ac.vub.wise.phidgets.interfaces.HorizontalMoveChangeListener;
import be.ac.vub.wise.phidgets.interfaces.RFIDChangedListener;
import be.ac.vub.wise.phidgets.interfaces.SpeedChangeListener;
import be.ac.vub.wise.phidgets.interfaces.VerticalMoveChangeListener;
import com.phidget22.*;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private VoltageRatioInput verticalController;
    private VoltageRatioInput horizontalController;
    private VoltageInput speedController;
    private Configurations configs;
    private RFID rfidController;

    private List<VerticalMoveChangeListener> _VerticalMoveChangeListenerList;
    private List<HorizontalMoveChangeListener> _HorizontalMoveChangeListenerList;
    private List<SpeedChangeListener> _SpeedChangeListenerList;
    private List<RFIDChangedListener> _RFIDChangedList;


    public void init() {
        try {
            com.phidget22.Log.enable(LogLevel.INFO, null);
            _VerticalMoveChangeListenerList = new ArrayList<>();
            _HorizontalMoveChangeListenerList = new ArrayList<>();
            _SpeedChangeListenerList = new ArrayList<>();
            _RFIDChangedList = new ArrayList<>();

            verticalController = new VoltageRatioInput();
            horizontalController = new VoltageRatioInput();
            speedController = new VoltageInput();
            rfidController = new RFID();

            verticalController.setChannel(configs.JoyVerticalPort.getConfig());
            horizontalController.setChannel(configs.JoyHorizontaltalPort.getConfig());
            speedController.setChannel(configs.SpeedControllerPort.getConfig());

            verticalController.addErrorListener(new ErrorListener() {
                public void onError(ErrorEvent ee) {
                    System.out.println("Error: " + ee.getDescription());
                }
            });
            verticalController.addVoltageRatioChangeListener(new VoltageRatioInputVoltageRatioChangeListener() {
                public void onVoltageRatioChange(VoltageRatioInputVoltageRatioChangeEvent e) {
                    //System.out.printf("Voltage Ratio Changed: %.3g\n", e.getVoltageRatio());
                    if (e.getVoltageRatio() > 0.8f) {
                        _VerticalMoveChangeListenerList.forEach(item -> {
                            item.onVerticalChanged(new VerticalMoveChangeEvent(true));
                        });
                    } else if (e.getVoltageRatio() < 0.3f) {
                        _VerticalMoveChangeListenerList.forEach(item -> {
                            item.onVerticalChanged(new VerticalMoveChangeEvent(false));
                        });
                    }

                }

            });

            horizontalController.addErrorListener(new ErrorListener() {
                public void onError(ErrorEvent ee) {
                    System.out.println("Error: " + ee.getDescription());
                }
            });
            horizontalController.addVoltageRatioChangeListener(new VoltageRatioInputVoltageRatioChangeListener() {
                public void onVoltageRatioChange(VoltageRatioInputVoltageRatioChangeEvent e) {
                    //System.out.printf("Voltage Ratio Changed: %.3g\n", e.getVoltageRatio());
                    if (e.getVoltageRatio() > 0.8f) {
                        _HorizontalMoveChangeListenerList.forEach(item -> {
                            item.onHorizontalChanged(new HorizontalMoveChangeEvent(true));
                        });
                    } else if (e.getVoltageRatio() < 0.3f) {
                        _HorizontalMoveChangeListenerList.forEach(item -> {
                            item.onHorizontalChanged(new HorizontalMoveChangeEvent(false));
                        });
                    }
                }
            });

            speedController.addErrorListener(new ErrorListener() {
                public void onError(ErrorEvent ee) {
                    System.out.println("Error: " + ee.getDescription());
                }
            });

            speedController.addVoltageChangeListener(new VoltageInputVoltageChangeListener() {
                @Override
                public void onVoltageChange(VoltageInputVoltageChangeEvent e) {
                    // System.out.printf("Voltage  Changed: %.3g\n", e.getVoltage());
                    _SpeedChangeListenerList.forEach(item -> {
                        item.onSpeedChanged(new SpeedChangedEvent(e.getVoltage()));
                    });
                }
            });

            rfidController.addTagListener(new RFIDTagListener() {
                public void onTag(RFIDTagEvent e) {
                    //If you are unsure how to use more than one Phidget channel with this event, we recommend going to
                    //www.phidgets.com/docs/Using_Multiple_Phidgets for information
                    _RFIDChangedList.forEach(item -> {
                        item.onRFIDChanged(new RFIDChangedEvent(e.getTag()));
                    });

//    				System.out.println("[Tag Event] -> Tag: " + e.getTag());
                }
            });

            verticalController.open();
            horizontalController.open();
            speedController.open();
            rfidController.open();


        } catch (PhidgetException ex) {
            //TODO: handle exception
            System.out.printf(ex.toString());
        }
    }

    public final void addVerticalChangeListener(VerticalMoveChangeListener vlis) {
        _VerticalMoveChangeListenerList.add(vlis);
    }

    public final void addHorizontalChangeListener(HorizontalMoveChangeListener hlis) {
        _HorizontalMoveChangeListenerList.add(hlis);
    }

    public final void addSpeedChangeListener(SpeedChangeListener slis) {
        _SpeedChangeListenerList.add(slis);
    }

    public final void addRFIDChangedListener(RFIDChangedListener rfidlis) {
        _RFIDChangedList.add(rfidlis);
    }

    public void closeAll() {
        try {
            verticalController.close();
            horizontalController.close();
            speedController.close();
        } catch (PhidgetException ex) {
            //TODO: handle exception
            System.out.println(ex.toString());
        }
    }

}
