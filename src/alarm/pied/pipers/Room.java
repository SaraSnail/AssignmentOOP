package alarm.pied.pipers;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String sortOfRoom;
    private SmokeDetector smokeDetector;


    public Room(String sortOfRoom) {
        this.sortOfRoom = sortOfRoom;
    }

    public String getSortOfRoom() {
        return sortOfRoom;
    }

    public void setSortOfRoom(String sortOfRoom) {
        this.sortOfRoom = sortOfRoom;
    }



    public SmokeDetector getSmokeDetector() {
        return smokeDetector;
    }

    public void setSmokeDetector(SmokeDetector smokeDetector) {
        this.smokeDetector = smokeDetector;
    }







    private List<Detectors> detectorsList = new ArrayList<>();

    public List<Detectors> getDetectorsList() {
        return detectorsList;
    }

    public void setDetectorsList(List<Detectors> detectorsList) {
        this.detectorsList = detectorsList;
    }


}
