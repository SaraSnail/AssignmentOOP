package alarm.pied.pipers;

public class SmokeDetector extends Detectors {
    private Sprinkler sprinkler = new Sprinkler(true,"sprinkler",false);

    public SmokeDetector(final boolean ON, String kindOfDetector, boolean detectedSomething) {
        super(ON, kindOfDetector, detectedSomething);
    }

    public boolean isOn() {
        return true;
    }

    public Sprinkler getSprinkler() {
        return sprinkler;
    }

    public void setSprinkler(Sprinkler sprinkler) {
        this.sprinkler = sprinkler;

    }

    @Override
    public void setDetectedSomething(boolean detectedSomething) {
        super.setDetectedSomething(detectedSomething);
        if(detectedSomething){
            System.out.print("Detected smoke");
            sprinkler.activate();
        }
    }
}
