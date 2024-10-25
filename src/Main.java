import alarm.pied.pipers.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Assignment OOP, course 1");

        //Skapar rum med knappstats
        Room garage = new Room("garage");
        Room livingRoom = new Room("living room");
        Room hall = new Room("hall");

        CentralProcessingUnit central1 = new CentralProcessingUnit(true, "central processing unit", true,garage);
        CentralProcessingUnit central2 = new CentralProcessingUnit(true, "central processing unit", true,livingRoom);
        CentralProcessingUnit central3 = new CentralProcessingUnit(true, "central processing unit", true,hall);


        Scanner scanner = new Scanner(System.in);
        boolean systemOn = true;

        while (systemOn){
            drawLine();
            System.out.println("Ange rum");
            System.out.println("1. Garage");
            System.out.println("2. Living room");
            System.out.println("3. Hall");
            System.out.print("Enter: ");
            switch (scanner.nextInt()){
                case 1: //Garage
                    drawLine();
                    central1.keypad();
                    systemOn = false;
                    break;

                case 2: //Living room
                    drawLine();
                    central2.keypad();
                    systemOn = false;
                    break;

                case 3: //Hall
                    drawLine();
                    central3.keypad();
                    systemOn = false;
                    break;
                default:
                    System.out.println("Error. This choice does not exist. Try again");
            }
        }


    }

    public static void drawLine(){
        System.out.println("------------------");
    }


}