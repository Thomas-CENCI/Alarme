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

    public void generateAnomalie(String localisation, String type, int defcon) {
        if (type.toUpperCase().equals("INCENDIE")) {
            anomalie = new Incendie(this);
            anomalie.setLocalisation(localisation);
            anomalie.setDefcon(defcon);
        }
        if (type.toUpperCase().equals("RADIATION")) {
            anomalie = new Radiation(this);
            anomalie.setLocalisation(localisation);
            anomalie.setDefcon(defcon);
        }
        if (type.toUpperCase().equals("GAZ")) {
            anomalie = new Gaz(this);
            anomalie.setLocalisation(localisation);
            anomalie.setDefcon(defcon);
        }

        for (Alarme a : alarmList){
            a.testSeuil(anomalie.seuil());
        }
    }
}
