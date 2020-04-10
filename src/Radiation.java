public class Radiation extends Anomalie {
    int radiation_lvl;

    public Radiation(Object source) {
        super(source);
    }

    public void setRadiation_lvl(int radiation_lvl) {
        this.radiation_lvl = radiation_lvl;
    }

    public int seuil(){
        return 10000;
    }}
