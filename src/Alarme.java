import java.util.EventObject;
import java.util.GregorianCalendar;

public abstract class Alarme extends EventObject {
    String date;
    String type;
    String location;
    int defcon;
    String detail;
    boolean status = false;
    String detail;

    public Alarme(Object source) {
        super(source);
    }

    abstract void setType(String type);
    abstract void setDate();
    abstract void setLocation(String location);
    abstract void setDefcon(int defcon);
    abstract void setDetail(String detail);

    abstract String getDate();
    abstract String getType();
    abstract boolean getStatus();
    abstract String getLocation();
    abstract int getDefcon();
    abstract String getDetail();

}
