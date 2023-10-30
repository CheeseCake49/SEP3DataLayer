package main.HerskabPadel.models;
import main.Proto.*;
import java.util.List;

public class CenterEntity {

    private String centerName;
    private String location;
    private List<Court> courts;

    public CenterEntity(String centerName, String location, List<Court> courts) {
        this.centerName = centerName;
        this.location = location;
        this.courts = courts;
    }


    public String getCenterName() {
        return centerName;
    }

    public String getLocation() {
        return location;
    }

    public List<Court> getCourts() {
        return courts;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCourts(List<Court> courts) {
        this.courts = courts;
    }

    


}
