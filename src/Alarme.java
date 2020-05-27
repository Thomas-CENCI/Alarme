import java.util.EventObject;
import java.util.GregorianCalendar;

public abstract class Alarme extends EventObject {
    GregorianCalendar date;
    String type;
    String location;
    int defcon;
    boolean status = false;

    public Alarme(Object source) {
        super(source);
    }

    abstract void setType(String type);
    abstract void setDate(GregorianCalendar date);
    abstract void setLocation(String location);
    abstract void setDefcon(int defcon);
    abstract void setDetail(String detail);

    abstract GregorianCalendar getDate();
    abstract String getType();
    abstract boolean getStatus();
    abstract String getLocation();
    abstract int getDefcon();
    abstract String getDetail();

}
