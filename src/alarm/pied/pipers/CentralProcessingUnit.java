package alarm.pied.pipers;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
//import en för sig eller hela util paketet bättre?

public class CentralProcessingUnit implements Alarms {
    private boolean on;
    private String name;
    private boolean simulate; //Behöver inte ange simulate eller alarmOn när man skapar CPU, de är inbakade här så de alltid fungerar
    private boolean alarmsOn;
    private final Room belongsTo;

    public CentralProcessingUnit(boolean on, String name, Room belongsTo) {
        this.on = on;
        this.name = name;
        this.belongsTo = belongsTo;
    }


    public void keypad(){

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        //Skapar rum och sirener
        Room garage = new Room("garage");
        Room livingRoom = new Room("living room");
        Room kitchen = new Room("kitchen");
        Room bedroom1 = new Room("first bedroom");
        Room bedroom2 = new Room("second bedroom");
        Room bedroom3 = new Room("third bedroom");
        Room bedroom4 = new Room("fourth bedroom");
        Room bedroom5 = new Room("fifth bedroom");
        Room hall = new Room("hall");
        Room garden = new Room("garden");

        Siren siren = new Siren(false);
        Siren siren2 = new Siren(false);


        //Skapar detektorer i varje rum
        //Garage
        garage.getDetectorsList().add(new Detectors(false,"window",false));
        garage.getDetectorsList().add(new Detectors(false,"garage door",false));
        garage.setSmokeDetector(new SmokeDetector(true,"smoke detector",false));

        //Living room
        livingRoom.getDetectorsList().add(new Detectors(false,"window",false));
        livingRoom.getDetectorsList().add(new Detectors(false,"window",false));
        livingRoom.getDetectorsList().add(new Detectors(false,"sliding door",false));
        livingRoom.getDetectorsList().add(new Detectors(false,"motion",false));
        livingRoom.setSmokeDetector(new SmokeDetector(true,"smoke detector",false));

        //Kitchen
        kitchen.getDetectorsList().add(new Detectors(false,"window",false));
        kitchen.setSmokeDetector(new SmokeDetector(true,"smoke detector",false));

        //Bedroom1
        bedroom1.getDetectorsList().add(new Detectors(false,"window",false));
        bedroom1.getDetectorsList().add(new Detectors(false,"window",false));
        bedroom1.setSmokeDetector(new SmokeDetector(true,"smoke detector",false));

        //Bedroom2
        bedroom2.getDetectorsList().add(new Detectors(false,"window",false));
        bedroom2.getDetectorsList().add(new Detectors(true,"motion",false));
        bedroom2.setSmokeDetector(new SmokeDetector(true,"smoke detector",false));

        //Bedroom3
        bedroom3.getDetectorsList().add(new Detectors(false,"window",false));
        bedroom3.setSmokeDetector(new SmokeDetector(true,"smoke detector",false));

        //Bedroom4
        bedroom4.getDetectorsList().add(new Detectors(false,"window",false));
        bedroom4.getDetectorsList().add(new Detectors(false,"window",false));
        bedroom4.setSmokeDetector(new SmokeDetector(true,"smoke detector",false));

        //Bedroom5
        bedroom5.getDetectorsList().add(new Detectors(false,"window",false));
        bedroom5.setSmokeDetector(new SmokeDetector(true,"smoke detector",false));

        //Hall
        hall.getDetectorsList().add(new Detectors(false, "window", false));
        hall.getDetectorsList().add(new Detectors(false, "front door", false));
        hall.getDetectorsList().add(new MotionDetector(false,"motion",false));
        hall.setSmokeDetector(new SmokeDetector(true,"smoke detector",false));

        //Garden
        garden.getDetectorsList().add(new Detectors(false,"motion",false));
        garden.setSmokeDetector(new SmokeDetector(true,"smoke detector",false));


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
            System.out.println(belongsTo.getSortOfRoom().toUpperCase());
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
                    alarmsOn = false;
                    sirenOff(false);
                    turnOff(getRoomList());

                    break;
                case 2://Simulate
                    simulate = true;
                    while (simulate){
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
                                while (Objects.equals(selectedRoom.getSortOfRoom(), "garden")){
                                        randomIndexRoom2 = random.nextInt(getRoomList().size());
                                        selectedRoom = getRoomList().get(randomIndexRoom2);
                                    }


                                int randomIndexInRoom = random.nextInt(selectedRoom.getDetectorsList().size());
                                Detectors selectInRoom = selectedRoom.getDetectorsList().get(randomIndexInRoom);

                                String breakinRoom = selectedRoom.getSortOfRoom();

                                simBreakIn(breakinRoom, selectInRoom);
                                break;



                            case 3://Movement at the pool
                                for (int i = 0; i < getRoomList().size(); i++) {
                                    Room tempRoom = getRoomList().get(i);

                                    if(Objects.equals(tempRoom.getSortOfRoom(), "garden")){
                                        for (int j = 0; j < tempRoom.getDetectorsList().size(); j++) {
                                            Detectors motion = tempRoom.getDetectorsList().get(j);
                                            if(Objects.equals(motion.getKindOfDetector(), "motion")){
                                                simMovementAtPool(motion,tempRoom);
                                            }
                                        }

                                    }
                                }


                                break;

                            case 4://Back
                                System.out.println("Back");
                                simulate = false;
                                break;

                            default:
                                error();
                                break;
                        }

                    }
                    break;

                case 3://Shut off alarms detections
                    System.out.print("The alarm is: ");
                    if (alarmsOn) {
                        System.out.println("[ON]");
                    }
                    else if (!alarmsOn) {
                        System.out.println("[OFF]");
                    }
                    else {
                        error();
                    }

                    System.out.println("Turn it ON/OFF");
                    System.out.println("1. On");
                    System.out.println("2. Off");
                    enterScanner();
                    switch (scanner.nextInt()){
                        case 1://ON
                            System.out.println("Turning on detection alarms");
                            alarmsOn = true;

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
                            alarmsOn = false;

                            turnOff(getRoomList());

                            break;

                        default:
                            error();
                            break;
                    }

                    break;

                case 4://Turn off console
                    System.out.println("Turning off...");
                    on = false;
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
        room.getSmokeDetector().setDetectedSomething(true);
        sirenOff(false);


    }


    @Override
    public void simBreakIn(String breakinRoom, Detectors detector){
        if (!alarmsOn) {
            System.out.println("The alarm isn't on");
            sirenOff();

        }
        else {
            if(!Objects.equals(detector.getKindOfDetector(), "motion")){
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
        if (!alarmsOn) {
            System.out.println("The alarm isn't on");
            sirenOff();

        } else {
            alarmDetected();
            detector.setOn(true);
            System.out.println("The " + detector.getKindOfDetector() + " detector alerted for movement at the pool in the " + room.getSortOfRoom() + "!");
        }

    }


    @Override
    public void turnOff(List roomList){

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


    @Override
    public void sirenOff(){
        for (int i = 0; i < getSirenList().size(); i++) {
            getSirenList().get(i).alarmLamp();
        }
    }
    @Override
    public void sirenOff(boolean turnOff){
        for (int i = 0; i < getSirenList().size(); i++) {
            getSirenList().get(i).setFlashing(turnOff);
        }
    }




//Har dessa så jag inte behöver skriva ut samma sak flera gånger
    public void drawLine(){
        System.out.println("------------------");
    }

    public void error(){
        System.out.println("Error. This choice does not exist. Try again");
    }
    public void enterScanner(){
        System.out.print("Enter: ");
    }


    //Lista på Sirener
    private List<Siren>sirenList = new ArrayList<>();

    public List<Siren>getSirenList(){
        return sirenList;
    }
    public void setSirenList(List<Siren>sirenList){
        this.sirenList = sirenList;
    }


    //Listan på rum
    private List<Room> roomList = new ArrayList<>();

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


}
