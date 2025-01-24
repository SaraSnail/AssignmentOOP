package alarm.pied.pipers;

import java.util.List;

public interface Alarms {

    void alarmDetected();

    void simFire(Room room);
    void simBreakIn(String room, Detectors detector, String motion);
    void simMovementAtPool(Detectors detector, Room room);
    
}
