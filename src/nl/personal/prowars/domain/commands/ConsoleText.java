package nl.personal.prowars.domain.commands;

/**
 * Created by maarten on 12-1-2015.
 */
public class ConsoleText {
    private String text;
    private boolean active;

    public ConsoleText() {
        active = false;
        text = "";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void addChar(char c){
        text += c;
    }

    public void removeChar(){
        if (text.length() > 0){
            text = text.substring(0, text.length() -1);
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        if (!active){
            text = "";
        }
        this.active = active;
    }
}
