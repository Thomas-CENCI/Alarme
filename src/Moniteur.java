import javax.swing.*;
import java.util.ArrayList;

public class Moniteur {
    ArrayList<Alarme> alarmList = new ArrayList<Alarme>();
    private Alarme alarme;
    public String type_moniteur;

    public Moniteur(String type){
        this.type_moniteur = type;
    }

    public String getTypeMoniteur(){
        return this.type_moniteur;
    }

    public ArrayList<Alarme> getAlarmes(){
        return this.alarmList;
    }

    public void addAlarme(Alarme alarme){
        this.alarmList.add(alarme);
    }

    public void addAlarmListener(Alarme alarme){
        this.alarmList.add(alarme);
    }

    public void removeAlarmListener(Alarme alarme){
        this.alarmList.remove(alarme);
    }

    public ArrayList<Alarme> getAlarmesRecues(){
        ArrayList<Alarme> alarmes_recues = new ArrayList<Alarme>();
        for (Alarme alarme : alarmList){
            if (alarme.getStatus() == false){
                alarmes_recues.add(alarme);
            }
        }
        return alarmes_recues;
    }

    public ArrayList<Alarme> getAlarmesTraitees(){
        ArrayList<Alarme> alarmes_traitees = new ArrayList<Alarme>();
        for (Alarme alarme : alarmList){
            if (alarme.getStatus() == true){
                alarmes_traitees.add(alarme);
            }
        }
        return alarmes_traitees;
    }

    public Alarme getSelectedAlarme(String date, String location, String type, String detail){
        for (Alarme alarme : this.getAlarmes()){
            if (alarme.getDate() == date && alarme.getLocation() == location && alarme.getType() == type && alarme.getDetail() == detail){
                
                return alarme;
            }
        }
        return null;
    }

}
