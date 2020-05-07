import java.util.*;

public class Statistics {
    ArrayList<Moniteur> moniteurList;

    public Statistics(ArrayList<Moniteur> moniteurList) {
        this.moniteurList = moniteurList;
    }

    public int numberOfAnomalie() {
        int nb = 0;
        for (Moniteur moniteur : moniteurList) {
            for (Alarme alarme : moniteur.getAlarmes()) {
                for(Anomalie anomalie : alarme.getAnomalies()) {
                    nb++;
                }
            }
        }
        return nb;
    }

    public HashMap<String, Integer> typesOfAnomalie() {
        HashMap<String, Integer> stats = new HashMap<String, Integer>();
        for (Moniteur moniteur : moniteurList) {
            for (Alarme alarme : moniteur.getAlarmes()) {
                for (Anomalie anomalie : alarme.getAnomalies()) {
                    if (anomalie.getType().equals("INCENDIE")) {
                        int inc = stats.getOrDefault("INCENDIE", 0);
                        stats.put("INCENDIE", inc++);

                        int gaz = stats.getOrDefault("GAZ", 0);
                        stats.put("INCENDIE", gaz++);

                        int rad = stats.getOrDefault("INCENDIE", 0);
                        stats.put("INCENDIE", rad++);
                    }
                }
            }
        }
        return stats;
    }

    public int meanDefcon() {
        HashMap<Integer, Integer> defcons = new HashMap<Integer, Integer>();
        for (Moniteur moniteur : moniteurList) {
            for (Alarme alarme : moniteur.getAlarmes()) {
                for (Anomalie anomalie : alarme.getAnomalies()) {
                    int n = defcons.getOrDefault(anomalie.getDefcon(), 0);
                    defcons.put(anomalie.getDefcon(), n++);
                }
            }
        }
        int keyOfMaxValue = Collections.max(defcons.entrySet(),
                new Comparator<Map.Entry<Integer,Integer>>() {
                    @Override
                    public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                        return o1.getValue() > o2.getValue() ? 1 : -1;
                    }
                }).getKey();

        return defcons.get(keyOfMaxValue);
    }

}
