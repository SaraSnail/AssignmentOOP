package alarm.pied.pipers;

public class Sprinkler extends Detectors{

    public Sprinkler(final boolean ON, String kindOfDetector, boolean detectedSomething) {
        super(ON, kindOfDetector, detectedSomething);
    }

    public boolean isOn() {
        return true;
    }


    public void activate(){
        setDetectedSomething(true);//Sprinkler detekterar något
        System.out.println(", "+ getKindOfDetector() + " activated: " + isOn());
        for (int i = 0; i < 15; i++) {
            System.out.print("^");
        }
        System.out.println();

    }


}
