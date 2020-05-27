import java.util.GregorianCalendar;

public class Radiation extends Alarme {
    String type;
    GregorianCalendar date;
    String location;
    int defcon;

    String detail;

    public Radiation(Object source) {
        super(source);
    }
    public void setType(String type) { this.type = type; }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setDate(GregorianCalendar date){
        this.date = date;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public void setDefcon(int defcon){
        this.defcon = defcon;
    }

    public String getDetail(){ return this.detail; }
    
    public GregorianCalendar getDate(){return this.date;}

    public String getType() { return this.type; }

    public boolean getStatus() {return this.status; }

    public String getLocation() {return this.location; }

    public int getDefcon() { return this.defcon; }
}
