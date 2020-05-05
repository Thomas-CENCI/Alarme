import java.util.GregorianCalendar;

public class Radiation extends Anomalie {
    int radiation_lvl;

    public Radiation(Object source) {
        super(source);
    }
    public void setType(String type) { this.type = type; }

    public void setRadiation_lvl(int radiation_lvl) {
        this.radiation_lvl = radiation_lvl;
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
    
    public GregorianCalendar getDate(){return this.date;}

    public String getType() { return this.type; }

    public int seuil(){
        return 10000;
    }}
