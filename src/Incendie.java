import java.util.GregorianCalendar;

public class Incendie extends Anomalie {

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
    
    public GregorianCalendar getDate(){return this.date;}

    public String getType() { return this.type; }

    public int getDefcon() { return this.defcon; }

    public int seuil(){
        return 10;
    }
}
