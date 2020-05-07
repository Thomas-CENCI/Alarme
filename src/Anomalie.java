import java.util.EventObject;
import java.util.GregorianCalendar;

public abstract class Anomalie extends EventObject {
    GregorianCalendar date;
    String type;
    String location;
    int defcon;

    public Anomalie(Object source) {
        super(source);
    }

    abstract int seuil();
    abstract void setType(String type);
    abstract void setDate(GregorianCalendar date);
    abstract void setLocation(String location);
    abstract void setDefcon(int defcon);
    abstract GregorianCalendar getDate();
    abstract String getType();
    abstract int getDefcon();
}
