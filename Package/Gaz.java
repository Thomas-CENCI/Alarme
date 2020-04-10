package Package;

public class Gaz extends Anomalie {
    String type;

    public Gaz(Object source) {
        super(source);
    }

    public void setType(String type) {
        this.type = type;
    }

    public void seuil(){
        System.out.println("Hello world from gaz");
    }

}
