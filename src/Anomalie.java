import java.util.EventObject;
import java.util.GregorianCalendar;

public abstract class Anomalie extends EventObject {
    GregorianCalendar date;
    String localisation;
    int defcon;

    public Anomalie(Object source) {
        super(source);
        this.date = new GregorianCalendar();
        this.localisation = "Lab";
        this.defcon = 3;
    }
    abstract int seuil();
    abstract void setDate(GregorianCalendar date);
    abstract void setLocalisation(String localisation);
    abstract void setDefcon(int defcon);
    abstract GregorianCalendar getDate();
}
