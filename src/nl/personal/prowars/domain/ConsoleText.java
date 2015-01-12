package nl.personal.prowars.domain;

/**
 * Created by maarten on 12-1-2015.
 */
public class ConsoleText {
    private String text;
    private boolean active;

    public ConsoleText() {
        active = false;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
