import java.util.GregorianCalendar;
import java.text.SimpleDateFormat; 

public class Gaz extends Alarme {

    public Gaz(Object source) {
        super(source);
    }

    public void setType(String type) { this.type = type; }

    public void setDetail(String detail) { this.detail = detail; }

    public void setDate(){
        SimpleDateFormat formattedDate = new SimpleDateFormat("hh:mm:ss dd-MMM-yyyy");
        GregorianCalendar gregorianCalendarDate = new GregorianCalendar();
        this.date = formattedDate.format(gregorianCalendarDate.getTime());
    }

    public void setLocation(String location){ this.location = location; }

    public void setDefcon(int defcon){ this.defcon = defcon; }

    public void setStatus(Boolean status) {this.status = status; }



    public String getDetail(){ return this.detail; }

    public String getDate(){return this.date;}

    public String getType() { return this.type; }

    public boolean getStatus() {return this.status; }

    public String getLocation() {return this.location; }

    public int getDefcon() { return this.defcon; }
}
