public class Alarme {

    public void testSeuil(int val){
        int valmax = 100;

        if (val > valmax){
            System.out.println("Alert");
        }
        else{
            System.out.println("Ce qui va c'est que je suis là");
        }
    }
}
