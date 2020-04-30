import java.util.GregorianCalendar;

public class Gaz extends Anomalie {
    String type;

    public Gaz(Object source) {
        super(source);
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(GregorianCalendar date){
        this.date = date;
    }

    public void setLocalisation(String localisation){
        this.localisation = localisation;
    }

    public void setDefcon(int defcon){
        this.defcon = defcon;
    }

    public GregorianCalendar getDate(){return this.date;}

    public int seuil(){
       return 100;
    }
}
