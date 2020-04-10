package Package;
import java.util.ArrayList;

public class Moniteur {
    ArrayList<Alarme> alarmList = new ArrayList<Alarme>();

    public void addAlarmListener(Alarme alarme){
        this.alarmList.add(alarme);
    }

    public void removeAlarmListener(Alarme alarme){
        this.alarmList.remove(alarme);
    }

    public void generateAnomalie(String type){
        if(type.toUpperCase() == "INCENDIE") {
            Anomalie anomalie = new Incendie(this);
        }
        if(type.toUpperCase() == "RADIATION") {
            Anomalie anomalie = new Radiation(this);
        }
        if(type.toUpperCase() == "GAZ") {
            Anomalie anomalie = new Gaz(this);
        }
    }
}
