public class Main {
    public static void main(String[] args){
        Moniteur m = new Moniteur();
        m.addAlarmListener(new Alarme());
        m.addAlarmListener(new Alarme());
        m.addAlarmListener(new Alarme());

        m.generateAnomalie("INCENDIE");
        m.generateAnomalie("gaz");
        m.generateAnomalie("radiation");

    }
}
