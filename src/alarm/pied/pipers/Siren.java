package alarm.pied.pipers;

public class Siren {
    private boolean flashing;

    public Siren(boolean flashing) {
        this.flashing = flashing;
    }

    public void alarmLamp(){
        if (isFlashing()){
            for (int i = 0; i < 10; i++) {
                System.out.print("* ");
            }
            System.out.println();
        }

    }


    public boolean isFlashing() {
        return flashing;
    }

    public void setFlashing(boolean flashing) {
        this.flashing = flashing;
    }
}
