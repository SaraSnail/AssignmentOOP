package alarm.pied.pipers;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class CentralProcessingUnit implements Alarms {
    private boolean on;
    private String name;
    private final Room belongsTo;

    //Dessa behöver inte anges för att skapa en CPU
    private boolean simulate;
    private boolean alarmsOn;


    public CentralProcessingUnit(boolean on, String name, Room belongsTo) {
        this.on = on;
        this.name = name;
        this.belongsTo = belongsTo;
    }

    //Listor för rummen och sirenerna
    private List<Room> roomList = new ArrayList<>();
    private List<Siren>sirenList = new ArrayList<>();


    //Metoden keypad
    public void keypad(Room garage, Room livingRoom, Room hall){//Skickar med rummen som skapades i main

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        //Skapar rum och sirener
        Room kitchen = new Room("kitchen");
        Room bedroom1 = new Room("first bedroom");
        Room bedroom2 = new Room("second bedroom");
        Room bedroom3 = new Room("third bedroom");
        Room bedroom4 = new Room("fourth bedroom");
        Room bedroom5 = new Room("fifth bedroom");
        Room garden = new Room("garden");

        Siren siren = new Siren(false);
        Siren siren2 = new Siren(false);

        //Skapar String för de upprepande detektorerna. Kan då också jämföra med dessa senare och mindre risk för stavfel
        String window = "window";
        String motion = "motion";
        String smokeDetector = "smoke detector";
        String door = "door";

        //Skapar detektorer i varje rum
        //Garage
        garage.getDetectorsList().add(new Detectors(false,window,false));
        garage.getDetectorsList().add(new Detectors(false,"garage " + door,false));
        garage.setSmokeDetector(new SmokeDetector(true,smokeDetector,false));

        //Living room
        livingRoom.getDetectorsList().add(new Detectors(false,window,false));
        livingRoom.getDetectorsList().add(new Detectors(false,window,false));
        livingRoom.getDetectorsList().add(new Detectors(false,"sliding " + door,false));
        livingRoom.getDetectorsList().add(new Detectors(false,window,false));
        livingRoom.setSmokeDetector(new SmokeDetector(true,smokeDetector,false));

        //Kitchen
        kitchen.getDetectorsList().add(new Detectors(false,window,false));
        kitchen.setSmokeDetector(new SmokeDetector(true,smokeDetector,false));

        //Bedroom1
        bedroom1.getDetectorsList().add(new Detectors(false,window,false));
        bedroom1.getDetectorsList().add(new Detectors(false,window,false));
        bedroom1.setSmokeDetector(new SmokeDetector(true,smokeDetector,false));

        //Bedroom2
        bedroom2.getDetectorsList().add(new Detectors(false,window,false));
        bedroom2.getDetectorsList().add(new Detectors(true,motion,false));
        bedroom2.setSmokeDetector(new SmokeDetector(true,smokeDetector,false));

        //Bedroom3
        bedroom3.getDetectorsList().add(new Detectors(false,window,false));
        bedroom3.setSmokeDetector(new SmokeDetector(true,smokeDetector,false));

        //Bedroom4
        bedroom4.getDetectorsList().add(new Detectors(false,window,false));
        bedroom4.getDetectorsList().add(new Detectors(false,window,false));
        bedroom4.setSmokeDetector(new SmokeDetector(true,smokeDetector,false));

        //Bedroom5
        bedroom5.getDetectorsList().add(new Detectors(false,window,false));
        bedroom5.setSmokeDetector(new SmokeDetector(true,smokeDetector,false));

        //Hall
        hall.getDetectorsList().add(new Detectors(false, window, false));
        hall.getDetectorsList().add(new Detectors(false, "front " + door, false));
        hall.getDetectorsList().add(new Detectors(false,motion,false));
        hall.setSmokeDetector(new SmokeDetector(true,smokeDetector,false));

        //Garden
        garden.getDetectorsList().add(new Detectors(false,motion,false));
        garden.setSmokeDetector(new SmokeDetector(true,smokeDetector,false));


        //Lägger in rummen i en lista
        getRoomList().add(garage);
        getRoomList().add(livingRoom);
        getRoomList().add(kitchen);
        getRoomList().add(bedroom1);
        getRoomList().add(bedroom2);
        getRoomList().add(bedroom3);
        getRoomList().add(bedroom4);
        getRoomList().add(bedroom5);
        getRoomList().add(hall);
        getRoomList().add(garden);

        //Lägger till sirener i en lista
        getSirenList().add(siren);
        getSirenList().add(siren2);


        while (on){
            drawLine();
            System.out.println(getName().toUpperCase() + ":");
            System.out.println(getBelongsTo().getSortOfRoom().toUpperCase());
            System.out.println("Alarm system");
            drawLine();

            System.out.println("1. Reset");
            System.out.println("2. Simulate");
            System.out.println("3. Alarms ON/OFF");
            System.out.println("4. Turn off central processing unit");
            enterScanner();

            switch (scanner.nextInt()){
                case 1://Reset
                    System.out.println("Reset");
                    System.out.println("Turning [OFF] the alarms");
                    setAlarmsOn(false);
                    sirenOff(false);
                    turnOff(getRoomList());

                    break;
                case 2://Simulate
                    setSimulate(true);
                    while (isSimulate()){
                        drawLine();
                        System.out.println("Simulate");
                        drawLine();

                        System.out.println("1. Fire");
                        System.out.println("2. Break-in");
                        System.out.println("3. Movement at the pool");
                        System.out.println("4. Back");
                        enterScanner();

                        switch (scanner.nextInt()){
                            case 1://Fire

                                //slumpar fram en siffra inom index-storleken
                                int randomIndexRoom = random.nextInt(getRoomList().size());
                                Room randomRoom = getRoomList().get(randomIndexRoom);

                                simFire(randomRoom);
                                break;


                            case 2://Break-in
                                int randomIndexRoom2 = random.nextInt(getRoomList().size());
                                Room selectedRoom = getRoomList().get(randomIndexRoom2);

                                //Loopar så att rummet inte kan vara "garden". Vill inte att den blir likadan som simMovementAtPool
                                while (Objects.equals(selectedRoom.getSortOfRoom(), garden.getSortOfRoom())){
                                        randomIndexRoom2 = random.nextInt(getRoomList().size());
                                        selectedRoom = getRoomList().get(randomIndexRoom2);
                                    }


                                int randomIndexInRoom = random.nextInt(selectedRoom.getDetectorsList().size());
                                Detectors selectInRoom = selectedRoom.getDetectorsList().get(randomIndexInRoom);

                                String breakinRoom = selectedRoom.getSortOfRoom();

                                simBreakIn(breakinRoom, selectInRoom, motion);
                                break;



                            case 3://Movement at the pool
                                for (int i = 0; i < getRoomList().size(); i++) {
                                    Room tempRoom = getRoomList().get(i);

                                    //Vill få fram bara "garden"
                                    if(Objects.equals(tempRoom.getSortOfRoom(), garden.getSortOfRoom())){
                                        for (int j = 0; j < tempRoom.getDetectorsList().size(); j++) {

                                            //Vill få fram motion detektorn
                                            Detectors isMotion = tempRoom.getDetectorsList().get(j);
                                            if(Objects.equals(isMotion.getKindOfDetector(), motion)){

                                                //Skickar in dem i metoden simMovementAtPool
                                                simMovementAtPool(isMotion,tempRoom);
                                            }
                                        }

                                    }
                                }


                                break;

                            case 4://Back
                                System.out.println("Back");
                                setSimulate(false);
                                break;

                            default:
                                error();
                                break;
                        }

                    }
                    break;

                case 3://Shut off alarms detections
                    drawLine();
                    System.out.print("The alarm is: ");
                    if (isAlarmsOn()) {
                        System.out.println("[ON]");
                    }
                    else if (!isAlarmsOn()) {
                        System.out.println("[OFF]");
                    }

                    else {//Skulle kunna ta bort denna, men... I fall att
                        error();
                    }

                    System.out.println("Turn it ON/OFF");
                    System.out.println("1. On");
                    System.out.println("2. Off");
                    enterScanner();
                    switch (scanner.nextInt()){
                        case 1://ON
                            System.out.println("Turning on detection alarms");
                            drawLine();
                            setAlarmsOn(true);

                            for (int i = 0; i < getRoomList().size(); i++) {
                                Room tempRoom = getRoomList().get(i);
                                System.out.println(tempRoom.getSortOfRoom().toUpperCase());

                                for (int j = 0; j < tempRoom.getDetectorsList().size(); j++) {
                                    tempRoom.getDetectorsList().get(j).setOn(true);
                                    System.out.println(tempRoom.getDetectorsList().get(j).getKindOfDetector() + ": " + tempRoom.getDetectorsList().get(j).isOn());
                                }
                                drawLine();
                            }

                            break;

                        case 2://OFF
                            System.out.println("Shutting off detection alarms");
                            drawLine();
                            setAlarmsOn(false);

                            turnOff(getRoomList());

                            break;

                        default:
                            error();
                            break;
                    }

                    break;

                case 4://Turn off console
                    System.out.println("Turning off...");
                    setOn(false);
                    break;

                default:
                    //Not a valid number
                    error();
            }

        }

    }




    @Override
    public void alarmDetected(){
        System.out.println("The alarm goes off!");
        for (int i = 0; i < getSirenList().size(); i++) {
            getSirenList().get(i).setFlashing(true);
            getSirenList().get(i).alarmLamp();
        }

    }


//Simulationer
    @Override
    public void simFire(Room room){
        alarmDetected();
        System.out.println("The fire is in the " + room.getSortOfRoom() + "!");
        room.getSmokeDetector().setDetectedSomething(true);//hämtar smoke detector från rummet som skickades med
        sirenOff(false);//så sirenen inte är på när de andra sim körs även om alarmet inte är på


    }


    @Override
    public void simBreakIn(String breakinRoom, Detectors detector, String motion){
        if (!isAlarmsOn()) {
            System.out.println("The alarm isn't on");
            sirenOff();

        }
        else {
            if(!Objects.equals(detector.getKindOfDetector(), motion)){
                alarmDetected();
                detector.setDetectedSomething(true);
                System.out.println("Someone is breaking in through the " + detector.getKindOfDetector() + " in the " + breakinRoom + "!");
            }
            else {
                alarmDetected();
                detector.setDetectedSomething(true);
                System.out.println("The " + detector.getKindOfDetector() + " detector alerted for movement in the " + breakinRoom + "!");
            }

        }


    }

    @Override
    public void simMovementAtPool(Detectors detector, Room room){
        if (!isAlarmsOn()) {
            System.out.println("The alarm isn't on");
            sirenOff();

        } else {
            alarmDetected();
            detector.setOn(true);
            System.out.println("The " + detector.getKindOfDetector() + " detector alerted for movement at the pool in the " + room.getSortOfRoom() + "!");
        }

    }



    private void turnOff(List <Room> roomList){

        for (int i = 0; i < getRoomList().size(); i++) {
            Room tempRoom = getRoomList().get(i);
            System.out.println(tempRoom.getSortOfRoom().toUpperCase());

            for (int j = 0; j < tempRoom.getDetectorsList().size(); j++) {
                tempRoom.getDetectorsList().get(j).setOn(false);
                System.out.println(tempRoom.getDetectorsList().get(j).getKindOfDetector() + ": " + tempRoom.getDetectorsList().get(j).isOn());
            }
            drawLine();
        }

    }



    private void sirenOff(){
        for (int i = 0; i < getSirenList().size(); i++) {
            getSirenList().get(i).alarmLamp();
        }
    }

    private void sirenOff(boolean turnOn){
        for (int i = 0; i < getSirenList().size(); i++) {
            getSirenList().get(i).setFlashing(turnOn);
        }
    }




//Metoder för enklare utskrifter
    private void drawLine(){
        System.out.println("------------------");
    }

    private void error(){
        System.out.println("Error. This choice does not exist. Try again");
    }
    private void enterScanner(){
        System.out.print("Enter: ");
    }



    //Getters och setters
    public List<Siren>getSirenList(){
        return sirenList;
    }
    public void setSirenList(List<Siren>sirenList){
        this.sirenList = sirenList;
    }

    public List<Room> getRoomList() {
        return roomList;
    }
    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public boolean isSimulate() {
        return simulate;
    }

    public void setSimulate(boolean simulate) {
        this.simulate = simulate;
    }

    public Room getBelongsTo() {
        return belongsTo;
    }

    public boolean isAlarmsOn() {
        return alarmsOn;
    }

    public void setAlarmsOn(boolean alarmsOn) {
        this.alarmsOn = alarmsOn;
    }
}
