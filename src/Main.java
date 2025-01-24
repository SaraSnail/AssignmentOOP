import alarm.pied.pipers.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Assignment OOP, course 1");


        //Skapar rum med knappstats
        Room garage = new Room("garage");
        Room livingRoom = new Room("living room");
        Room hall = new Room("hall");

        //Skapar knappsatser
        CentralProcessingUnit central1 = new CentralProcessingUnit(true, "central processing unit", garage);
        CentralProcessingUnit central2 = new CentralProcessingUnit(true, "central processing unit", livingRoom);
        CentralProcessingUnit central3 = new CentralProcessingUnit(true, "central processing unit", hall);


        Scanner scanner = new Scanner(System.in);
        boolean systemLoop = true;

        while (systemLoop){
            System.out.println("------------------");
            System.out.println("Choose a room");
            System.out.println("1. Garage");
            System.out.println("2. Living room");
            System.out.println("3. Hall");
            System.out.print("Enter: ");
            switch (scanner.nextInt()){
                case 1: //Garage
                    central1.keypad(garage, livingRoom, hall);
                    systemLoop = false;
                    break;

                case 2: //Living room
                    central2.keypad(garage, livingRoom, hall);
                    systemLoop = false;
                    break;

                case 3: //Hall
                    central3.keypad(garage, livingRoom, hall);
                    systemLoop = false;
                    break;
                default:
                    System.out.println("Error. This choice does not exist. Try again");
            }
        }


    }

}