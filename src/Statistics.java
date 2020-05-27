import java.util.*;

public class Statistics {
    ArrayList<Moniteur> moniteurList;

    public Statistics(ArrayList<Moniteur> moniteurList) {
        this.moniteurList = moniteurList;
    }

    public int numberOfAlarme() {
        int nb = 0;
        for (Moniteur moniteur : moniteurList) {
            for (Alarme alarme : moniteur.getAlarmes()) {
                nb++;
            }
        }
        return nb;
    }

    public HashMap<String, Integer> typesOfAlarme() {
        HashMap<String, Integer> stats = new HashMap<String, Integer>();
        for (Moniteur moniteur : moniteurList) {
            for (Alarme alarme : moniteur.getAlarmes()) {
                if (alarme.getType().equals("INCENDIE")) {
                    int inc = stats.getOrDefault("INCENDIE", 0);
                    stats.put("INCENDIE", inc++);

                    int gaz = stats.getOrDefault("GAZ", 0);
                    stats.put("INCENDIE", gaz++);

                    int rad = stats.getOrDefault("INCENDIE", 0);
                    stats.put("INCENDIE", rad++);
                }
            }
        }
        return stats;
    }

    public int meanDefcon() {
        HashMap<Integer, Integer> defcons = new HashMap<Integer, Integer>();
        for (Moniteur moniteur : moniteurList) {
            for (Alarme alarme : moniteur.getAlarmes()) {
                int n = defcons.getOrDefault(alarme.getDefcon(), 0);
                defcons.put(alarme.getDefcon(), n++);
            }
        }
        // int keyOfMaxValue = Collections.max(defcons.entrySet(),
                // new Comparator<Map.Entry<Integer,Integer>>() {
                    // @Override
                    // public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                        // return o1.getValue() > o2.getValue() ? 1 : -1;
                    // }
                // }).getKey();

        // return defcons.get(keyOfMaxValue);
        return -1;
    }

}
