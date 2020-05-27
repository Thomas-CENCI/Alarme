import java.util.GregorianCalendar;

public class Incendie extends Alarme {
    String type;
    GregorianCalendar date;
    String location;
    int defcon;
    String detail;

    public Incendie(Object source) {
        super(source);
    }

    public void setType(String type) { this.type = type; }

    public void setDate(GregorianCalendar date){
        this.date = date;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public void setDefcon(int defcon){
        this.defcon = defcon;
    }

    public void setDetail(String detail){ this.detail = detail; }
    
    public String getDetail(){ return ""; }

    public GregorianCalendar getDate(){ return this.date; }

    public String getType() { return this.type; }

    public boolean getStatus() {return this.status; }

    public String getLocation() {return this.location; }

    public int getDefcon() { return this.defcon; }

}
