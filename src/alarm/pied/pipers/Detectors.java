package alarm.pied.pipers;

public class Detectors {
    private String kindOfDetector;
    private boolean on;
    private boolean detectedSomething;

    public Detectors(boolean on, String kindOfDetector, boolean detectedSomething) {
        this.on = on;
        this.kindOfDetector = kindOfDetector;
        this.detectedSomething = detectedSomething;
    }

    public String getKindOfDetector() {
        return kindOfDetector;
    }

    public void setKindOfDetector(String kindOfDetector) {
        this.kindOfDetector = kindOfDetector;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public boolean isDetectedSomething() {
        return detectedSomething;
    }

    public void setDetectedSomething(boolean detectedSomething) {
        this.detectedSomething = detectedSomething;

    }
}
