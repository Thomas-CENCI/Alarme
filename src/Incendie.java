import java.util.GregorianCalendar;

public class Incendie extends Anomalie {

    public Incendie(Object source) {
        super(source);
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
        return 10;
    }
}
