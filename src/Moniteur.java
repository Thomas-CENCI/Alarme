import java.util.ArrayList;

public class Moniteur {
    ArrayList<Alarme> alarmList = new ArrayList<Alarme>();
    private Anomalie anomalie;

    public void addAlarmListener(Alarme alarme){
        this.alarmList.add(alarme);
    }

    public void removeAlarmListener(Alarme alarme){
        this.alarmList.remove(alarme);
    }

    public void generateAnomalie(String type) {
        if (type.toUpperCase().equals("INCENDIE")) {
            anomalie = new Incendie(this);
        }
        if (type.toUpperCase().equals("RADIATION")) {
             anomalie = new Radiation(this);
        }
        if (type.toUpperCase().equals("GAZ")) {
            anomalie = new Gaz(this);
        }

        for (Alarme a : alarmList){
            a.testSeuil(anomalie.seuil());
        }
    }
}
