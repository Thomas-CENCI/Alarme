import java.util.ArrayList;

public class Moniteur {
    ArrayList<Alarme> alarmList = new ArrayList<Alarme>();
    private Anomalie anomalie;
    private String type_moniteur;

    public Moniteur(String type){
        this.type_moniteur = type;
    }

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
        if (type.toUpperCase().equals("RADIATION") && this.type_moniteur.equals("B")) {
            anomalie = new Radiation(this);
            anomalie.setLocalisation(localisation);
            anomalie.setDefcon(defcon);
        }
        if (type.toUpperCase().equals("GAZ") && this.type_moniteur.equals("A")) {
            anomalie = new Gaz(this);
            anomalie.setLocalisation(localisation);
            anomalie.setDefcon(defcon);
        }

        for (Alarme alarme : alarmList){
            if (alarme.getType().equals(type) && alarme.getLocalisation().equals(localisation)){
                boolean exist = false;
                for (Anomalie ano : alarme.getAnomalies()){
                    if (ano.getDate().equals(anomalie.getDate())){
                        exist = true;
                    }
                }

                if (!exist){
                    alarme.addAnomalie(anomalie);
                    alarme.testSeuil(anomalie.seuil());         
                }


            }
        }
    }
}
