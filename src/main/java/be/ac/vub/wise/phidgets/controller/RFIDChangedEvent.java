package be.ac.vub.wise.phidgets.controller;


public class RFIDChangedEvent {
    private String _tagString;

    public RFIDChangedEvent(String tagString) {
        _tagString = tagString;
    }

    public String getTag() {
        return _tagString;
    }

}
