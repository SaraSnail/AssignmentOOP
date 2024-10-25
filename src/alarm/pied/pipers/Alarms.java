package alarm.pied.pipers;

import java.util.List;

public interface Alarms {

    void alarmDetected();

    void simFire(Room room);
    void simBreakIn(String room, Detectors detector);
    void simMovementAtPool(Detectors detector, Room room);

    void turnOff(List roomList);

    void sirenOff();
    void sirenOff(boolean turnOff);
}
